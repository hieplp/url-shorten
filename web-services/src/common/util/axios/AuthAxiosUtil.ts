import axios, { AxiosError, AxiosInstance, AxiosRequestConfig, AxiosResponse, InternalAxiosRequestConfig } from "axios";
import { Config } from "../../../config";
import { error, success } from "./CommonAxiosUtil";
import { getCookie, saveToken } from "../CookieUtil";
import { tokenConstant } from "../../constant/Constant";
import TokenModel from "../../model/TokenModel";
import { UnauthorizedException } from "../../exception/UnauthorizedException";

const instance: AxiosInstance = axios.create({ baseURL: Config.apiHost });

// -------------------------------------------------------------------------
// XXX Interceptors
// -------------------------------------------------------------------------

// Axios request interceptor to add access token to headers
instance.interceptors.request.use(async (config: InternalAxiosRequestConfig<any>) => {
  // Check if access token is available in local storage or store
  // Add access token to headers
  config.headers.Authorization = getCookie(tokenConstant.accessToken);
  return config;

}, (error: AxiosError) => {
  return Promise.reject(error);
});

// Axios interceptor to handle token expiration and refresh token
let isRefreshing = false;
let refreshSubscribers: ((accessToken: string) => void)[] = [];

instance.interceptors.response.use((response: AxiosResponse) => {
  return response;
}, async (error: AxiosError) => {

  const originalRequest = error.config;
  if (!originalRequest) {
    return Promise.reject(error);
  }

  if (error.response?.status === 401) {

    // If another request is already refreshing the access token,
    // wait and retry the original request after refresh
    if (isRefreshing) {
      return new Promise((resolve) => {
        refreshSubscribers.push((accessToken: string) => {
          originalRequest.headers.Authorization = accessToken;
          resolve(instance(originalRequest));
        });
      });
    }

    // If the access token is expired, try to refresh it
    await refreshAccessToken();

    // Retry the original request with the new access token
    return instance(originalRequest);
  } else {
    // Throw an error with the error message for generic error handling
    return Promise.reject(error);
  }
})
;

const refreshAccessToken = async (): Promise<string> => {
  isRefreshing = true;

  const refreshToken = getRefreshToken();
  if (!refreshToken) {
    throw new UnauthorizedException("Refresh token is not available");
  }

  // Call the refresh token API to get a new access token
  const response = await axios.get(Config.apiHost + "/auth/refresh-token", {
    // const response = await instance.get("/auth/refresh-token", {
    headers: {
      Authorization: refreshToken
    }
  });
  const data = response.data.data;

  // Update the access token in the Axios headers
  instance.defaults.headers.common["Authorization"] = data.token;

  // Update the access token in the cookie
  saveToken(tokenConstant.accessToken, {
    token: data.token,
    expiredAt: data.expiredAt
  } as TokenModel);

  // Call all the subscribers with the new access token
  refreshSubscribers.forEach((callback) => callback(data.token));

  // Reset the refresh token flag
  isRefreshing = false;

  return data.token;
};

const getRefreshToken = (): string => {
  const refreshToken = getCookie(tokenConstant.refreshToken);

  if (!refreshToken) {
    window.location.href = "login";
    throw new UnauthorizedException("Refresh token is not available");
  }

  return refreshToken;
};

// -------------------------------------------------------------------------
// XXX Auth
// -------------------------------------------------------------------------

export const getWithAuth = <T = any>(url: string, params?: any, config?: AxiosRequestConfig): Promise<T> => {
  // Add authentication headers to the request config
  const authConfig: AxiosRequestConfig = {
    ...config,
    headers: {
      ...config?.headers // Include any existing headers from the config
    },
    params
  };

  return instance.get(url, authConfig)
    .then(success)
    .catch(error);
};

export const postWithAuth = <T = any>(url: string, data?: any, config?: AxiosRequestConfig): Promise<T> => {
  // Add authentication headers to the request config
  const authConfig: AxiosRequestConfig = {
    ...config,
    headers: {
      ...config?.headers // Include any existing headers from the config
    }
  };

  return instance.post(url, data, config)
    .then(success)
    .catch(error);
};

export const patchWithAuth = <T = any>(url: string, data?: any, config?: AxiosRequestConfig): Promise<T> => {
  // Add authentication headers to the request config
  const authConfig: AxiosRequestConfig = {
    ...config,
    headers: {
      ...config?.headers // Include any existing headers from the config
    }
  };

  return instance.patch(url, data, config)
    .then(success)
    .catch(error);
};

export const deleteWithAuth = <T = any>(url: string, params?: any, config?: AxiosRequestConfig): Promise<T> => {
  // Add authentication headers to the request config
  const authConfig: AxiosRequestConfig = {
    ...config,
    headers: {
      ...config?.headers // Include any existing headers from the config
    },
    params
  };

  return instance.delete(url, authConfig)
    .then(success)
    .catch(error);
};



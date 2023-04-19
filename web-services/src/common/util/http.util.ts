import axios, { AxiosError, AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";
import { Config } from "../../config";
import { BadRequestException } from "../exception/BadRequestException";
import Localize from "../constant/Localize";
import { UnauthorizedException } from "../exception/UnauthorizedException";

const instance: AxiosInstance = axios.create({ baseURL: Config.apiHost });

// -------------------------------------------------------------------------
// XXX Common
// -------------------------------------------------------------------------

const success = (response: AxiosResponse): any => {
  return response.data;
};

const error = (error: AxiosError): Promise<never> => {
  // Handle specific error cases, e.g. token expiration, custom error codes, etc.
  // ...

  // Throw an error with the error message for generic error handling
  switch (error.request.status) {
    case 400:
      throw new BadRequestException(error.message || Localize.Error.apiError);
    case 401:
      throw new UnauthorizedException(error.message || Localize.Error.apiError);
    default:
      throw new Error(error.message || Localize.Error.apiError);
  }
};

// -------------------------------------------------------------------------
// XXX Auth
// -------------------------------------------------------------------------
export const getWithoutAuth = <T = any>(url: string, config?: AxiosRequestConfig): Promise<T> => {
  return instance.get(url, config)
    .then(success)
    .catch(error);
};

export const postWithoutAuth = <T = any>(url: string, data?: any, config?: AxiosRequestConfig): Promise<T> => {
  return instance.post(url, data, config)
    .then(success)
    .catch(error);
};

export const putWithoutAuth = <T = any>(url: string, data?: any, config?: AxiosRequestConfig): Promise<T> => {
  return instance.put(url, data, config)
    .then(success)
    .catch(error);
};

export const patchWithoutAuth = <T = any>(url: string, data?: any, config?: AxiosRequestConfig): Promise<T> => {
  return instance.patch(url, data, config)
    .then(success)
    .catch(error);
};

// -------------------------------------------------------------------------
// XXX Auth
// -------------------------------------------------------------------------

export const getWithAuth = <T = any>(url: string, config?: AxiosRequestConfig): Promise<T> => {
  // Add authentication headers to the request config
  const authConfig: AxiosRequestConfig = {
    ...config,
    headers: {
      // Set your authentication headers here, e.g. Authorization: 'Bearer <token>'
      // ...
      ...config?.headers // Include any existing headers from the config
    }
  };

  return instance.get(url, authConfig)
    .then(success)
    .catch(error);
};


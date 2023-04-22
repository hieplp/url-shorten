import axios, { AxiosInstance, AxiosRequestConfig } from "axios";
import { Config } from "../../../config";
import { error, success } from "./CommonAxiosUtil";

const instance: AxiosInstance = axios.create({ baseURL: Config.apiHost });


// -------------------------------------------------------------------------
// XXX NonAuth
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

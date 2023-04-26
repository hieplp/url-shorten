import UrlModel from "../model/UrlModel";

export const isUrlExpired = (url: UrlModel): boolean => {
  return url.expiredAt ? new Date(url.expiredAt) < new Date() : false;
};

export const isUrlDeleted = (url: UrlModel): boolean => {
  return url.isDeleted === 1;
};
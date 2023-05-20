import { defineStore } from "pinia";
import UrlModel from "../common/model/UrlModel";
import { postWithoutAuth } from "../common/util/axios/NonAuthAxiosUtil";
import CreateUrlByPublicRequest from "../common/payload/url/request/CreateUrlByPublicRequest";
import CreateUrlByAuthRequest from "../common/payload/url/request/CreateUrlByAuthRequest";
import { deleteWithAuth, getWithAuth, patchWithAuth, postWithAuth } from "../common/util/axios/AuthAxiosUtil";
import GetUrlsRequest from "../common/payload/url/request/GetUrlsRequest";
import UpdateUrlByAuthRequest from "../common/payload/url/request/UpdateUrlByAuthRequest";

export const useUrlStore = defineStore("url", {
  state: () => ({
      isShortened: false,
      longUrl: "" as string,
      shortUrl: "" as string,
      url: {} as UrlModel,
      urls: [] as UrlModel[],
      total: 0
    }
  ),
  getters: {},
  actions: {

    getUrlByAlias(alias: string, referrer: string): Promise<UrlModel> {
      return new Promise((resolve, reject) => {
        getWithAuth("/public/url/" + alias, {}, {
          headers: {
            "fromHost": referrer
          }
        })
          .then((response) => {
            let url = response.data as UrlModel;
            this.url = url;
            resolve(url);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    createUrlByPublic(request: CreateUrlByPublicRequest): Promise<any> {
      return new Promise((resolve, reject) => {
        postWithoutAuth("/public/url", request)
          .then((response) => {
            this.isShortened = true;
            let url = response.data as UrlModel;
            this.url = url;
            resolve(url);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    createUrlByAuth(request: CreateUrlByAuthRequest): Promise<any> {
      return new Promise((resolve, reject) => {
        postWithAuth("/user/url", request)
          .then((response) => {
            this.isShortened = true;
            let url = response.data as UrlModel;
            this.url = url;
            resolve(url);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    updateUrlByAuth(request: UpdateUrlByAuthRequest): Promise<any> {
      return new Promise((resolve, reject) => {
        patchWithAuth("/user/url", request)
          .then((response) => {
            let url = response.data as UrlModel;
            this.url = url;
            resolve(url);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    getUrlsByAuth(params: GetUrlsRequest): Promise<any> {
      return new Promise((resolve, reject) => {
        getWithAuth("/user/url", params)
          .then((response) => {
            let data = response.data;
            this.urls = data.list as UrlModel[];
            this.total = data.total as number;
            resolve(data);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    getUrlByAuth(urlId: string): Promise<any> {
      return new Promise((resolve, reject) => {
        getWithAuth("/user/url/" + urlId)
          .then((response) => {
            let url = response.data as UrlModel;
            this.url = url;
            resolve(url);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    deleteUrlByAuth(urlId: string): Promise<void> {
      return new Promise((resolve, reject) => {
        deleteWithAuth("/user/url/" + urlId)
          .then((response) => {
            resolve();
          })
          .catch((error) => {
            reject(error);
          });
      });
    }


  }
});

import { defineStore } from "pinia";
import UrlModel from "../common/model/UrlModel";
import { postWithoutAuth } from "../common/util/axios/NonAuthAxiosUtil";
import { CreateUrlByPublicRequest } from "../common/payload/url/request/CreateUrlByPublicRequest";

export const useUrlStore = defineStore("url", {
  state: () => ({
      isShortened: false,
      longUrl: "" as string,
      shortUrl: "" as string,
      url: {} as UrlModel,
      urls: [
        {
          urlId: "12321",
          longUrl: "https://www.google.com",
          shortUrl: "https://www.google.com",
          createdAt: 0,
          modifiedAt: 1,
          status: "ACTIVE"
        }
      ] as UrlModel[]
    }
  ),
  getters: {},
  actions: {
    getUrls() {

    },

    getUrl() {

    },

    getUrlByShortUrl() {

    },

    createUrlByPublic(request: CreateUrlByPublicRequest) {
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
    }
  }
});

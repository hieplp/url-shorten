import { defineStore } from "pinia";
import UrlModel from "../common/model/UrlModel";

export const useUrlStore = defineStore("url", {
  state: () => ({
      isShortened: false,
      longUrl: "" as string,
      shortUrl: "" as string,
      url: {
        urlId: "12321",
        longUrl: "https://www.google.com",
        shortUrl: "https://www.google.com",
        createdAt: 0,
        modifiedAt: 1,
        status: "ACTIVE"
      } as UrlModel,
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

    }
  }
});

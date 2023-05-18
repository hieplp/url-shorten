import { defineStore } from "pinia";
import { getWithAuth } from "../common/util/axios/AuthAxiosUtil";
import StatisticOfSocialMediaModel from "../common/model/StatisticOfSocialMediaModel";
import TotalClicksModel from "../common/model/TotalClicksModel";

export const useStatisticStore = defineStore("statistic", {
  state: () => ({
    statisticOfSocialMedia: [] as StatisticOfSocialMediaModel[]
  }),
  getters: {},
  actions: {

    getStatisticOfSocialMedia(urlId: String): Promise<any> {
      return new Promise((resolve, reject) => {
        getWithAuth("/statistic/social-media", {
          urlId: urlId
        })
          .then((response) => {
            let statisticOfSocialMedia = response.data as StatisticOfSocialMediaModel[];
            this.statisticOfSocialMedia = statisticOfSocialMedia;
            resolve(statisticOfSocialMedia);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    getTotalClicksByDate(urlId: String, fromDate: number, toDate: number): Promise<TotalClicksModel> {
      return new Promise((resolve, reject) => {
        getWithAuth("/statistic/total-clicks-by-date", {
          params: {
            urlId: urlId,
            fromDate: fromDate,
            toDate: toDate
          }
        })
          .then((response) => {
            let totalClick = response.data as TotalClicksModel;
            resolve(totalClick);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    getTotalClicksGroupByMonth(urlId: String, fromDate: number, toDate: number): Promise<TotalClicksModel[]> {
      return new Promise((resolve, reject) => {
        getWithAuth("/statistic/total-clicks-group-by-month", {
          params: {
            urlId: urlId,
            fromDate: fromDate,
            toDate: toDate
          }
        })
          .then((response) => {
            let totalClicks = response.data as TotalClicksModel[];
            resolve(totalClicks);
          })
          .catch((error) => {
            reject(error);
          });
      });
    }

  }
});
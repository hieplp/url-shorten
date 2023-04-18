import { defineStore } from "pinia";
import { UserModel } from "../common/model/UserModel";

export const userUserStore = defineStore("user", {
  state: () => ({
    user: {} as UserModel
  }),
  getters: {},
  actions: {}
});
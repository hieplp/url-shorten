import { defineStore } from "pinia";
import UserModel from "../common/model/UserModel";
import { postWithoutAuth } from "../common/util/axios/NonAuthAxiosUtil";
import RegisterResponse from "../common/payload/auth/response/RegisterResponse";
import RegisterRequest from "../common/payload/auth/request/RegisterRequest";
import LoginRequest from "../common/payload/auth/request/LoginRequest";
import LoginResponse from "../common/payload/auth/response/LoginResponse";
import { getWithAuth } from "../common/util/axios/AuthAxiosUtil";

export const useUserStore = defineStore("user", {
  state: () => ({
    user: {} as UserModel
  }),
  getters: {},
  actions: {
    register(request: RegisterRequest): Promise<RegisterResponse> {
      return new Promise((resolve, reject) => {
        postWithoutAuth("/auth/register", request)
          .then((response) => {
            this.user = response.data;
            resolve(response.data);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    login(request: LoginRequest): Promise<LoginResponse> {
      return new Promise((resolve, reject) => {
        postWithoutAuth("/auth/login", request)
          .then((response) => {
            resolve(response.data);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    getProfile(): Promise<void> {
      return new Promise((resolve, reject) => {
        getWithAuth("/user/profile")
          .then((response) => {
            this.user = response.data;
            resolve();
          })
          .catch((error) => {
            reject(error);
          });
      });
    }
  }
});
import { defineStore } from "pinia";
import UserModel from "../common/model/UserModel";
import { postWithoutAuth } from "../common/util/http.util";
import { RegisterResponse } from "../common/payload/auth/response/RegisterResponse";
import { RegisterRequest } from "../common/payload/auth/request/RegisterRequest";
import { LoginRequest } from "../common/payload/auth/request/LoginRequest";
import { LoginResponse } from "../common/payload/auth/response/LoginResponse";

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
    }
  }
});
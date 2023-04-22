import { defineStore } from "pinia";

import ToastMessageModel from "../common/model/ToastMessageModel";

export const useToastStore = defineStore("toast", {
  state: () => ({
    messages: [] as ToastMessageModel[]
  }),
  getters: {},
  actions: {
    remove(id: string): void {
      this.messages = this.messages.filter((message) => message.id !== id);
    },

    success(message: string): void {
      this.messages.push({
        id: new Date().getTime().toString(),
        message,
        type: "success"
      });
    },

    error(message: string): void {
      this.messages.push({
        id: new Date().getTime().toString(),
        message,
        type: "error"
      });
    }
  }
});
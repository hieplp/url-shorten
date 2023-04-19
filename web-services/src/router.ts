import { createRouter, createWebHistory } from "vue-router";
import Home from "./pages/Home.vue";
import Login from "./pages/auth/Login.vue";
import Register from "./pages/auth/Register.vue";
import Profile from "./pages/user/Profile.vue";
import Urls from "./pages/url/Urls.vue";
import Url from "./pages/url/Url.vue";
import UpdateUrl from "./pages/url/UpdateUrl.vue";


export const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      name: "Home",
      component: Home
    },

    //
    {
      path: "/login",
      name: "Login",
      component: Login
    },
    {
      path: "/register",
      name: "Register",
      component: Register
    },
    {
      path: "/profile",
      name: "Profile",
      component: Profile
    },

    //
    {
      path: "/urls",
      name: "Urls",
      component: Urls
    },
    {
      path: "/urls/:urlId",
      name: "Url",
      component: Url
    },
    {
      path: "/urls/:urlId/update",
      name: "UpdateUrl",
      component: UpdateUrl
    }
  ]
});

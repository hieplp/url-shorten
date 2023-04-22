<template>
  <div class="flex flex-col
              items-center justify-center
              h-screen
              px-6 py-8
              lg:py-0
              m-auto">

    <a class="flex items-center mb-6 text-2xl font-semibold text-blue-600" href="/">
      SHORTEN.IT
    </a>

    <div class="w-full
                bg-white rounded-lg
                shadow
                md:mt-0
                sm:max-w-md
                xl:p-0">
      <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
        <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
          Sign in to your account
        </h1>
        <div class="space-y-4 md:space-y-6">

          <TextInput v-model="username.value"
                     :error-message="username.errorMessage"
                     :is-disabled="isLoading"
                     :is-error="username.isError"
                     :is-required="true"
                     label="Your Username"
                     placeholder="Username"
                     type="text" />

          <TextInput v-model="password.value"
                     :error-message="password.errorMessage"
                     :is-disabled="isLoading"
                     :is-error="password.isError"
                     :is-required="true"
                     label="Password"
                     placeholder="••••••••"
                     type="password" />

          <LoadingButton :is-loading="isLoading" text="Sign in" @click="login" />

          <p class="text-sm text-center font-light text-gray-500 dark:text-gray-400">
            Don’t have an account yet?
            <router-link class="font-bold text-blue-600 hover:underline"
                         to="/register">
              Sign up
            </router-link>
          </p>
        </div>
      </div>
    </div>
  </div>

</template>

<script lang="ts" setup>
import LoadingButton from "../../components/button/LoadingButton.vue";
import { ref } from "vue";
import Localize from "../../common/constant/Localize";
import { useUserStore } from "../../store/user";
import EncryptUtil from "../../common/util/EncryptUtil";
import LoginRequest from "../../common/payload/auth/request/LoginRequest";
import { saveToken } from "../../common/util/CookieUtil";
import { tokenConstant } from "../../common/constant/Constant";
import { useRouter } from "vue-router";
import { BadRequestException } from "../../common/exception/BadRequestException";
import { useToastStore } from "../../store/toast";
import TextInput from "../../components/input/TextInput.vue";
import { UnauthorizedException } from "../../common/exception/UnauthorizedException";

// -------------------------------------------------------------------------
// XXX Common
// -------------------------------------------------------------------------

const router = useRouter();

// -------------------------------------------------------------------------
// XXX Store
// -------------------------------------------------------------------------

const userStore = useUserStore();
const toastStore = useToastStore();

// -------------------------------------------------------------------------
// XXX Inner State
// -------------------------------------------------------------------------

const isLoading = ref(false);

const username = ref({
  value: "",
  isError: false,
  errorMessage: Localize.Login.invalidUsername
});

const password = ref({
  value: "",
  isError: false,
  errorMessage: Localize.Login.invalidPassword
});

// -------------------------------------------------------------------------
// XXX Function
// -------------------------------------------------------------------------

function login() {

  validateUsername();
  validatePassword();

  if (!isValid()) {
    return;
  }

  let request = {
    username: username.value.value,
    password: EncryptUtil.encryptPassword(password.value.value)
  } as LoginRequest;
  userStore.login(request)
    .then((response) => {
      saveToken(tokenConstant.accessToken, response.accessToken);
      saveToken(tokenConstant.refreshToken, response.refreshToken);
      router.push("/");
    })
    .catch((error) => {
      if (error instanceof BadRequestException || error instanceof UnauthorizedException) {
        username.value.isError = true;
        username.value.errorMessage = Localize.Login.invalidCredentials;
      } else {
        toastStore.error(Localize.Error.unknownError);
      }
    })
    .finally(() => {
      isLoading.value = false;
    });
}

// -------------------------------------------------------------------------
// XXX Validation
// -------------------------------------------------------------------------

function isValid(): boolean {
  return !username.value.isError && !password.value.isError;
}

function validateUsername(): void {
  if (username.value.value.length < 3) {
    username.value.isError = true;
    username.value.errorMessage = Localize.Login.invalidUsername;
  } else {
    username.value.isError = false;
  }
}

function validatePassword(): void {
  if (password.value.value.length < 3) {
    password.value.isError = true;
    password.value.errorMessage = Localize.Login.invalidPassword;
  } else {
    password.value.isError = false;
  }
}


</script>

<style lang="scss" scoped>

</style>
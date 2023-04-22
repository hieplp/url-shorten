<template>
  <div class="w-screen h-screen bg-gray-50">

    <div class="flex flex-col
                items-center justify-center
                h-screen
                px-6 py-8
                lg:py-0
                m-auto">

      <router-link class="flex items-center mb-6 text-2xl font-semibold text-blue-600" to="/">
        SHORTEN.IT
      </router-link>

      <div class="w-full
                  bg-white rounded-lg
                  shadow
                  md:mt-0
                  sm:max-w-md
                  xl:p-0">
        <div class="p-6 space-y-4 md:space-y-6 sm:p-8">

          <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
            Register your account
          </h1>

          <div class="space-y-2 md:space-y-2">

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

            <TextInput v-model="confirmPassword.value"
                       :error-message="confirmPassword.errorMessage"
                       :is-disabled="isLoading"
                       :is-error="confirmPassword.isError"
                       :is-required="true"
                       label="Confirm Password"
                       placeholder="••••••••"
                       type="password" />

            <LoadingButton :is-loading="isLoading" text="Sign up" @click="register" />

            <p class="text-sm text-center font-light text-gray-500 dark:text-gray-400">
              Already have an account?
              <router-link class="font-bold text-blue-600 hover:underline"
                           to="/login">
                Sign in
              </router-link>
            </p>

          </div>

        </div>

      </div>

    </div>

  </div>
</template>

<script lang="ts" setup>
import LoadingButton from "../../components/button/LoadingButton.vue";
import { ref } from "vue";
import TextInput from "../../components/input/TextInput.vue";
import EncryptUtil from "../../common/util/EncryptUtil";
import { saveToken } from "../../common/util/CookieUtil";
import { useRouter } from "vue-router";
import RegisterRequest from "../../common/payload/auth/request/RegisterRequest";
import { tokenConstant } from "../../common/constant/Constant";
import { BadRequestException } from "../../common/exception/BadRequestException";
import Localize from "../../common/constant/Localize";
import { useToastStore } from "../../store/toast";
import { useUserStore } from "../../store/user";

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
  errorMessage: Localize.Register.invalidUsername
});

const password = ref({
  value: "",
  isError: false,
  errorMessage: Localize.Register.invalidPassword
});

const confirmPassword = ref({
  value: "",
  isError: false,
  errorMessage: Localize.Register.invalidConfirmPassword
});

// -------------------------------------------------------------------------
// XXX Function
// -------------------------------------------------------------------------

function register(): void {

  validateUsername();
  validatePassword();
  validateConfirmPassword();

  if (!isValid()) {
    return;
  }

  isLoading.value = true;
  let request = {
    username: username.value.value,
    password: EncryptUtil.encryptPassword(password.value.value)
  } as RegisterRequest;
  userStore.register(request)
    .then((response) => {
      saveToken(tokenConstant.accessToken, response.accessToken);
      saveToken(tokenConstant.refreshToken, response.refreshToken);
      router.push("/");
    })
    .catch((error) => {
      if (error instanceof BadRequestException) {
        username.value.isError = true;
        username.value.errorMessage = Localize.Register.duplicatedUsername;
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
  return !(username.value.isError
    || password.value.isError
    || confirmPassword.value.isError);
}

function validateUsername(): void {
  if (username.value.value.length < 3) {
    username.value.isError = true;
    username.value.errorMessage = Localize.Register.invalidUsername;
  } else {
    username.value.isError = false;
  }
}

function validatePassword(): void {
  password.value.isError = password.value.value.length < 8;
}

function validateConfirmPassword(): void {
  confirmPassword.value.isError = confirmPassword.value.value !== password.value.value;
}
</script>

<style lang="scss" scoped>

</style>
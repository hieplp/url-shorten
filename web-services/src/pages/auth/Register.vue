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

          <form class="space-y-2 md:space-y-2">

            <TextInput type="text"
                       v-model="username.value"
                       :is-disabled="isLoading"
                       label="Your Username"
                       placeholder="Username"
                       :is-required="true"
                       :is-error="username.isError"
                       :error-message="username.errorMessage" />

            <TextInput type="password"
                       v-model="password.value"
                       :is-disabled="isLoading"
                       label="Password"
                       placeholder="••••••••"
                       :is-required="true"
                       :is-error="password.isError"
                       :error-message="password.errorMessage" />

            <TextInput type="password"
                       v-model="confirmPassword.value"
                       :is-disabled="isLoading"
                       label="Confirm Password"
                       placeholder="••••••••"
                       :is-required="true"
                       :is-error="confirmPassword.isError"
                       :error-message="confirmPassword.errorMessage" />

            <LoadingButton :is-loading="isLoading" text="Sign up" @click="register" />

            <p class="text-sm text-center font-light text-gray-500 dark:text-gray-400">
              Already have an account?
              <router-link class="font-bold text-blue-600 hover:underline"
                           to="/login">
                Sign in
              </router-link>
            </p>

          </form>

        </div>

      </div>

    </div>

  </div>
</template>

<script lang="ts" setup>
import LoadingButton from "../../components/button/LoadingButton.vue";
import { ref } from "vue";
import TextInput from "../../components/input/TextInput.vue";

const isLoading = ref(false);

const username = ref({
  value: "",
  isError: false,
  errorMessage: "Username is required"
});

const password = ref({
  value: "",
  isError: true,
  errorMessage: "Password is required"
});

const confirmPassword = ref({
  value: "",
  isError: true,
  errorMessage: "Confirm password is required"
});

function register(): void {
  isLoading.value = true;

  console.log(username.value);

  // if (!checkPassword()) {
  //   isLoading.value = false;
  //   return;
  // }

  setTimeout(() => {
    isLoading.value = false;
  }, 2000);
}

function checkPassword(): boolean {
  // if (password.value.length < 8) {
  //   alert("Password must be at least 8 characters long");
  //   return false;
  // }
  // return password.value.value === confirmPassword.value;
  return false;
}

</script>

<style lang="scss" scoped>

</style>
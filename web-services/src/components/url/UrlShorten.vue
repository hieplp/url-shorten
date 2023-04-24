<template>
  <div class="w-full
              flex flex-col
              items-center justify-center
              px-6 py-8
              lg:py-0
              m-auto">

    <div class="w-full
                bg-white rounded-lg
                shadow
                md:mt-0
                sm:max-w-xl
                xl:p-0">
      <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
        <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
          Shorten your URL
        </h1>
        <div class="space-y-4 md:space-y-6">

          <TextAreaInput v-model="longUrl.value"
                         :error-message="longUrl.errorMessage"
                         :is-disabled="isLoading"
                         :is-error="longUrl.isError"
                         :is-required="true"
                         label="Your URL"
                         placeholder="https://example.com"
          />

          <TextInput v-if="isAuth"
                     v-model="alias.value"
                     :error-message="alias.errorMessage"
                     :is-disabled="isLoading"
                     :is-error="alias.isError"
                     :is-required="false"
                     label="URL Alias"
                     placeholder="Alias"
                     type="text"
          />

          <TextInput v-if="isAuth"
                     v-model="expirationTime.value"
                     :error-message="expirationTime.errorMessage"
                     :is-disabled="isLoading"
                     :is-error="expirationTime.isError"
                     :is-required="false"
                     :min="currentDatetime()"
                     label="Expiration Time"
                     placeholder="Expiration Time"
                     type="datetime-local"
          />

          <LoadingButton
            :is-loading="isLoading"
            text="Shorten"
            @click="shortenUrl" />

        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>

import { useUrlStore } from "../../store/url";
import LoadingButton from "../button/LoadingButton.vue";
import { computed, ref } from "vue";
import { doesCookieExist } from "../../common/util/CookieUtil";
import { tokenConstant } from "../../common/constant/Constant";
import TextInput from "../input/TextInput.vue";
import TextAreaInput from "../input/TextAreaInput.vue";
import Localize from "../../common/constant/Localize";
import { isUrlValid } from "../../common/util/ValidationUtil";
import CreateUrlByPublicRequest from "../../common/payload/url/request/CreateUrlByPublicRequest";
import { useToastStore } from "../../store/toast";
import { currentDatetime } from "../../common/util/DateUtil";
import CreateUrlByAuthRequest from "../../common/payload/url/request/CreateUrlByAuthRequest";
import { ConflictException } from "../../common/exception/ConflictException";

// -------------------------------------------------------------------------
// XXX Common
// -------------------------------------------------------------------------

const isAuth = computed(() => doesCookieExist(tokenConstant.refreshToken));

// -------------------------------------------------------------------------
// XXX Store
// -------------------------------------------------------------------------

const urlStore = useUrlStore();
const toastStore = useToastStore();

// -------------------------------------------------------------------------
// XXX Inner State
// -------------------------------------------------------------------------

const isLoading = ref(false);

const longUrl = ref({
  value: "",
  isError: false,
  errorMessage: ""
});

const alias = ref({
  value: "",
  isError: false,
  errorMessage: ""
});

const expirationTime = ref({
  value: "",
  isError: false,
  errorMessage: ""
});

// -------------------------------------------------------------------------
// XXX Function
// -------------------------------------------------------------------------

function shortenUrl() {

  validateLongUrl();

  if (isAuth) {
    validateAlias();
  }

  if (!isValid()) {
    return;
  }

  isLoading.value = true;
  if (isAuth) {

    console.log(new Date(expirationTime.value.value).getTime());

    let request = {
      longUrl: longUrl.value.value,
      alias: alias.value.value
    } as CreateUrlByAuthRequest;

    if (expirationTime.value.value) {
      request.expiredAt = new Date(expirationTime.value.value).getTime();
    }

    urlStore.createUrlByAuth(request)
      .then((response) => {
      })
      .catch((error) => {
        if (error instanceof ConflictException) {
          alias.value.isError = true;
          alias.value.errorMessage = Localize.Url.duplicatedAlias;
        } else {
          toastStore.error(Localize.Error.unknownError);
        }
      })
      .finally(() => {
        isLoading.value = false;
      });
  } else {

    let request = {
      longUrl: longUrl.value.value
    } as CreateUrlByPublicRequest;

    urlStore.createUrlByPublic(request)
      .then((response) => {
      })
      .catch((error) => {
        toastStore.error(Localize.Error.unknownError);
      })
      .finally(() => {
        isLoading.value = false;
      });
  }
}

// -------------------------------------------------------------------------
// XXX Validation
// -------------------------------------------------------------------------

function isValid(): boolean {
  return !longUrl.value.isError
    && !alias.value.isError;
}

function validateLongUrl(): void {
  const url = longUrl.value.value;
  if (!isUrlValid(url)) {
    longUrl.value.isError = true;
    longUrl.value.errorMessage = Localize.Url.invalidUrl;
  } else {
    longUrl.value.isError = false;
  }
}

function validateAlias(): void {
  const value = alias.value.value;
  if (value.length < 6) {
    alias.value.isError = true;
    alias.value.errorMessage = Localize.Url.invalidAlias;
  } else {
    alias.value.isError = false;
  }
}

</script>

<style lang="scss" scoped>

</style>
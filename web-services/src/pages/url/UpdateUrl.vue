<template>
  <Header />

  <ConfirmationModal
    v-if="isConfirmModalVisible"
    :cancel="changeConfirmModalVisibility"
    :confirm="update"
    cancel-text="No, cancel"
    confirm-text="Yes, I'm sure"
    message="Are you want to update this url?" />

  <div class="max-w-screen-xl
	            mx-auto
	            md:px-8 px-2
	           ">

    <Breadcrumb :items="breadcrumbs" last-item="Update" />

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

            <TextInput
              v-model="url.urlId"
              :is-disabled="true"
              label="URL Id"
              type="text"
            />

            <TextAreaInput v-model="longUrl.value"
                           :error-message="longUrl.errorMessage"
                           :is-disabled="isLoading"
                           :is-error="longUrl.isError"
                           :is-required="false"
                           label="Your URL"
                           placeholder="https://example.com"
            />

            <TextInput
              v-model="alias.value"
              :error-message="alias.errorMessage"
              :is-disabled="isLoading"
              :is-error="alias.isError"
              :is-required="false"
              label="URL Alias"
              placeholder="Alias"
              type="text"
            />

            <TextInput
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
              :disabled="!isDataChanged()"
              :is-loading="isLoading"
              text="Update"
              @click="changeConfirmModalVisibility" />

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import Header from "../../components/Header.vue";
import { useUrlStore } from "../../store/url";
import { computed, onMounted, ref } from "vue";
import Breadcrumb from "../../components/Breadcrumb.vue";
import BreadcrumbModel from "../../common/model/BreadcrumbModel";
import { useRoute } from "vue-router";
import ConfirmationModal from "../../components/modal/ConfirmationModal.vue";
import TextInput from "../../components/input/TextInput.vue";
import { currentDatetime, getInputDateTime } from "../../common/util/DateUtil";
import TextAreaInput from "../../components/input/TextAreaInput.vue";
import UpdateUrlByAuthRequest from "../../common/payload/url/request/UpdateUrlByAuthRequest";
import { isUrlValid } from "../../common/util/ValidationUtil";
import Localize from "../../common/constant/Localize";
import LoadingButton from "../../components/button/LoadingButton.vue";
import { useToastStore } from "../../store/toast";
import { ConflictException } from "../../common/exception/ConflictException";

// -------------------------------------------------------------------------
// XXX Common
// -------------------------------------------------------------------------

onMounted(() => {
  loadUrl();
});

const route = useRoute();

const urlId = computed(() => route.params.urlId as string);

// -------------------------------------------------------------------------
// XXX Store
// -------------------------------------------------------------------------

const urlStore = useUrlStore();
const toastStore = useToastStore();

const url = computed(() => urlStore.url);

// -------------------------------------------------------------------------
// XXX Inner State
// -------------------------------------------------------------------------

const isLoading = ref(false);

const isConfirmModalVisible = ref(false);

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

const breadcrumbs = [
  {
    name: "Home",
    path: "/"
  },
  {
    name: "URL",
    path: "/urls"
  }
] as BreadcrumbModel[];

// -------------------------------------------------------------------------
// XXX Functions
// -------------------------------------------------------------------------

function loadUrl(): void {
  urlStore.getUrlByAuth(urlId.value)
    .then(() => {
      longUrl.value.value = url.value.longUrl;
      alias.value.value = url.value.alias;
      expirationTime.value.value = getInputDateTime(url.value.expiredAt);
    })
    .catch((error) => {
      console.log(error);
    });
}

function isDataChanged() {
  return longUrl.value.value !== url.value.longUrl
    || alias.value.value !== url.value.alias
    || expirationTime.value.value !== getInputDateTime(url.value.expiredAt);
}

function update(): void {
  changeConfirmModalVisibility();

  let request = {
    urlId: url.value.urlId
  } as UpdateUrlByAuthRequest;

  if (longUrl.value.value != url.value.longUrl) {
    validateLongUrl();
    request.longUrl = longUrl.value.value;
  }

  if (alias.value.value != url.value.alias) {
    validateAlias();
    request.alias = alias.value.value;
  }

  if (expirationTime.value.value != getInputDateTime(url.value.expiredAt)) {
    request.expiredAt = new Date(expirationTime.value.value).getTime();
  }

  if (!isValid()) {
    return;
  }

  isLoading.value = true;
  urlStore.updateUrlByAuth(request)
    .then(() => {
      toastStore.success(Localize.Url.updateSuccess);
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

function isValid(): boolean {
  return !longUrl.value.isError && !alias.value.isError;
}


function changeConfirmModalVisibility(): void {
  isConfirmModalVisible.value = !isConfirmModalVisible.value;
}

</script>

<style lang="scss" scoped>

</style>
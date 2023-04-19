<template>
  <Header />

  <ConfirmationModal
    v-if="isConfirmModalVisible"
    :cancel="changeConfirmModalVisibility"
    :confirm="confirm"
    cancel-text="No, cancel"
    confirm-text="Yes, I'm sure"
    message="Are you want to update this url?" />

  <div class="max-w-screen-xl
	            mx-auto
	            md:px-8 px-2
	           ">

    <Breadcrumb :items="breadcrumbs" :last-item="urlId" />

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

            <div>
              <label class="block mb-2 text-sm font-medium text-gray-900" for="your-url">
                Your URL
              </label>
              <textarea v-model="url.longUrl"
                        class="bg-gray-50
                               border border-gray-300
                               text-gray-900 sm:text-sm rounded-lg
                               focus:outline-blue-600
                               block w-full p-2.5"
                        disabled
                        name="your-url"
                        placeholder="Username"
                        type="text"
              />
            </div>

            <div>
              <label class="block mb-2 text-sm font-medium text-gray-900"
                     for="url-alias">
                URL Alias
              </label>
              <input v-model="url.shortUrl"
                     class="bg-gray-50
                            border border-gray-300
                            text-gray-900 sm:text-sm rounded-lg
                            focus:outline-blue-600
                            block w-full p-2.5"
                     name="url-alias"
                     placeholder="Alias"
                     type="text">
            </div>

            <button class="w-full
                           font-medium text-sm
                           text-white text-center
                           bg-blue-600
                           hover:bg-blue-700
                           rounded-lg
                           px-5 py-2.5"
                    type="submit"
                    @click="update">
              Update
            </button>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import Header from "../../components/Header.vue";
import { useUrlStore } from "../../store/url";
import { computed, ref } from "vue";
import Breadcrumb from "../../components/Breadcrumb.vue";
import BreadcrumbModel from "../../common/model/BreadcrumbModel";
import { useRoute } from "vue-router";
import ConfirmationModal from "../../components/modal/ConfirmationModal.vue";

const route = useRoute();
const urlStore = useUrlStore();
const url = computed(() => urlStore.url);

const urlId = computed(() => route.params.urlId as string);
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

const isConfirmModalVisible = ref(false);


function update(): void {
  changeConfirmModalVisibility();
}

function confirm(): void {
  changeConfirmModalVisibility();
}

function changeConfirmModalVisibility(): void {
  isConfirmModalVisible.value = !isConfirmModalVisible.value;
}

</script>

<style lang="scss" scoped>

</style>
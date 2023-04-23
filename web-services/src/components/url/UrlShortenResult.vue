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
          Shortened URL
        </h1>

        <div class="space-y-4 md:space-y-6">

          <TextAreaInput v-model="url.longUrl"
                         :is-disabled="true"
                         label="Your Long URL"
                         placeholder="https://example.com"
          />

          <div class="flex flex-col">
            <label class="block mb-2 text-sm font-medium text-gray-900"
                   for="shortened-url">
              Shortened URL
            </label>
            <CopyInput :item="url.shortUrl" />
          </div>

          <div class="grid
                      md:grid-cols-2
                      grid-cols-1
                      gap-1.5">

            <router-link v-if="isAuth"
                         class="font-medium text-sm
                           text-blue-600 text-center
                           border-2 border-blue-600
                           hover:bg-blue-700 hover:text-white
                           rounded-lg
                           px-5 py-2.5"
                         to="/urls"
            >
              My Urls
            </router-link>

            <button :class="{
                      'col-span-2': !isAuth
                    }"
                    class="font-medium text-sm
                           text-white text-center
                           bg-blue-600
                           hover:bg-blue-700
                           rounded-lg
                           px-5 py-2.5"
                    type="submit"
                    @click="shortenAnother">
              Shorten another
            </button>

          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { useUrlStore } from "../../store/url";
import CopyInput from "../input/CopyInput.vue";
import { computed } from "vue";
import TextAreaInput from "../input/TextAreaInput.vue";
import { doesCookieExist } from "../../common/util/CookieUtil";
import { tokenConstant } from "../../common/constant/Constant";

// -------------------------------------------------------------------------
// XXX Common
// -------------------------------------------------------------------------

const isAuth = computed(() => doesCookieExist(tokenConstant.refreshToken));

// -------------------------------------------------------------------------
// XXX Store
// -------------------------------------------------------------------------

const urlStore = useUrlStore();

// -------------------------------------------------------------------------
// XXX Inner State
// -------------------------------------------------------------------------

const url = computed(() => urlStore.url);

// -------------------------------------------------------------------------
// XXX Function
// -------------------------------------------------------------------------

function shortenAnother() {
  urlStore.isShortened = false;
}

</script>

<style scoped>

</style>
<template>
  <div class="flex flex-col
							items-center justify-center
              w-screen h-full
             ">

    <div class="py-32
								px-4
								mx-auto">
      <div class="mx-auto
									max-w-screen-sm
									text-center">

        <h1 class="mb-4
									text-7xl
									tracking-tight
									font-extrabold
									lg:text-9xl
									text-blue-600">
          {{ header }}
        </h1>

        <p class="mb-4
									text-3xl
									tracking-tight
									font-bold
									text-gray-900
									md:text-4xl">
          {{ subHeader }}
        </p>
        <p class="mb-4
									text-lg
									font-light
									text-gray-500">
          {{ message }}
        </p>

        <a :href="redirectUrl"
           class="inline-flex
				          text-white
				          bg-blue-600
				          hover:bg-blue-800
				          rounded-lg
				          text-sm
				          px-5 py-2.5
				          my-4
				          text-center">
          {{ buttonMsg }}
        </a>
      </div>
    </div>

  </div>
</template>

<script lang="ts" setup>

import { PropType } from "vue";
import UrlModel from "../../common/model/UrlModel";

const props = defineProps({
  header: {
    type: String as PropType<string>,
    required: true
  },
  subHeader: {
    type: String as PropType<string>,
    required: true
  },
  message: {
    type: String as PropType<string>,
    required: true
  },
  buttonMsg: {
    type: String as PropType<string>,
    required: true
  },
  redirectUrl: {
    type: Object as PropType<UrlModel>,
    required: true
  }
});

</script>

<style lang="scss" scoped>

</style>
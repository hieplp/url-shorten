<template>
  <nav class="max-w-screen-xl
	            mx-auto
	            mb-8
	            ">
    <ol class="inline-flex
		           items-center
		           space-x-1 md:space-x-3">

      <li v-for="(item, index) in items" :key="index" class="inline-flex items-center">
        <router-link :to="item.path"
                     class="inline-flex
				                    items-center
				                    text-sm
				                    font-bold
				                    text-gray-700
				                    mr-4
				                    hover:text-blue-600">
          {{ item.name }}
        </router-link>

        <svg aria-hidden="true"
             class="w-6 h-6 text-gray-400"
             fill="currentColor"
             viewBox="0 0 20 20"
             xmlns="http://www.w3.org/2000/svg">
          <path clip-rule="evenodd"
                d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                fill-rule="evenodd"></path>
        </svg>

      </li>

      <li aria-current="page" class="flex items-center">
					<span class="text-sm
										   font-bold
										   text-blue-600">
						{{ lastItem }}
				</span>
      </li>
    </ol>
  </nav>

</template>

<script lang="ts" setup>
import { PropType } from "vue";
import BreadcrumbModel from "../common/model/BreadcrumbModel";

const props = defineProps({
  items: {
    type: Array as PropType<BreadcrumbModel[]>,
    required: true
  },
  lastItem: {
    type: String as PropType<string>,
    required: true
  }
});

</script>

<style lang="scss" scoped>

</style>
<template>
  <div class="flex
							items-center
							p-4
							bg-white
							rounded-lg">

    <div class="flex flex-shrink-0
								items-center justify-center
								bg-red-200
								h-16 w-16
								rounded">
      <svg class="w-6 h-6
									fill-current
									text-red-700"
           fill="currentColor"
           viewBox="0 0 20 20"
           xmlns="http://www.w3.org/2000/svg">
        <path clip-rule="evenodd"
              d="M16.707 10.293a1 1 0 010 1.414l-6 6a1 1 0 01-1.414 0l-6-6a1 1 0 111.414-1.414L9 14.586V3a1 1 0 012 0v11.586l4.293-4.293a1 1 0 011.414 0z"
              fill-rule="evenodd" />
      </svg>
    </div>

    <div class="flex-grow
								flex flex-col
								ml-4">
			<span class="text-xl font-bold">
				{{ amount }}
			</span>
      <div class="flex
									items-center
									justify-between">
				<span class="text-gray-500">
					{{ title }}
				</span>
        <span class="text-red-500
										 text-sm
										 font-semibold
										 ml-2">
					-{{ percentage }}%
				</span>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>

import { PropType } from "vue";

const props = defineProps({
  amount: {
    type: Number as PropType<number>,
    required: true
  },
  title: {
    type: String as PropType<string>,
    required: true
  },
  percentage: {
    type: Number as PropType<number>,
    required: true
  }
});

</script>

<style scoped>

</style>
<template>
  <div class="space-y-2">
    <label class="block mb-2 text-sm font-medium text-gray-900" for="username">
      {{ label }}
    </label>

    <div class="bg-gray-100
               border
               text-gray-900 sm:text-sm rounded-lg
               focus:outline-blue-600
               block w-full p-2.5
               disabled:bg-gray-100"
    >
      {{ value ? value : "N/A" }}
    </div>

  </div>
</template>

<script lang="ts" setup>

const props = defineProps({
  value: {
    required: false,
    default: ""
  },

  //
  label: {
    type: String,
    required: true,
    default: ""
  }
});


</script>

<style scoped>

</style>
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
      {{ value ? value : placeholder }}
    </div>

  </div>
</template>

<script lang="ts" setup>

const props = defineProps({
  value: {
    required: true,
    default: ""
  },

  //
  label: {
    type: String,
    required: true,
    default: ""
  },

  placeholder: {
    type: String,
    required: false,
    default: "N/A"
  }
});


</script>

<style scoped>

</style>
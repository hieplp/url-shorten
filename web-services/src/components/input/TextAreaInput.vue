<template>
  <div class="space-y-2">
    <label class="block mb-2 text-sm font-medium text-gray-900" for="username">
      {{ label }} <span v-if="isRequired" class="text-red-600">(*)</span>
    </label>

    <textarea v-model="inputValue"
              :class="{
                  'border-red-500 focus:outline-red-600': isError,
                  'border-gray-300 focus:outline-blue-600': !isError
              }"
              :disabled="isDisabled"
              :placeholder="placeholder"
              class="bg-gray-50
                     border
                     text-gray-900 sm:text-sm rounded-lg
                     focus:outline-blue-600
                     disabled:bg-gray-100
                     block w-full p-2.5"
              name="username"
              type="text" />

    <p v-if="isError"
       class="text-sm
              px-2
              leading-tight
              tracking-tight
              text-red-600">
      {{ errorMessage }}
    </p>

  </div>
</template>

<script lang="ts" setup>

import { computed } from "vue";

const emit = defineEmits<{
  (event: "update:modelValue", ...args: any[]): void;
}>();

const props = defineProps({
  isDisabled: {
    type: Boolean,
    required: true,
    default: false
  },
  modelValue: {
    type: String,
    required: true,
    default: ""
  },

  //
  label: {
    type: String,
    required: true,
    default: ""
  },
  isRequired: {
    type: Boolean,
    required: false,
    default: false
  },
  placeholder: {
    type: String,
    required: false,
    default: ""
  },

  //
  isError: {
    type: Boolean,
    required: false,
    default: false
  },
  errorMessage: {
    type: String,
    required: false,
    default: ""
  }
});

const inputValue = computed({
  get() {
    return props.modelValue;
  },
  set(value) {
    emit("update:modelValue", value);
  }
});


</script>

<style scoped>

</style>
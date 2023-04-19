<template>
  <div :id="toast.id"
       class="flex items-center
              w-full max-w-xs
              p-4 mb-4
              text-gray-500
              bg-white
              rounded-lg
              fixed bottom-5 right-5"
       role="alert">

    <div class="inline-flex
                items-center justify-center
                flex-shrink-0
                w-8 h-8
                text-red-500 bg-red-100
                rounded-lg">
      <svg aria-hidden="true"
           class="w-5 h-5"
           fill="currentColor"
           viewBox="0 0 20 20"
           xmlns="http://www.w3.org/2000/svg">
        <path clip-rule="evenodd"
              d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
              fill-rule="evenodd"></path>
      </svg>
      <span class="sr-only">Check icon</span>
    </div>

    <div class="ml-3 text-sm font-normal">{{ toast.message }}</div>

    <button aria-label="Close"
            class="ml-auto -mx-1.5 -my-1.5
                   p-1.5
                   h-8 w-8
                   bg-white
                   text-gray-400
                   hover:text-gray-900 hover:bg-gray-100
                   rounded-lg
                   focus:ring-2 focus:ring-gray-300
                   inline-flex"
            type="button"
            @click="closeToast">

      <span class="sr-only">Close</span>
      <svg aria-hidden="true"
           class="w-5 h-5"
           fill="currentColor"
           viewBox="0 0 20 20"
           xmlns="http://www.w3.org/2000/svg">

        <path clip-rule="evenodd"
              d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
              fill-rule="evenodd"></path>
      </svg>
    </button>
  </div>
</template>

<script lang="ts" setup>
import ToastMessageModel from "../../common/model/ToastMessageModel";
import { onMounted, PropType } from "vue";
import { useToastStore } from "../../store/toast";

onMounted(() => {
  setTimeout(() => {
    closeToast();
  }, 3000);
});

const props = defineProps({
  toast: {
    type: Object as PropType<ToastMessageModel>,
    required: true
  }
});
const toastStore = useToastStore();

function closeToast(): void {
  const toast = document.getElementById(props.toast.id);
  toast?.remove();
  toastStore.remove(props.toast.id);
}

</script>

<style scoped>

</style>
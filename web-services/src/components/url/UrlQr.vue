<template>
  <div class="flex flex-col justify-center items-center px-1 py-2">
    <qrcode-vue ref="qrRef"
                :size="200"
                :value="shortUrl"
                level="H"
    />

    <span class="mt-4 lg:mt-8
                 px-2
								 w-11/12
								 text-gray-400
								 break-words
								 hover:cursor-pointer hover:text-gray-500"
          @click="copyToClipboard">
			{{ shortUrl }} &#x2398;
		</span>

    <button class="bg-blue-600
		               text-white
		               rounded
		               px-5
		               py-2
		               mt-4 lg:mt-8"
            type="button"
            @click="download">
      Download QR Code
    </button>
  </div>
</template>

<script lang="ts" setup>
import QrcodeVue from "qrcode.vue";
import { useToastStore } from "../../store/toast";
import { ref } from "vue";

const toastStore = useToastStore();
const props = defineProps({
  shortUrl: {
    type: String,
    required: true
  }
});

const qrRef = ref();

function copyToClipboard() {
  navigator.clipboard.writeText(props.shortUrl);
  toastStore.success("Copied to clipboard successfully!");
}

function download() {
  // Get the QR code element from the ref
  const qrcode = qrRef.value.$el;

  // Create a canvas element and set its dimensions
  const canvas = document.createElement("canvas");
  canvas.width = qrcode.offsetWidth;
  canvas.height = qrcode.offsetHeight;

  // Draw the QR code on the canvas
  const context = canvas.getContext("2d");
  if (!context) {
    return;
  }
  context.fillStyle = "#ffffff";
  context.drawImage(qrcode, 0, 0);

  // Convert the canvas to a data URL
  const dataUrl = canvas.toDataURL("image/jpeg");

  // Create a temporary link and download the file
  const link = document.createElement("a");
  link.download = "my-qrcode.jpg";
  link.href = dataUrl;
  link.click();
}

</script>

<style scoped>

</style>
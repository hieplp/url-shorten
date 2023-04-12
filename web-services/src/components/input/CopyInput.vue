<template>
	<div class="flex">
		<input :value="item"
		       class="bg-gray-50
                  border border-gray-300
                  text-gray-900 sm:text-sm rounded-lg
                  block w-full p-2.5
                  mr-2"
		       disabled
		       name="shortened-url"
		       type="text"
		>

		<button class="font-medium text-sm
                   text-white text-center
                   bg-blue-600
                   hover:bg-blue-700
                   rounded-lg
                   px-5 py-2.5"
		        @click="copyToClipboard">
			Copy
		</button>
	</div>
</template>

<script lang="ts" setup>
import {useToastStore} from "../../store/toast";

const props = defineProps({
    item: {
        type: String,
        required: true
    }
});

const toastStore = useToastStore();


function copyToClipboard() {
    navigator.clipboard.writeText(props.item);
    toastStore.success("Copied to clipboard successfully!");
}


</script>

<style lang="scss" scoped>

</style>
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
					Shorten your URL
				</h1>
				<div class="space-y-4 md:space-y-6">

					<div>
						<label class="block mb-2 text-sm font-medium text-gray-900" for="your-url">
							Your URL
						</label>
						<textarea class="bg-gray-50
                             border border-gray-300
                             text-gray-900 sm:text-sm rounded-lg
                             focus:outline-blue-600
                             block w-full p-2.5" name="your-url"
						          placeholder="Username"
						          required type="text"/>
					</div>

					<div>
						<label class="block mb-2 text-sm font-medium text-gray-900"
						       for="url-alias">
							URL Alias
						</label>
						<input class="bg-gray-50
                          border border-gray-300
                          text-gray-900 sm:text-sm rounded-lg
                          focus:outline-blue-600
                          block w-full p-2.5" name="url-alias"
						       placeholder="Alias"
						       type="text">
					</div>

					<LoadingButton
							:is-loading="isLoading"
							text="Shorten"
							@click="shortenUrl"/>

				</div>
			</div>
		</div>
	</div>
</template>

<script lang="ts" setup>

import {useUrlStore} from "../../store/url";
import LoadingButton from "../button/LoadingButton.vue";
import {ref} from "vue";

const urlStore = useUrlStore();

const isLoading = ref(false);

function shortenUrl() {
    isLoading.value = true;

    // TODO: Make API call to shorten URL

    setTimeout(() => {
        urlStore.isShortened = true;
        isLoading.value = false;
    }, 2000);
}

</script>

<style lang="scss" scoped>

</style>
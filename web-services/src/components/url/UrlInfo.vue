<template>
	<div class="p-6 space-y-4 md:space-y-6 sm:p-8">
		<h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
			Shortened URL
		</h1>
		<div class="space-y-4 md:space-y-6">

			<div>
				<label class="block mb-2 text-sm font-medium text-gray-900" for="your-url">
					Long URL
				</label>
				<textarea :value="url.longUrl" class="bg-gray-50
                         border border-gray-300
                         text-gray-900 sm:text-sm rounded-lg
                         focus:outline-blue-600
                         block w-full p-2.5"
				          disabled
				          name="your_url"
				          type="text"
				/>
			</div>

			<div class="flex flex-col">
				<label class="block mb-2 text-sm font-medium text-gray-900"
				       for="shortened-url">
					Shortened URL
				</label>

				<CopyInput :item="url.shortUrl"/>
			</div>

			<div class="grid grid-cols-2 gap-2">
				<div>
					<label class="block mb-2 text-sm font-medium text-gray-900" for="exp-date">
						Expiration Date
					</label>
					<input class="bg-gray-50
                        border border-gray-300
                        text-gray-900 sm:text-sm rounded-lg
                        focus:outline-blue-600
                        block w-full p-2.5" disabled
					       name="exp-date"
					       type="text"
					/>
				</div>

				<div>
					<label class="block mb-2 text-sm font-medium text-gray-900" for="status">
						Status
					</label>
					<input class="bg-gray-50
                        border border-gray-300
                        text-gray-900 sm:text-sm rounded-lg
                        focus:outline-blue-600
                        block w-full p-2.5" disabled
					       name="status"
					       type="text"
					/>
				</div>
			</div>

			<div class="grid grid-cols-2 gap-2">
				<div>
					<label class="block mb-2 text-sm font-medium text-gray-900" for="created-by">
						Created By
					</label>
					<input class="bg-gray-50
                        border border-gray-300
                        text-gray-900 sm:text-sm rounded-lg
                        focus:outline-blue-600
                        block w-full p-2.5" disabled
					       name="created-by"
					       type="text"
					/>
				</div>

				<div>
					<label class="block mb-2 text-sm font-medium text-gray-900" for="created-at">
						Created At
					</label>
					<input class="bg-gray-50
                        border border-gray-300
                        text-gray-900 sm:text-sm rounded-lg
                        focus:outline-blue-600
                        block w-full p-2.5" disabled
					       name="created-at"
					       type="text"
					/>
				</div>
			</div>

			<div class="grid grid-cols-2 gap-2">
				<div>
					<label class="block mb-2 text-sm font-medium text-gray-900" for="modified-by">
						Modified By
					</label>
					<input class="bg-gray-50
                        border border-gray-300
                        text-gray-900 sm:text-sm rounded-lg
                        focus:outline-blue-600
                        block w-full p-2.5" disabled
					       name="modified-by"
					       type="text"
					/>
				</div>

				<div>
					<label class="block mb-2 text-sm font-medium text-gray-900" for="modified-at">
						Modified At
					</label>
					<input class="bg-gray-50
                        border border-gray-300
                        text-gray-900 sm:text-sm rounded-lg
                        focus:outline-blue-600
                        block w-full p-2.5" disabled
					       name="modified-at"
					       type="text"
					/>
				</div>
			</div>

		</div>
	</div>
</template>

<script lang="ts" setup>
import CopyInput from "../input/CopyInput.vue";
import {UrlModel} from "../../common/model/UrlModel";
import {PropType} from "vue";

const props = defineProps({
    url: {
        type: Object as PropType<UrlModel>,
        required: true
    }
});


</script>

<style scoped>

</style>
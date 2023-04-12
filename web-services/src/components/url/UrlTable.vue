<template>
	<div class="overflow-x-auto
							shadow-md sm:rounded-lg">
		<table class="w-full
									text-sm
									text-left
									text-gray-500
									bg-gray-100">
			<thead class="text-xs
										text-gray-700
										uppercase">
			<tr>
				<th class="px-6 py-3" scope="col">
					#
				</th>
				<th class="px-6 py-3" scope="col">
					Short URL
				</th>
				<th class="px-6 py-3" scope="col">
					Status
				</th>
				<th class="px-6 py-3 w-fit" scope="col">
					Created At
				</th>
				<th class="px-6 py-3" scope="col">
					Action
				</th>
			</tr>
			</thead>
			<tbody>
			<tr v-for="(url, index) in urls" :key="url.urlId" class="bg-white border-b">

				<th class="px-6 py-4 font-medium  whitespace-nowrap" scope="row">
					{{ index + 1 }}
				</th>

				<td class="px-6 py-4 whitespace-nowrap">
					{{ url.shortUrl }}
				</td>

				<td class="px-6 py-4">
					{{ url.status }}
				</td>

				<td class="px-6 py-4">
					{{ url.createdAt }}
				</td>

				<td class="px-6 py-4 flex flex-row gap-2">

					<router-link :to="`/urls/${url.urlId}`"
					             class="h-fit w-fit
						                  py-1.5 px-2.5
															rounded
															flex items-center justify-center
															bg-blue-600
															hover:bg-blue-700">
						<MagnifyingGlassIcon class="h-4 w-4 text-white"/>
					</router-link>

					<router-link :to="`/urls/${url.urlId}/update`"
					             class="h-fit w-fit
						                  py-1.5 px-2.5
										          rounded
										          flex items-center justify-center
										          bg-yellow-500
										          hover:bg-yellow-600">
						<PencilIcon class="h-4 w-4 text-white"/>
					</router-link>

				</td>
			</tr>
			</tbody>
		</table>

	</div>
</template>

<script lang="ts" setup>
import {MagnifyingGlassIcon, PencilIcon} from "@heroicons/vue/24/outline";
import {PropType} from "vue";
import {UrlModel} from "../../common/model/UrlModel";

const props = defineProps({
    urls: {
        type: Array as PropType<UrlModel[]>,
        required: true
    }
});


</script>

<style lang="scss" scoped>

</style>

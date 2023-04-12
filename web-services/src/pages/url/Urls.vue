<template>
	<Header/>

	<Error v-if="urls.length == 0"
	       button-msg="Back to Homepage"
	       header="EMPTY"
	       message="Sorry, url list is empty. You'll find lots to explore on the home page."
	       redirect-url="/"
	       sub-header="UrlModel list is empty."/>

	<div v-else
	     class="flex flex-col
							items-center
              w-screen
              px-2 md:px-4">
		<UrlTable :urls="urls"
		          class="w-full max-w-screen-xl"/>

		<Pagination v-if="urls.length > 10"
		            :current-page="1"
		            :from="10"
		            :page-range="1"
		            :per-page="10"
		            :to="20"
		            :total="400"
		            @page-changed=""/>
	</div>
</template>

<script lang="ts" setup>
import UrlTable from "../../components/url/UrlTable.vue";
import {useUrlStore} from "../../store/url";
import {computed} from "vue";
import Header from "../../components/Header.vue";
import Error from "../../components/error/Error.vue";
import Pagination from "../../components/Pagination.vue";

const urlStore = useUrlStore();
const urls = computed(() => urlStore.urls);


</script>

<style scoped>

</style>

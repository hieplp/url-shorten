<template>
	<Header/>

	<div class="max-w-screen-xl
	            mx-auto
	            pt-28 md:pt-32
	           ">
		<UrlShorten v-if="!isShortened"/>
		<UrlShortenResult v-else/>
	</div>
</template>

<script lang="ts" setup>
import Header from "../components/Header.vue";
import UrlShorten from "../components/url/UrlShorten.vue";
import UrlShortenResult from "../components/url/UrlShortenResult.vue";
import {computed} from "vue";
import {useUrlStore} from "../store/url";

const urlStore = useUrlStore();
const isShortened = computed(() => urlStore.isShortened);
</script>

<style lang="scss" scoped>

</style>

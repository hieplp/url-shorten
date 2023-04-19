<template>
  <Header />

  <div class="max-w-screen-xl
	            mx-auto
	            md:px-8 px-2
	           ">

    <Breadcrumb :items="breadcrumbs" :last-item="urlId" />

    <div class="w-full
								grid
								grid-cols-1 md:grid-cols-2
								gap-2">

      <div class="grid
									grid-cols-1 md:grid-cols-2
									gap-2
								  md:hidden
									row-span-1">
        <PercentageCard :amount="100" :is-increase="true" :percentage=10 title="clicks today" />
        <PercentageCard :amount=100 :is-increase="false" :percentage=10 title="clicks last 30 days" />
      </div>

      <UrlInfo :url="url"
               class="bg-white
											shadow
									    rounded-lg" />

      <div class="grid grid-cols-1 gap-2">
        <div class="hidden
                    gap-2
										md:grid
										md:grid-cols-1 lg:grid-cols-2
										row-span-1">
          <PercentageCard :amount="100" :is-increase="true" :percentage=10 title="clicks today" />
          <PercentageCard :amount=100 :is-increase="false" :percentage=10 title="clicks last 30 days" />
        </div>

        <div class="grid
										grid-cols-1 lg:grid-cols-2
										gap-2
										row-span-5">
          <SocialMediaStatistic class="bg-white
																	     shadow
																	     row-span-6
									                     rounded-lg" />

          <UrlQr :short-url="url.shortUrl"
                 class="bg-white
												shadow
												row-span-6
									      rounded-lg" />
        </div>
      </div>

      <UrlStatisticLineChart
        class="bg-white
							   shadow
							   md:col-span-2
							   rounded-lg" />

    </div>
  </div>


</template>

<script lang="ts" setup>
import Header from "../../components/Header.vue";
import Breadcrumb from "../../components/Breadcrumb.vue";
import { computed } from "vue";
import BreadcrumbModel from "../../common/model/BreadcrumbModel";
import { useRoute } from "vue-router";
import { useUrlStore } from "../../store/url";
import SocialMediaStatistic from "../../components/social/SocialMediaStatistic.vue";
import UrlInfo from "../../components/url/UrlInfo.vue";
import PercentageCard from "../../components/card/PercentageCard.vue";
import UrlQr from "../../components/url/UrlQr.vue";
import UrlStatisticLineChart from "../../components/url/UrlStatisticLineChart.vue";


const route = useRoute();
const urlStore = useUrlStore();
const url = computed(() => urlStore.url);

const urlId = computed(() => route.params.urlId as string);
const breadcrumbs = [
  {
    name: "Home",
    path: "/"
  },
  {
    name: "URL",
    path: "/urls"
  }
] as BreadcrumbModel[];

</script>

<style scoped>

</style>
<template>
  <Header/>

  <div class="max-w-screen-xl
	            mx-auto
	            md:px-8 px-2
	           ">

    <Breadcrumb :items="breadcrumbs" :last-item="`DETAIL ( ${urlId} )`"/>

    <div class="w-full
								grid
								grid-cols-1 md:grid-cols-2
								gap-2">

      <div class="grid
									grid-cols-1 md:grid-cols-2
									gap-2
								  md:hidden
									row-span-1">
        <PercentageCard :amount="todayClicks.totalClicks"
                        :is-increase="isIncrease(todayClicks.totalClicks, yesterdayClicks.totalClicks)"
                        :percentage="getPercentage(todayClicks.totalClicks, yesterdayClicks.totalClicks)"
                        title="clicks today"/>

        <PercentageCard :amount="thisMonthClicks.totalClicks"
                        :is-increase="isIncrease(thisMonthClicks.totalClicks, lastMonthClicks.totalClicks)"
                        :percentage="getPercentage(thisMonthClicks.totalClicks, lastMonthClicks.totalClicks)"
                        title="clicks last 30 days"/>
      </div>

      <UrlInfo :url-id="urlId"
               class="bg-white
											shadow
									    rounded-lg"/>


      <div class="grid grid-cols-1 gap-2">
        <div class="hidden
                    gap-2
										md:grid
										md:grid-cols-1 lg:grid-cols-2
										row-span-1">
          <PercentageCard :amount="todayClicks.totalClicks"
                          :is-increase="isIncrease(todayClicks.totalClicks, yesterdayClicks.totalClicks)"
                          :percentage="getPercentage(todayClicks.totalClicks, yesterdayClicks.totalClicks)"
                          title="clicks today"/>

          <PercentageCard :amount="thisMonthClicks.totalClicks"
                          :is-increase="isIncrease(thisMonthClicks.totalClicks, lastMonthClicks.totalClicks)"
                          :percentage="getPercentage(thisMonthClicks.totalClicks, lastMonthClicks.totalClicks)"
                          title="clicks last 30 days"/>
        </div>

        <div class="grid
										grid-cols-1 lg:grid-cols-2
										gap-2
										row-span-5">
          <SocialMediaStatistic :url-id="urlId"
                                class="bg-white
																	     shadow
																	     row-span-6
									                     rounded-lg"/>

          <UrlQr v-if="url.urlId"
                 :short-url="url.shortUrl"
                 class="bg-white
												shadow
												row-span-6
									      rounded-lg"/>
        </div>
      </div>

      <UrlStatisticLineChart :url-id="urlId"
                             class="bg-white
							                      shadow
							                      md:col-span-2
							                      rounded-lg
                                    mb-5"/>

    </div>
  </div>


</template>

<script lang="ts" setup>
import Header from "../../components/Header.vue";
import Breadcrumb from "../../components/Breadcrumb.vue";
import {computed, onMounted, ref} from "vue";
import BreadcrumbModel from "../../common/model/BreadcrumbModel";
import {useRoute} from "vue-router";
import {useUrlStore} from "../../store/url";
import SocialMediaStatistic from "../../components/social/SocialMediaStatistic.vue";
import UrlInfo from "../../components/url/UrlInfo.vue";
import PercentageCard from "../../components/card/PercentageCard.vue";
import UrlQr from "../../components/url/UrlQr.vue";
import UrlStatisticLineChart from "../../components/url/UrlStatisticLineChart.vue";
import {useStatisticStore} from "../../store/statistic";
import TotalClicksModel from "../../common/model/TotalClicksModel";


onMounted(() => {

  // Today
  let today = new Date().getTime();
  let todayStart = new Date(today).setHours(0, 0, 0, 0);
  let todayEnd = new Date(today).setHours(23, 59, 59, 999);
  statisticStore.getTotalClicksByDate(urlId.value, todayStart, todayEnd)
      .then((totalClicks) => {
        todayClicks.value = totalClicks;
      });

  // Yesterday
  let yesterday = today - 24 * 60 * 60 * 1000;
  let yesterdayStart = new Date(yesterday).setHours(0, 0, 0, 0);
  let yesterdayEnd = new Date(yesterday).setHours(23, 59, 59, 999);
  statisticStore.getTotalClicksByDate(urlId.value, yesterdayStart, yesterdayEnd)
      .then((totalClicks) => {
        yesterdayClicks.value = totalClicks;
      });

  // This month
  let thisMonthStart = new Date(today).setDate(1);
  let thisMonthEnd = new Date(today).setDate(31);
  console.log(thisMonthStart, thisMonthEnd)
  statisticStore.getTotalClicksByDate(urlId.value, thisMonthStart, thisMonthEnd)
      .then((totalClicks) => {
        console.log("this month", totalClicks);
        thisMonthClicks.value = totalClicks;
      });

  // Last month
  let lastMonth = thisMonthStart - 30 * 24 * 60 * 60 * 1000;
  let lastMonthStart = new Date(lastMonth).setDate(1);
  let lastMonthEnd = new Date(lastMonth).setDate(31);
  statisticStore.getTotalClicksByDate(urlId.value, lastMonthStart, lastMonthEnd)
      .then((totalClicks) => {
        console.log("last month", totalClicks);
        lastMonthClicks.value = totalClicks;
      });
});

const route = useRoute();
const urlStore = useUrlStore();
const statisticStore = useStatisticStore();
const url = computed(() => urlStore.url);

//
const todayClicks = ref({} as TotalClicksModel);
const yesterdayClicks = ref({} as TotalClicksModel);
//
const thisMonthClicks = ref({} as TotalClicksModel);
const lastMonthClicks = ref({} as TotalClicksModel);

const urlId = computed(() => route.params.urlId as string);
const breadcrumbs = [
  {
    name: "Home",
    path: "/"
  },
  {
    name: "URLS",
    path: "/urls"
  }
] as BreadcrumbModel[];

//

function isIncrease(today: number, yesterday: number): boolean {
  return today > yesterday;
}

function getPercentage(today: number, yesterday: number): number {
  return Math.round((today - yesterday) / (yesterday == 0 ? 1 : yesterday) * 100);
}

</script>

<style scoped>

</style>
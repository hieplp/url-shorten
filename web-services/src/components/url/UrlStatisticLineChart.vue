<template>
  <div class="p-4">
    <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
      Total Clicks
    </h1>

    <Line :data="data" :options="options" class="max-h-96" />

  </div>
</template>

<script lang="ts" setup>
import { Line } from "vue-chartjs";
import { useStatisticStore } from "../../store/statistic";
import { onMounted, ref } from "vue";

const props = defineProps({
  urlId: {
    type: String,
    required: true
  }
});

onMounted(() => {

  let today = new Date().getTime();
  let firstDateOfYear = new Date(today).setMonth(0, 1);
  let lastDateOfYear = new Date(today).setMonth(11, 31);
  statisticStore.getTotalClicksGroupByMonth(props.urlId, firstDateOfYear, lastDateOfYear)
    .then((totalClicks) => {
      let index = 0;
      totalClicks.forEach((totalClicks) => {
        data.value.datasets[0].data[index] = totalClicks.totalClicks;
        index++;
      });
    });

});

const statisticStore = useStatisticStore();


const data = ref({
  labels: [
    "January", "February", "March", "April",
    "May", "June", "July", "August",
    "September", "October", "November", "December"
  ],
  datasets: [
    {
      label: "Clicks",
      backgroundColor: "#1c6ecb",
      data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    }
  ]
});

const options = {
  responsive: true,
  maintainAspectRatio: false
};


</script>

<style lang="scss" scoped>

</style>
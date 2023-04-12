<template>
	<div class="p-4">
		<h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
			Total Clicks
		</h1>

		<Line :data="data" :options="options" class="max-h-96"/>

	</div>
</template>

<script lang="ts" setup>
import {Line} from "vue-chartjs";


const data = {
    labels: ["January", "February", "March", "April", "May", "June", "July"],
    datasets: [
        {
            label: "Clicks",
            backgroundColor: "#1c6ecb",
            data: [40, 39, 10, 40, 39, 80, 40]
        }
    ]
};

const options = {
    responsive: true,
    maintainAspectRatio: false
};


</script>

<style lang="scss" scoped>

</style>
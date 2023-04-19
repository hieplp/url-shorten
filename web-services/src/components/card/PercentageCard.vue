<template>
  <IncreaseCard v-if="isIncrease"
                :amount="amount"
                :percentage="percentage"
                :title="title" />
  <DecreaseCard v-else
                :amount="amount"
                :percentage="percentage"
                :title="title" />
</template>

<script lang="ts" setup>
import { PropType } from "vue";
import IncreaseCard from "./IncreaseCard.vue";
import DecreaseCard from "./DecreaseCard.vue";

const props = defineProps({
  amount: {
    type: Number as PropType<number>,
    required: true
  },
  title: {
    type: String as PropType<string>,
    required: true
  },
  percentage: {
    type: Number as PropType<number>,
    required: true
  },
  isIncrease: {
    type: Boolean as PropType<boolean>,
    required: true
  }
});

</script>

<style scoped>

</style>
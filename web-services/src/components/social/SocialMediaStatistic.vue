<template>
  <div class="">

    <div class="p-4 space-y-4 md:space-y-4 sm:p-4">
      <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
        Social Media
      </h1>


      <input class="bg-gray-50
                    border border-gray-300
                    text-gray-900 sm:text-sm rounded-lg
                    focus:outline-blue-600
                    block w-full p-2.5" name="your_url"
             placeholder="Search for a social media profile"
             type="text"
      />

      <div class="flex flex-col">
        <div v-for="media in socialMediaList"
             :key="media.socialType"
             class="flex
										flex-wrap
										gap-4
										px-1
										py-2
										w-full">

          <div class="">
            <img :alt="socialMediaTypes[media.socialType].name"
                 :src="socialMediaTypes[media.socialType].svgPath"
                 class="h-7 w-7" />
          </div>

          <span class="font-bold">
						{{ socialMediaTypes[media.socialType].name }}
					</span>

          <span class="ml-auto text-gray-400">
						{{ media.totalClicks }}
					</span>
        </div>
      </div>


      <div class="flex border-t-2 py-1 pl-2">
        <span class="font-bold">Total: </span>
        <span class="ml-auto text-gray-400">
					{{ sumOfClicks }}
				</span>
      </div>

    </div>

  </div>
</template>

<script lang="ts" setup>
import { computed, onMounted } from "vue";
import { useStatisticStore } from "../../store/statistic";
import { socialMediaTypes } from "../../common/constant/Constant";

const props = defineProps({
  urlId: {
    type: String,
    required: true,
    default: ""
  }
});

onMounted(() => {
  statisticStore.getStatisticOfSocialMedia(props.urlId);
});

const statisticStore = useStatisticStore();

const socialMediaList = computed(() => statisticStore.statisticOfSocialMedia);
const sumOfClicks = computed(() => {
  let sum = 0;
  socialMediaList.value.forEach((media) => {
    sum += media.totalClicks;
  });
  return sum;
});

</script>

<style lang="scss" scoped>

</style>
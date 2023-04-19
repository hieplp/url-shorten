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
             :key="media.id"
             class="flex
										flex-wrap
										gap-4
										px-1
										py-2
										w-full">

          <div class="">
            <img :alt="media.name" :src="media.svgPath" class="h-7 w-7" />
          </div>

          <span class="font-bold">
						{{ media.name }}
					</span>

          <span class="ml-auto text-gray-400">
						{{ media.count }}
					</span>
        </div>
      </div>


      <div class="flex border-t-2 py-1 pl-2">
        <span class="font-bold">Total: </span>
        <span class="ml-auto text-gray-400">
					{{ socialMediaList.length }}
				</span>
      </div>

    </div>

  </div>
</template>

<script lang="ts" setup>

import SocialMediaModel from "../../common/model/SocialMediaModel";

const socialMediaList = [
  {
    id: 1,
    name: "Messenger",
    count: 0,
    svgPath: "/src/assets/svg/messenger.svg"
  },
  {
    id: 2,
    name: "Facebook",
    count: 0,
    svgPath: "/src/assets/svg/facebook.svg"
  },
  {
    id: 3,
    name: "Google",
    count: 0,
    svgPath: "/src/assets/svg/google.svg"
  },
  {
    id: 4,
    name: "Youtube",
    count: 0,
    svgPath: "/src/assets/svg/youtube.svg"
  },
  {
    id: 5,
    name: "Instagram",
    count: 0,
    svgPath: "/src/assets/svg/instagram.svg"
  },
  {
    id: 6,
    name: "Others",
    count: 0,
    svgPath: "/src/assets/svg/url.svg"
  }
] as SocialMediaModel[];

</script>

<style lang="scss" scoped>

</style>
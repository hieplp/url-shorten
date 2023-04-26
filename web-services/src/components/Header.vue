<template>
  <nav class="max-w-screen-xl
							flex flex-wrap
	            justify-between
	            items-center
							px-2
              p-4
	            mx-auto
	            mb-8
	            md:px-8
              md:py-4
              z-50
	            ">

    <router-link class="self-center
                        text-2xl
                        font-semibold
                        text-blue-600
                        hover:text-blue-700
                        whitespace-nowrap" to="/">
      SHORTEN.IT
    </router-link>

    <label class="pointer-cursor md:hidden block"
           for="menu-toggle">
      <svg aria-hidden="true"
           class="w-6 h-6"
           fill="currentColor"
           viewBox="0 0 20 20"
           xmlns="http://www.w3.org/2000/svg">
        <path clip-rule="evenodd"
              d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z"
              fill-rule="evenodd"></path>
      </svg>
    </label>
    <input id="menu-toggle"
           class="hidden"
           type="checkbox" />

    <div id="menu"
         class="hidden
								w-full
								bg-white
								p-1
								mt-4
								rounded-lg
                md:block
                md:w-auto
                md:bg-transparent
                md:p-0
								md:mt-0">

      <ul class="font-bold
                 flex
                 flex-col
                 md:flex-row
                 p-1
                 md:p-0
                 border
                 border-gray-100
                 rounded-lg
                 md:space-x-2
                 md:mt-0
                 md:border-0">

        <li v-for="tab in tabs"
            :key="tab.name"
            :class="{
                        'hidden': tab.isAuth !== isAuth,
				    }">
          <router-link :class="{
                              'text-white bg-blue-600': currentTab === tab.path,
					             }"
                       :to="tab.path"
                       class="block
                              px-6 py-2
                              text-gray-500
                              rounded
                              hover:text-white
                              hover:bg-blue-600"
                       @click="updateCurrentTab(tab)">
            {{ tab.name }}
          </router-link>
        </li>

      </ul>
    </div>

  </nav>
</template>

<script lang="ts" setup>
import { computed, onMounted, ref } from "vue";
import HeaderTabModel from "../common/model/HeaderTabModel";
import { tokenConstant } from "../common/constant/Constant";
import { doesCookieExist } from "../common/util/CookieUtil";

onMounted(() => {
  const currentPath = window.location.pathname;
  const currentTab = tabs.find(tab => currentPath.includes(tab.path));
  if (currentTab) {
    updateCurrentTab(currentTab);
  }
});

const tabs = [
  { name: "My Urls", path: "/urls", isAuth: true },
  { name: "Profile", path: "/profile", isAuth: true },
  { name: "Login", path: "/login", isAuth: false }
] as HeaderTabModel[];

const currentTab = ref("");

const isAuth = computed(() => doesCookieExist(tokenConstant.accessToken) || doesCookieExist(tokenConstant.refreshToken));

function updateCurrentTab(tab: HeaderTabModel): void {
  currentTab.value = tab.path;
}

</script>

<style lang="scss" scoped>

#menu-toggle:checked + #menu {
  display: block;
}

</style>

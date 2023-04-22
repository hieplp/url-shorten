<template>
  <Header />

  <div class="max-w-screen-xl
	            mx-auto
	            pt-28 md:pt-32
	           ">
    <div class="w-full
                flex flex-col
                items-center justify-center
                px-6 py-8
                lg:py-0
                m-auto">

      <div class="w-full
                  bg-white rounded-lg
                  shadow
                  md:mt-0
                  sm:max-w-xl
                  xl:p-0">
        <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
          <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
            Profile
          </h1>
          <div class="space-y-4 md:space-y-6">


            <div>
              <label class="block mb-2 text-sm font-medium text-gray-900"
                     for="url-alias">
                Username
              </label>
              <input v-model="profile.username" class="bg-gray-50
                            border border-gray-300
                            text-gray-900 sm:text-sm rounded-lg
                            focus:outline-blue-600
                            block w-full p-2.5"
                     disabled
                     name="url-alias"
                     placeholder="Username"
                     type="text"
              >
            </div>

            <button class="w-full
                           font-medium text-sm
                           text-white text-center
                           bg-red-600
                           hover:bg-red-700
                           rounded-lg
                           px-5 py-2.5"
                    type="submit"
                    @click="logout">
              Logout
            </button>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import Header from "../../components/Header.vue";
import { deleteCookie } from "../../common/util/CookieUtil";
import { tokenConstant } from "../../common/constant/Constant";
import { useRouter } from "vue-router";
import { useUserStore } from "../../store/user";
import { computed, onMounted } from "vue";

// -------------------------------------------------------------------------
// XXX Common
// -------------------------------------------------------------------------
const router = useRouter();

onMounted(() => {
  getProfile();
});

// -------------------------------------------------------------------------
// XXX Store
// -------------------------------------------------------------------------
const userStore = useUserStore();

// -------------------------------------------------------------------------
// XXX Local States
// -------------------------------------------------------------------------
const profile = computed(() => userStore.user);

// -------------------------------------------------------------------------
// XXX Function
// -------------------------------------------------------------------------

function getProfile() {
  userStore.getProfile()
    .then(() => {
    })
    .catch((err) => {
      console.log(err);
    });
}

function logout() {
  deleteCookie(tokenConstant.accessToken);
  deleteCookie(tokenConstant.refreshToken);
  router.push("/login");
}
</script>

<style scoped>

</style>
<template>
  <div>
    <h1>
      Redirecting...
    </h1>
  </div>
</template>

<script lang="ts" setup>

import { onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useUrlStore } from "../../store/url";

onMounted(() => {
  const alias = route.params.pathMatch[0];
  const referrer = document.referrer;
  console.log(referrer);
  urlStore.getUrlByAlias(alias, referrer)
    .then((url) => {
      window.location.replace(url.longUrl);
    })
    .catch((error) => {
      console.log(error);
      router.push("/");
    });
});

const route = useRoute();
const router = useRouter();

const urlStore = useUrlStore();

</script>

<style scoped>

</style>
<template>
  <Error button-msg="Back to Homepage"
         header="404"
         message="Sorry, we can't find that page. You'll find lots to explore on the home page."
         redirect-url="/"
         sub-header="Something's missing." />
</template>

<script lang="ts" setup>

import Error from "./Error.vue";
</script>

<style lang="scss" scoped>

</style>
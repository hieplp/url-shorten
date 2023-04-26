<template>
  <div v-if="url.urlId" class="p-6 space-y-4 md:space-y-6 sm:p-8">
    <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
      Shortened URL
    </h1>
    <div class="space-y-4 md:space-y-6">

			<TextArea :value="url.longUrl"
                label="Long URL"
      />

      <Text :value="url.alias"
            label="URL Alias"
      />

      <div class="flex flex-col">
        <label class="block mb-2 text-sm font-medium text-gray-900"
               for="shortened-url">
          Shortened URL
        </label>

        <CopyInput :item="url.shortUrl" />
      </div>

      <div class="grid grid-cols-2 gap-2">

        <Text :value="formatDatetime(url.expiredAt)"
              label="Expiration Date"
        />

        <Text :value="formatStatus(url)"
              label="Status"
        />

      </div>

      <div class="grid grid-cols-2 gap-2">
        <Text :value="url.createdBy"
              label="Created By"
        />

        <Text :value="formatDatetime(url.createdAt)"
              label="Created At"
        />
      </div>

      <div class="grid grid-cols-2 gap-2">
        <Text :value="url.modifiedBy"
              label="Modified By"
        />

        <Text :value="formatDatetime(url.modifiedAt)"
              label="Modified At"
        />
      </div>

    </div>
  </div>
</template>

<script lang="ts" setup>

import CopyInput from "../input/CopyInput.vue";
import { computed, onMounted } from "vue";
import { formatDatetime } from "../../common/util/DateUtil";
import Text from "../text/Text.vue";
import TextArea from "../text/TextArea.vue";
import { useUrlStore } from "../../store/url";
import UrlModel from "../../common/model/UrlModel";
import { isUrlExpired } from "../../common/util/UrlUtil";
import Localize from "../../common/constant/Localize";
import { urlStatus } from "../../common/constant/Constant";

onMounted(() => {
  urlStore.getUrlByAuth(props.urlId)
    .then(() => {
    })
    .catch((error) => {
      console.log(error);
    });
});

const urlStore = useUrlStore();

const props = defineProps({
  urlId: {
    type: String,
    required: true,
    default: ""
  }
});

const url = computed(() => urlStore.url);

function formatStatus(url: UrlModel) {

  if (isUrlExpired(url)) {
    return Localize.Url.expired;
  }

  switch (url.status) {
    case urlStatus.active:
      return Localize.Url.active;
    default:
      return Localize.Url.inactive;
  }
}


</script>

<style scoped>

</style>
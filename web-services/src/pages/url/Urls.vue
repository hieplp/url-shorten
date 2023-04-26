<template>
  <Header />

  <Error v-if="urls.length == 0"
         button-msg="Back to Homepage"
         header="EMPTY"
         message="Sorry, url list is empty. You'll find lots to explore on the home page."
         redirect-url="/"
         sub-header="UrlModel list is empty." />


  <div v-else
       class="flex flex-col
							items-center
              w-screen
              px-2 md:px-4">

    <ConfirmationModal v-if="isConfirmModalVisible"
                       :cancel="changeConfirmModalVisibility"
                       :confirm="deleteUrl"
                       cancel-text="No, cancel"
                       confirm-text="Yes, I'm sure"
                       message="Are you want to delete this url?" />


    <UrlTable :delete-url="openDeleteModal"
              :from="params.from"
              :urls="urls"
              class="w-full max-w-screen-xl"
    />

    <Pagination v-if="total > params.limit"
                :current-page="currentPage"
                :from="params.from"
                :page-range="1"
                :per-page="params.limit"
                :total="total"
                @page-changed="selectPage" />
  </div>
</template>

<script lang="ts" setup>
import UrlTable from "../../components/url/UrlTable.vue";
import { useUrlStore } from "../../store/url";
import { computed, onMounted, ref } from "vue";
import Header from "../../components/Header.vue";
import Error from "../../components/error/Error.vue";
import Pagination from "../../components/Pagination.vue";
import GetUrlsRequest from "../../common/payload/url/request/GetUrlsRequest";
import { useToastStore } from "../../store/toast";
import Localize from "../../common/constant/Localize";
import ConfirmationModal from "../../components/modal/ConfirmationModal.vue";
import UrlModel from "../../common/model/UrlModel";

// -------------------------------------------------------------------------
// XXX Common
// -------------------------------------------------------------------------
onMounted(() => {
  loadUrls();
});

// -------------------------------------------------------------------------
// XXX Store
// -------------------------------------------------------------------------

const urlStore = useUrlStore();
const toastStore = useToastStore();

const urls = computed(() => urlStore.urls);
const total = computed(() => urlStore.total);


// -------------------------------------------------------------------------
// XXX Local States
// -------------------------------------------------------------------------


const currentPage = ref<number>(1);
const params = ref<GetUrlsRequest>(
  {
    from: 0,
    limit: 10,
    order: "createdAt",
    by: "desc"
  }
);

const isConfirmModalVisible = ref<boolean>(false);

// -------------------------------------------------------------------------
// XXX Methods
// -------------------------------------------------------------------------

function selectPage(page: number) {
  currentPage.value = page;
  let previousPage = page - 1;
  params.value.from = (previousPage <= 0 ? 0 : previousPage) * params.value.limit;
  loadUrls();
}

function loadUrls() {
  urlStore.getUrlsByAuth(params.value)
    .then(() => {
    })
    .catch((error) => {
      toastStore.error(Localize.Error.unknownError);
    });
}

function openDeleteModal(url: UrlModel) {
  isConfirmModalVisible.value = true;
  urlStore.url = url;
}

function deleteUrl() {
  changeConfirmModalVisibility();

  // TODO: Add loading

  if (!urlStore.url) {
    return;
  }

  urlStore.deleteUrlByAuth(urlStore.url.urlId)
    .then(() => {
      toastStore.success(Localize.Url.deleteSuccess);
      loadUrls();
    })
    .catch((error) => {
      toastStore.error(Localize.Error.unknownError);
    });
}

function changeConfirmModalVisibility(): void {
  isConfirmModalVisible.value = !isConfirmModalVisible.value;
}


</script>

<style scoped>

</style>

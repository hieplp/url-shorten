<template>
	<div v-for="toast in toasts" :key="toast.id">
		<transition name="fade">
			<SuccessToast v-if="toast.type === 'success'" :toast="toast"/>
		</transition>

	</div>

</template>

<script lang="ts" setup>
import {useToastStore} from "../../store/toast";
import {computed} from "vue";
import SuccessToast from "./SuccessToast.vue";

const toastStore = useToastStore();

const toasts = computed(() => toastStore.messages);


</script>

<style lang="scss" scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}

</style>
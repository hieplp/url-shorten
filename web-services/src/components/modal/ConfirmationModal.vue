<template>
	<div id="popup-modal"
	     class="fixed
	            top-0
	            left-0
	            right-0
	            z-50
	            p-4
	            bg-gray-500
	            bg-opacity-80
	            overflow-x-hidden
	            overflow-y-auto
	            md:inset-0
	            h-[calc(100%-1rem)]
	            max-h-full"
	     tabindex="-1">

		<div class="relative
                flex flex-col
                justify-center items-center
								min-h-full">
			<div class="relative
                  bg-white
                  rounded-lg
                  shadow">

				<button class="absolute
                       top-3
                       right-2.5
                       text-gray-400
                       bg-transparent
                       hover:bg-gray-200
                       hover:text-gray-900
                       rounded-lg
                       text-sm
                       p-1.5
                       ml-auto
                       inline-flex
                       items-center"
				        data-modal-hide="popup-modal"
				        type="button"
				        @click="cancel">

					<XMarkIcon class="w-5 h-5 text-gray-500 text-lg"/>
					<span class="sr-only">Close modal</span>
				</button>

				<div class="p-6 md:px-16
                    text-center">

					<InformationCircleIcon class="mx-auto mb-4 text-gray-400 w-14 h-14 dark:text-gray-200"/>

					<h3 class="mb-5
                     text-lg
                     font-normal
                     text-gray-500">
						{{ message }}
					</h3>

					<button :class="confirmClass" class="text-white
                         bg-red-600
                         hover:bg-red-800
                         focus:ring-4
                         focus:outline-none
                         focus:ring-red-300
                         font-medium
                         rounded-lg
                         text-sm
                         inline-flex
                         items-center
                         px-5
                         py-2.5
                         text-center
                         mr-2"
					        data-modal-hide="popup-modal"
					        type="button"
					        @click="confirm">
						{{ confirmText }}
					</button>

					<button :class="cancelClass"
					        class="text-gray-500
                         bg-white
                         hover:bg-gray-100
                         focus:ring-4
                         focus:outline-none
                         focus:ring-gray-200
                         rounded-lg
                         border
                         border-gray-200
                         text-sm
                         font-medium
                         px-5
                         py-2.5
                         hover:text-gray-900
                         focus:z-10"
					        data-modal-hide="popup-modal"
					        type="button"
					        @click="cancel">
						{{ cancelText }}
					</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script lang="ts" setup>
import {InformationCircleIcon, XMarkIcon} from "@heroicons/vue/24/outline";

const props = defineProps({
    message: {
        type: String,
        required: true
    },
    confirmText: {
        type: String,
        required: true
    },
    cancelText: {
        type: String,
        required: true
    },
    confirm: {
        type: Function,
        required: true
    },
    cancel: {
        type: Function,
        required: true
    },
    //
    confirmClass: {
        type: String,
        required: false
    },
    cancelClass: {
        type: String,
        required: false
    }
});
</script>

<style scoped>

</style>
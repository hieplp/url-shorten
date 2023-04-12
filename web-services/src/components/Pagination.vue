<template>
	<div class="flex
              border-t
              border-gray-200
              px-1
              py-3">

		<div class="flex
                flex-1
                justify-between
                sm:hidden">
			<a class="relative
                inline-flex items-center
                rounded-md
                border
                border-gray-300
                bg-white
                px-4
                py-2
                text-sm
                font-medium
                text-gray-700
                hover:bg-gray-50"
			   href="#">Previous</a>
			<a class="relative
                ml-3
                inline-flex
                items-center
                rounded-md
                border
                border-gray-300
                bg-white
                px-4
                py-2
                text-sm
                font-medium
                text-gray-700 hover:bg-gray-50"
			   href="#">Next</a>
		</div>

		<div class="hidden
                sm:flex
                sm:flex-1
                sm:items-center
                sm:justify-between">
			<div>
				<nav aria-label="Pagination"
				     class="isolate inline-flex -space-x-px rounded-md shadow-sm">
					<a class="cursor-pointer
                    relative
                    inline-flex
                    items-center
                    rounded-l-md
                    border
                    border-gray-300
                    bg-white
                    px-2
                    py-2
                    text-sm
                    font-medium
                    text-gray-500
                    hover:bg-gray-50
                    focus:z-20"
					   @click="previousPage">
						<span class="sr-only">Previous</span>
						<ChevronLeftIcon aria-hidden="true" class="h-5 w-5"/>
					</a>

					<a v-if="hasFirst()"
					   :class="{'border-blue-500 border-2 bg-blue-50':isCurrentPage(1)}"
					   class="cursor-pointer
                    border
                    border-gray-300
                    bg-white
                    px-4
                    py-2
                    text-sm
                    font-medium
                    text-gray-500
                    hover:bg-gray-50
                    focus:z-20"
					   @click="selectPage(1)">
						1
					</a>

					<a v-if="hasFirst()"
					   class="cursor-pointer
                    border
                    border-gray-300
                    bg-white
                    px-4
                    py-2
                    text-sm
                    font-medium
                    text-gray-500
                    hover:bg-gray-50
                    focus:z-20"
					>
						...
					</a>

					<a v-for="page in renderPages" :key="page"
					   :class="{'border-blue-500 border-2 bg-blue-50':isCurrentPage(page)}"
					   class="cursor-pointer
                    border
                    border-gray-300
                    bg-white
                    px-4
                    py-2
                    text-sm
                    font-medium
                    text-gray-500
                    hover:bg-gray-50
                    focus:z-20"
					   @click="selectPage(page)"
					>

						{{ page }}
					</a>

					<a v-if="hasLast()"
					   class="cursor-pointer
                    border
                    border-gray-300
                    bg-white
                    px-4
                    py-2
                    text-sm
                    font-medium
                    text-gray-500
                    hover:bg-gray-50
                    focus:z-20">
						...
					</a>

					<a v-if="hasLast()"
					   :class="{'border-blue-500 border-2 bg-blue-50':isCurrentPage(totalPages)}"
					   class="cursor-pointer
                    border
                    border-gray-300
                    bg-white
                    px-4
                    py-2
                    text-sm
                    font-medium
                    text-gray-500
                    hover:bg-gray-50
                    focus:z-20"
					   @click="selectPage(totalPages)"
					>
						{{ totalPages }}
					</a>

					<a class="cursor-pointer
                    relative
                    inline-flex
                    items-center
                    rounded-r-md
                    border
                    border-gray-300
                    bg-white
                    px-2
                    py-2
                    text-sm
                    font-medium
                    text-gray-500
                    hover:bg-gray-50
                    focus:z-20"
					   @click="nextPage">
						<span class="sr-only">Next</span>
						<ChevronRightIcon aria-hidden="true" class="h-5 w-5"/>
					</a>
				</nav>
			</div>
		</div>
	</div>

</template>
<script setup>
import {ChevronLeftIcon, ChevronRightIcon} from "@heroicons/vue/24/outline";
import {computed, toRefs} from "vue";

const props = defineProps({
    currentPage: {
        type: Number,
        default: 1
    },
    total: {
        type: Number,
        required: true
    },
    from: {
        type: Number,
        required: true
    },
    to: {
        type: Number,
        required: true
    },
    perPage: {
        type: Number,
        required: true
    },
    pageRange: {
        type: Number,
        required: true
    }
});
const {currentPage, total, from, to, perPage, pageRange} = toRefs(props);

// Emits
const emit = defineEmits(["page-changed"]);

// XXX Data
const totalPages = computed(() => Math.ceil(total.value / perPage.value));

// XXX Computed
const rangeStart = computed(() => {
    let start = currentPage.value - pageRange.value;
    return (start > 0) ? start : 1;
});
const rangeEnd = computed(() => {
    let end = currentPage.value + pageRange.value;
    return (end < totalPages.value) ? end : totalPages.value;
});
const renderPages = computed(() => {
    let pagesArray = [];
    for (let i = rangeStart.value; i <= rangeEnd.value; i++) {
        pagesArray.push(i);
    }

    return pagesArray;
});

// XXX Methods
function hasFirst() {
    return rangeStart.value !== 1 && (totalPages.value - 2) > pageRange.value;
}

function hasLast() {
    return rangeEnd.value < totalPages.value && (totalPages.value - 2) > pageRange.value;
}

function isCurrentPage(page) {
    return currentPage.value === page;
}

function nextPage() {
    let nextPage = currentPage.value + 1;
    if (nextPage <= totalPages.value) {
        selectPage(nextPage);
    }
}

function previousPage() {
    let previousPage = currentPage.value - 1;
    if (previousPage > 0) {
        selectPage(previousPage);
    }
}

function selectPage(selectedPage) {
    emit("page-changed", selectedPage);
}

</script>

<style lang="scss" scoped>

</style>
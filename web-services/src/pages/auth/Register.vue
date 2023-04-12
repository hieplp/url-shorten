<template>
	<div class="w-screen h-screen bg-gray-50">

		<div class="flex flex-col
                items-center justify-center
                h-screen
                px-6 py-8
                lg:py-0
                m-auto">

			<router-link class="flex items-center mb-6 text-2xl font-semibold text-blue-600" to="/">
				SHORTEN.IT
			</router-link>

			<div class="w-full
                  bg-white rounded-lg
                  shadow
                  md:mt-0
                  sm:max-w-md
                  xl:p-0">
				<div class="p-6 space-y-4 md:space-y-6 sm:p-8">

					<h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
						Register your account
					</h1>

					<form class="space-y-4 md:space-y-6">

						<div>
							<label class="block mb-2 text-sm font-medium text-gray-900" for="username">
								Your username
							</label>
							<input :disabled="isLoading"
							       class="bg-gray-50
                            border border-gray-300
                            text-gray-900 sm:text-sm rounded-lg
                            focus:outline-blue-600
                            block w-full p-2.5"
							       name="username"
							       placeholder="Username"
							       required type="text">
						</div>

						<div>
							<label class="block mb-2 text-sm font-medium text-gray-900"
							       for="password">
								Password
							</label>
							<input :disabled="isLoading"
							       class="bg-gray-50
                            border border-gray-300
                            text-gray-900 sm:text-sm rounded-lg
                            focus:outline-blue-600
                            block w-full p-2.5"
							       name="password"
							       placeholder="••••••••"
							       required="" type="password">
						</div>


						<div>
							<label class="block mb-2 text-sm font-medium text-gray-900"
							       for="confirm-password">
								Confirm Password
							</label>
							<input :disabled="isLoading"
							       class="bg-gray-50
                            border border-gray-300
                            text-gray-900 sm:text-sm rounded-lg
                            focus:outline-blue-600
                            block w-full p-2.5"
							       name="confirm-password"
							       placeholder="••••••••"
							       required="" type="password">
						</div>

						<LoadingButton :is-loading="isLoading" text="Sign up" @click="register"/>

						<p class="text-sm text-center font-light text-gray-500 dark:text-gray-400">
							Already have an account?
							<router-link class="font-bold text-blue-600 hover:underline"
							             to="/login">
								Sign in
							</router-link>
						</p>
					</form>
				</div>
			</div>
		</div>

	</div>
</template>

<script lang="ts" setup>
import LoadingButton from "../../components/button/LoadingButton.vue";
import {ref} from "vue";

const isLoading = ref(false);

function register() {
    isLoading.value = true;
    setTimeout(() => {
        isLoading.value = false;
    }, 2000);
}

</script>

<style lang="scss" scoped>

</style>
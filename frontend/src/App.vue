<script setup lang="ts">
import {Close, Toast, Toasts} from "agnostic-vue";
import "agnostic-vue/dist/common.min.css";
import "agnostic-vue/dist/index.css";
// import { faCheckCircle, faTimesCircle } from "@fortawesome/free-solid-svg-icons";
import {activeToasts} from "./ts/toasts.ts";
import NavBar from "./components/NavBar.vue";
import Footer from "./components/Footer.vue";

// Function to trigger a success toast
// const showSuccessToast = () => {
//   createToast({
//     title: "Success!",
//     message: "Operation completed successfully.",
//     type: "success",
//     icon: faCheckCircle,
//     timeout: 3,
//   });
// };
//
// // Function to trigger an error toast
// const showErrorToast = () => {
//   createToast({
//     title: "Error!",
//     message: "Something went wrong.",
//     type: "error",
//     icon: faTimesCircle,
//     timeout: 3,
//   });
// };
</script>

<template>
  <div id="app" class="layout">
    <!-- Buttons to trigger toasts -->
    <NavBar></NavBar>

    <div class="main">7
      <RouterView/>
    </div>

    <Footer></Footer>

  </div>

  <!-- Toasts Display -->
  <Toasts vertical-position="top" horizontal-position="end" class="custom-toast-container">
    <template v-for="toast of activeToasts" :key="toast.key">
      <Toast :type="toast.type" class="alert alert-border-left alert-info">
        <div class="flex-fill flex flex-column">
          <div class="flex">
            <h3 class="flex-fill">{{ toast.title }}</h3>
            <Close @click="toast.close()"/>
          </div>
          <div class="flex">
            <font-awesome-icon :icon="toast.icon" size="xl" class="mie8 pbs2"></font-awesome-icon>
            <div class="flex-fill">{{ toast.message }}</div>
          </div>
        </div>
      </Toast>
      <div class="mbe14"/>
    </template>
  </Toasts>

</template>


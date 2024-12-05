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
function toastClass({type}: { type: any }) {
  switch (type) {
    case 'success':
      return 'alert-success'; // Green
    case 'error':
      return 'alert-danger'; // Red
    case 'warning':
      return 'alert-warning'; // Yellow
    case 'info':
      return 'alert-info'; // Blue
    case 'dark':
      return 'alert-dark'; // Dark Gray
    default:
      return '';
  }
}
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

  <Toasts vertical-position="top" horizontal-position="end" class="custom-toast-container">
    <template v-for="toast of activeToasts" :key="toast.key">
      <Toast :type="toast.type" class="alert" :class="toastClass({type : toast.type})">
        <div class="toast-body">
          <h5 class="toast-title">{{ toast.title }}</h5>
          <div class="toast-message">{{ toast.message }}</div>
        </div>
        <Close @click="toast.close()" class="cursor-pointer"/>
      </Toast>
      <div class="mbe14"/>
    </template>
  </Toasts>




</template>

import './style.css'

import {createApp} from 'vue'
import App from './App.vue'


// fontawesome 5 icons
import {library} from '@fortawesome/fontawesome-svg-core'
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome'
import {fas} from '@fortawesome/free-solid-svg-icons'
// Import Font Awesome CSS
import '@fortawesome/fontawesome-free/css/all.min.css';

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

import { BootstrapVue3 } from "bootstrap-vue-3";

import router from './router'


import 'vue3-modal/dist/modal.css';

import { faHome, faUsers, faList, faUserPlus, faSignInAlt, faSignOutAlt, faCog, faStar, faInfoCircle, faEllipsisH,faClipboardList } from '@fortawesome/free-solid-svg-icons';
// Add icons to the library
library.add(faHome, faUsers, faList, faUserPlus, faSignInAlt, faSignOutAlt, faCog, faStar, faInfoCircle, faEllipsisH,faClipboardList);


library.add(fas);

const app = createApp(App)
// BootstrapVue for UI components and icons

app.use(BootstrapVue3);
app.use(router)
app.component("font-awesome-icon", FontAwesomeIcon)

app.mount('#app')

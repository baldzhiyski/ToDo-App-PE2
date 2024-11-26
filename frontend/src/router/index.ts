import {createRouter, createWebHistory} from 'vue-router'
import AssigneeView from "../views/AssigneeView.vue";
import MainPage from "../views/MainPage.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'home',
            component: MainPage, // Render MainPage for the home route
        },
        {
            path: '/assignees',
            name: 'assignees',
            component: AssigneeView
        }

    ]
})

export default router
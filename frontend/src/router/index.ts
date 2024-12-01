import {createRouter, createWebHistory} from 'vue-router'
import AssigneeView from "../views/AssigneeView.vue";
import MainPage from "../views/MainPage.vue";
import TodosView from "../views/TodosView.vue";

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
        },
        {
            path: '/todos',
            name: 'todos',
            component: TodosView
        }

    ]
})

export default router
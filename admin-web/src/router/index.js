import {createRouter, createWebHashHistory} from 'vue-router'


const routes = [
    {path: '/login', component: () => import('@/pages/sys-user/login.vue')},
    {path: '/index', component: () => import('@/pages/index.vue')},
]

export default createRouter({
    history: createWebHashHistory(),
    routes,
})

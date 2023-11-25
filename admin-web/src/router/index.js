import {createRouter, createWebHashHistory} from 'vue-router'


const routes = [
    {
        path: '/login',
        component: () => import('@/pages/sys-user/login.vue')
    },
    {
        path: '/home',
        component: () => import('@/pages/index.vue'),
        children: [
            {
                path: 'home',
                component: () => import('@/pages/home/home.vue')
            },
            {
                path: 'file',
                component: () => import('@/pages/file/list.vue')
            }
        ]
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});
export default router

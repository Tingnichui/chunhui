import {createRouter, createWebHashHistory} from 'vue-router'


const routes = [
    {
        path: '/login',
        component: () => import('@/pages/sys-user/login.vue')
    },
    {
        path: '/admin',
        component: () => import('@/pages/admin/index.vue'),
        children: [
            {
                path: 'resource/list',
                component: () => import('@/pages/admin/resource/list.vue')
            }
        ]
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});
export default router

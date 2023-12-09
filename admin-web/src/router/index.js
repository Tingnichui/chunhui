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
            },
            {
                path: 'menu/list',
                component: () => import('@/pages/admin/sys-menu/list.vue')
            },
            {
                path: 'sys-role/list',
                component: () => import('@/pages/admin/sys-role/list.vue')
            },
            {
                path: 'sys-permission/list',
                component: () => import('@/pages/admin/sys-permission/list.vue')
            },
            {
                path: 'sys-user/list',
                component: () => import('@/pages/admin/sys-user/list.vue')
            }
        ]
    },
    {
        path: '/home',
        component: () => import('@/pages/home/index.vue'),
        children: [
            {
                path: 'resource/list',
                component: () => import('@/pages/home/resource/list.vue')
            }
        ]
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});
export default router

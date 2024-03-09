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
                component: () => import('@/pages/admin/res-info/list.vue')
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
            },
            {
                path: 'jljs-class-record/list',
                component: () => import('@/pages/admin/jljs-class-record/list.vue')
            },
            {
                path: 'jljs-coach-info/list',
                component: () => import('@/pages/admin/jljs-coach-info/list.vue')
            },
            {
                path: 'jljs-contract-operate-record/list',
                component: () => import('@/pages/admin/jljs-contract-operate-record/list.vue')
            },
            {
                path: 'jljs-course-info/list',
                component: () => import('@/pages/admin/jljs-course-info/list.vue')
            },
            {
                path: 'jljs-member-info/list',
                component: () => import('@/pages/admin/jljs-member-info/list.vue')
            },
            {
                path: 'jljs_contract_info/list',
                component: () => import('@/pages/admin/jljs-contract-info/list.vue')
            },
            {
                path: 'jljs_home/index',
                component: () => import('@/pages/admin/jljs-home/index.vue')
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

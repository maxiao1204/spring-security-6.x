// @ts-nocheck
import { createRouter, createWebHistory } from 'vue-router'

import index from './components/index.vue'
import login from "./components/login.vue"

// 创建路由实例对象
const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', name:'index',component:index },
        { path: '/login', name:'login',component:login }
    ],
})

export default router

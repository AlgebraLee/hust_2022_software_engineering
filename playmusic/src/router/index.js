import {createRouter, createWebHistory} from 'vue-router'
import CommunityView from '@/views/CommunityView.vue'
import ExploreView from '@/views/ExploreView.vue'
import HomeView from '@/views/HomeView.vue'

const routes = [
    {
        path: '/community',
        name: 'community',
        component: CommunityView
    },
    {
        path: '/explore',
        name: 'explore',
        component: ExploreView
    },
    {
        path: '/',
        name: 'home',
        component: HomeView
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
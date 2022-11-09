import { createRouter, createWebHistory } from 'vue-router'
import ExploreView from "@/views/ExploreView";
import CommunityView from "@/views/CommunityView";
import HomeView from '@/views/HomeView.vue';

const routes = [
  {
    path: '/community',
    name: 'community',
    component: CommunityView,
    meta: {
      requestAuth: false,
    }
  },
	{
    path: '/explore',
    name: 'explore',
    component: ExploreView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requestAuth: false,
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router

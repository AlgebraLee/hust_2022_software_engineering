import HomeView from '@views/HomeView'
import ExploreView from '@views/ExploreView'
import CommunityView from "@/views/CommunityView"
import { createRouter, createWebHistory } from 'vue-router'



const routes = [
    {
      path: '/',
      name: 'home',
      component: HomeView,
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
      path: '/community',
      name: 'community',
      component: CommunityView,
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

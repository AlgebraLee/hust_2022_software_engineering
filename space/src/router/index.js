import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SpaceView from "@/views/SpaceView";
import LoginView from "@/views/LoginView";
import RegisterView from "@/views/RegisterView";
import store from '../store/index'

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
    path: '/space',
    name: 'space',
    component: SpaceView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView,
    meta: {
      requestAuth: false,
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})
router.beforeEach((to, from, next) => {

  let flag = 1;
  const jwt_token = localStorage.getItem("jwt_token");

  if (jwt_token) {
    store.commit("updateToken", jwt_token);
    store.dispatch("getInfo", {
      success() {
      },
      error() {
        alert("token无效，请重新登录！");
        router.push({ name: 'userLogin' });
      }
    })
  } else {
    flag = 2;
  }
  if (to.meta.requestAuth && !store.state.user.is_login) {
    if (flag === 1) {
      next();
    } else {
      alert("请先进行登录！");
      next({name: "userLogin"});
    }
  } else {
    next();
  }
})

export default router

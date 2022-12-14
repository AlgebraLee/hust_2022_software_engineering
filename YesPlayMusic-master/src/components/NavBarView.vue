<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <img src="../assets/musical-note.png" />
      <router-link class="navbar-brand" :to="{ name: 'home', params: {} }"
        >网易音乐</router-link
      >
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link
              class="nav-link"
              aria-current="page"
              :to="{ name: 'home', params: {} }"
              >首页</router-link
            >
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'home', params: {} }"
              >音乐馆</router-link
            >
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'space', params: {} }"
              >社区</router-link
            >
          </li>
        </ul>
        <span class="navbar-text">
          <ul
            class="navbar-nav me-auto mb-2 mb-lg-0"
            v-if="!$store.state.user.is_login"
          >
            <li class="nav-item">
              <router-link class="nav-link" :to="{ name: 'login', params: {} }"
                >登陆</router-link
              >
            </li>
            <li class="nav-item">
              <router-link
                class="nav-link"
                :to="{ name: 'register', params: {} }"
                >注册</router-link
              >
            </li>
          </ul>
          <ul class="navbar-nav me-auto mb-2 mb-lg-0" v-else>
            <el-dropdown>
              <span class="el-dropdown-link" style="font-size: 18px">
                <img
                  :src="$store.state.user.photo"
                  style="border-radius: 50%"
                  class="img-fluid"
                />
                <el-icon class="el-icon--right">
                  <arrow-down />
                </el-icon>
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item
                    >{{
                      $store.state.user.username
                    }}的个人中心</el-dropdown-item
                  >
                  <el-dropdown-item>关于</el-dropdown-item>
                  <el-dropdown-item divided @click="logout"
                    >退出登陆</el-dropdown-item
                  >
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </ul>
        </span>
      </div>
    </div>
  </nav>
</template>

<script>
import { useStore } from 'vuex';
import router from '@/router/index';

export default {
  name: 'NavBarView',
  setup() {
    const store = useStore();
    const logout = () => {
      store.dispatch('logout');
      router.push({ name: 'login' });
    };
    return {
      logout,
    };
  },
};
</script>

<style scoped>
img {
  height: 80px;
  width: 80px;
  margin-right: 20px;
}
.el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
</style>

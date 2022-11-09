<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <img src="@/assets/musical-note.png">
      <router-link class="navbar-brand" :to="{name: 'home',params: {}}">网易音乐</router-link>
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link class="nav-link" aria-current="page" :to="{name: 'home',params: {}}">首页</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{name: 'home',params: {}}">音乐馆</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{name: 'space',params: {}}">社区</router-link>
          </li>
        </ul>
        <span class="navbar-text">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0" v-if="!$store.state.user.is_login">
            <li class="nav-item">
              <router-link class="nav-link" :to="{name: 'login',params: {}}">登陆</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" :to="{name: 'register',params: {}}">注册</router-link>
            </li>
          </ul>
        </span>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "NavBar",
}
</script>

<style scoped>
img{
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

<template>
  <div id="nav">
    <el-row>
      <el-col>
        <div id="backward">
          <img src="@/assets/icon_返回.svg" alt="">
        </div>
        <div id="forward">
          <img src="@/assets/icon_进入.svg" alt="">
        </div>
      </el-col>
      <el-col>
        <router-link :to="{path: '/home'}">我的</router-link>
      </el-col>
      <el-col>
        <router-link :to="{path:'/explore'}">发现</router-link>
      </el-col>
      <el-col>
        <router-link :to="{path:'/community'}">社区</router-link>
      </el-col>
      <el-col>
        <div>
          <img src="@/assets/icon_搜索.svg" alt="">
          <input type="search">
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'NavBar',

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#nav {
  height: 64px;
  background-color:azure;
}
</style>

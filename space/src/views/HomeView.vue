<template>
  <ContentView>
    音乐首页
  </ContentView>
  <router-view/>
</template>

<script>
import ContentView from "@/components/ContentView";

export default {
  name: 'HomeView',
  components: {
    ContentView,
  }
}
</script>

<style scoped>

</style>

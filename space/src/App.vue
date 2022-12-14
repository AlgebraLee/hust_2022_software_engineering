<template>
  <NavBarView/>
  <router-view :key="$route.fullPath"/>
</template>

<script>
import 'bootstrap/dist/js/bootstrap'
import 'bootstrap/dist/css/bootstrap.css'
import NavBarView from "@/components/NavBarView"

export default {
  name: "APP",
  components: {
    NavBarView,
  },
}

</script>

<style scoped>

</style>

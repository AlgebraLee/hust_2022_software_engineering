<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col">
        <div class="card">
          <div class="card-body">
            <slot></slot>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ContentView',
};
</script>

<style scoped>
card {
  width: 100%;
}
div.container {
  margin-top: 20px;
}
.card-body {
  text-align: center;
}
</style>

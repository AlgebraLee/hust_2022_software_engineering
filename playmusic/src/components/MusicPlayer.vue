<template>
  <div id="player">
    <audio :src="getMP3(405998841)" id="player"></audio>
    <p>this is player</p>
  </div>
</template>

<script>
import getMP3 from '@/api/track'
export default {
  name: 'MusicPlayer',
  props: {
    
  },
  data() {
    return  {
    }
  },
  methods: {
    getMP3
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #player {
    align-self: center;
    height: 160px;
    width: 90px;
  }
</style>

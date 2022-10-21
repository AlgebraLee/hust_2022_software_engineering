<template>
  <div id="player">
    <audio :src="getMP3(id)" id="player"></audio>
    <p>this is player</p>
  </div>
</template>

<script>
import getMP3 from '@/api/track.js'
export default {
  name: 'MusicPlayer',
  props: {
    
  },
  data() {
    return  {
      id: 
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
    background-color: aliceblue;
  }
</style>

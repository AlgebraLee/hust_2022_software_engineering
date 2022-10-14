<template>
    <div id="app">
        <Scrollbar/>
        <Navbar/>
        <main ref="main">
            
        </main>
        <Player/>
        <Toast/>
        <ModalAddTrackToPlaylist/>
        <ModalNewPlaylist/>
        <Lyrics/>
    </div>
</template>

<script>
import Scrollbar from './components/Scrollbar.vue';
import Navbar from './components/Navbar.vue';
import Player from './components/Player.vue';
import Toast from './components/Toast.vue';
import ModalAddTrackToPlaylist from './components/ModalAddTrackToPlaylist.vue';
import ModalNewPlaylist from './components/ModalNewPlaylist.vue';
import Lyrics from './views/lyrics.vue';

export default {
    name: 'App',
    components: {
        Scrollbar,
        Navbar,
        Player,
        Toast,
        ModalAddTrackToPlaylist,
        ModalNewPlaylist,
        Lyrics
    },
    data() {

    },
    computed: {

    },
    methods: {
        
    },
}
</script>

<style lang="scss">
#app {
    width: 100%;
    transition: all 0.4s;
}

main {
    position: fixed;
    top: 0;
    bottom: 0;
    right: 0;
    left: 0;
    overflow: auto;
    padding: 64px 10vw 96px 10vw;
    box-sizing: border-box;
    scrollbar-width: none; // firefox
}
</style>
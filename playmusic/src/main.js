import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router/index.js'
import ElementUI from 'element-ui'

createApp(App).use(router).use(ElementUI).mount('#app')

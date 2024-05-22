import './assets/style.css'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-icons/font/bootstrap-icons.css"
import "bootstrap/dist/js/bootstrap.min.js"

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import Chat from 'vue3-beautiful-chat'
import router from './router'
import VueDaumPostcode from "vue-daum-postcode"
import DaumPostcode from "vuejs-daum-postcode"

const app = createApp(App)
const pinia = createPinia()

pinia.use(piniaPluginPersistedstate)

app.use(pinia)
app.use(router)
app.use(Chat)
app.use(VueDaumPostcode)
app.use(DaumPostcode)

app.mount('#app')
import HomeView from '@/views/HomeView.vue'
import Commu from '@/components/Commu.vue'
import Shop from '@/components/Shop.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path : '/',
      name : 'home',
      component : HomeView,
      children : [
        {
          path : '',
          name : 'commu',
          component : Commu
        },
        {
          path : 'shop',
          name : 'shop',
          component : Shop
        } 
      ]
    }
  ]
})

export default router

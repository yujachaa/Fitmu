import HomeView from '@/views/HomeView.vue'
import Commu from '@/components/Commu.vue'
import Shop from '@/components/Shop.vue'
import LoginView from '@/views/LoginView.vue'
import RegistView from '@/views/RegistView.vue'

import { createRouter, createWebHistory } from 'vue-router'
import MypageView from '@/views/MypageView.vue'
import Scrapbook from '@/components/Scrapbook.vue'
import Zzim from '@/components/Zzim.vue'
import StoryRegistView from '@/views/StoryRegistView.vue'
import ProductRegistView from '@/views/ProductRegistView.vue'
import Profile from '@/components/Profile.vue'
import Setting from '@/components/Setting.vue'
import KakaoLoginView from '@/views/KakaoLoginView.vue'
import ProductDetail from '@/components/ProductDetail.vue'
import ProductInfo from '@/components/ProductInfo.vue'
import ProductReview from '@/components/ProductReview.vue'
import ProductInquiry from '@/components/ProductInquiry.vue'
import SellerRegist from '@/components/SellerRegist.vue'
import StoryDetail from '@/components/StoryDetail.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path : '/',
      name : 'home',
      component : HomeView,
      children : [
        {
          path : '/',
          name : 'commu',
          component : Commu,
          children:[
            {
              path : '/storydetail/:storyId',
              name:'storyDetail',
              component:StoryDetail
            }
          ]
        },
        {
          path : 'shop',
          name : 'shop',
          component : Shop
        },
        {
          path : '/productdetail/:productId',
          name : 'productDetail',
          component : ProductDetail,
          children : [{
            path : '/productdetail/:productId/info',
            name : 'productinfo',
            component : ProductInfo
          },
          {
            path : '/productdetail/:productId/review',
            name : 'productreview',
            component : ProductReview
          },
          {
            path : 'productdetail/:productId/inquiry',
            name : 'productinquiry',
            component : ProductInquiry
          }]
        }
      ]
    },
    {
      path : "/login",
      name : "login",
      component : LoginView
    },
    {
      path : "/regist",
      name : "regist",
      component : RegistView
    },
    {
      path : '/mypage',
      name : "mypage",
      component : MypageView,
      children:[
        {
          path:'',
          name:"profile",
          component:Profile
        },
        {
          path:"/scrapbook",
          name:"scrapbook",
          component: Scrapbook
        },
        {
          path:"/zzim",
          name:"zzim",
          component: Zzim
        },
        {
          path:"/setting",
          name:"setting",
          component: Setting
        },
        {
          path:"/seller-regist",
          name : "seller-regist",
          component: SellerRegist
        }
      ]
    },
    {
      path : "/storyregist",
      name : "storyregist",
      component : StoryRegistView,
    },
    {
      path : "/productregist",
      name : "productRegist",
      component : ProductRegistView,
    },
    {
      path :"/kakaologin",
      name : "kakaoLogin",
      component : KakaoLoginView,
    }
  ]
})

export default router

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
import OrderView from '@/views/OrderView.vue'
import Popular from '@/components/commu/Popular.vue'
import HomeTraining from '@/components/commu/HomeTraining.vue'
import Running from '@/components/commu/Running.vue'
import Three from '@/components/commu/Three.vue'
import Tip from '@/components/commu/Tip.vue'
import Today from '@/components/commu/Today.vue'
import Clothes from '@/components/shop/Clothes.vue'
import Diet from '@/components/shop/Diet.vue'
import Protein from '@/components/shop/Protein.vue'
import MyOrder from '@/components/MyOrder.vue'
import MyReview from '@/components/MyReview.vue'
import MyInquiry from '@/components/MyInquiry.vue'

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
            path : '',
            name : 'productinfo',
            component : ProductInfo
          },
          {
            path : 'review',
            name : 'productreview',
            component : ProductReview
          },
          {
            path : 'inquiry',
            name : 'productinquiry',
            component : ProductInquiry
          }]
        },
        {
          path : '/storydetail/:storyId',
          name:'storyDetail',
          component:StoryDetail
        },
        {
          path:'popular',
          name:'popular',
          component:Popular
        },
        {
          path:'homeTraining',
          name:'homeTraining',
          component:HomeTraining
        },
        {
          path:'running',
          name:'running',
          component:Running
        },
        {
          path:'three',
          name:'three',
          component:Three
        },
        {
          path:'tip',
          name:'tip',
          component:Tip
        },
        {
          path:'today',
          name:'today',
          component:Today
        },
        {
          path:'clothes',
          name:'clothes',
          component:Clothes
        },
        {
          path:'diet',
          name:'diet',
          component:Diet
        },
        {
          path:'protein',
          name:'protein',
          component:Protein
        },
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
        },
        {
          path:"/my-order",
          name : "my-order",
          component : MyOrder,
        },
        {
          path : "/my-review",
          name : "my-review",
          component : MyReview,
        },
        {
          path : "/my-inquiry",
          name : "my-inquiry",
          component : MyInquiry,
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
    },
    {
      path : "/order/:productId/:quantity",
      name : "order",
      component : OrderView
    },

  ]
})

export default router

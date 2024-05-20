import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import { useRoute, useRouter } from 'vue-router'

const REST_SELLER_API = `http://localhost:8080/seller-api`

export const useSellerStore = defineStore('seller', () => {

  const route = useRoute();
  const router = useRouter();

  const sellerList = ref([])
  const getSellerList = function (){
    axios.get(REST_SELLER_API + "/seller")
    .then((res)=>{
      sellerList.value = res.data
    })
  }

  const registSeller = function(seller){
    axios.post(REST_SELLER_API + "/regist", seller)
    .then((res)=>{
      sessionStorage.setItem("role", "s")
      router.replace({name : 'profile'})
    })
    .catch((err) => {
      console.log(err)
    })
  }



  return {
    sellerList,
    getSellerList,
    registSeller,


  }
}, { persist: true })

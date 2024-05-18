import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import {useRoute, useRouter} from 'vue-router'
import axios from 'axios'

export const useProductStore = defineStore('product', () => {

  const route = useRoute()
  const router = useRouter()
  
  const registProduct = function(product){
    const userId = sessionStorage.getItem("loginUser")
    axios.get("http://localhost:8080/seller-api/" + userId, product)
     .then((response)=> {
        product.sellerId = response.data.sellerId
        product.userId = userId
        return axios.post("http://localhost:8080/product-api/regist", product)
      })
      .then((response)=>{
        console.log(response.data)
        
      })
  }
  return {
    registProduct,

   }
}, {persist : true})
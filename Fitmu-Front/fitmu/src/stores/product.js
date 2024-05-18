import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import {useRoute, useRouter} from 'vue-router'
import axios from 'axios'

export const useProductStore = defineStore('product', () => {

  const route = useRoute()
  const router = useRouter()

  
  const registProduct = function(product, files){
    console.log(files)
    const userId = sessionStorage.getItem("loginUser")
    const productId = ref(0);
    const sellerId = ref(0);
    axios.get("http://localhost:8080/seller-api/" + userId, product)
     .then((response)=> {
        sellerId.value = response.data.sellerId
        product.sellerId = sellerId.value
        product.userId = userId
        return axios.post("http://localhost:8080/product-api/regist", product)
      })
      .then((response)=>{
        productId.value = response.data
        const formData = new FormData()
        for (let i = 0; i < files.length; i++) {
          formData.append("files", files[i])
        }
        console.log(formData)
        return axios.post("http://localhost:8080/product-api/upload", formData)
      })
      .then((response)=>{
        console.log(response.data)
        const uploadPromises = response.data.map((fileName) => {
          const productImage = {
              imageId: 0,
              sellerId: sellerId.value,
              productId: productId.value,
              fileName: fileName
          }
          return axios.post("http://localhost:8080/product-api/imageUpload", productImage)
        })
        return Promise.all(uploadPromises)
      })
  }
  return {
    registProduct,
   }
}, {persist : true})
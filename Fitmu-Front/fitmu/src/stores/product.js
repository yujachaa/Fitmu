import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import {useRoute, useRouter} from 'vue-router'
import axios from 'axios'

export const useProductStore = defineStore('product', () => {

  const route = useRoute()
  const router = useRouter()
  const product = ref({})
  const productImages = ref([])
  const products = ref([])
  const reviews = ref([])
  const users = ref([])

  const help = function(){
    reviews.value = reviews.value.sort((a,b)=> b.liked - a.liked)
  }

  const late = function(){
    reviews.value = reviews.value.sort((a,b) => b.reviewId - a.reviewId)
  }

  const registReview = function(review){
    axios.post("http://localhost:8080/review-api/regist", review)
    .then((response)=>{
      router.go()
    })
  }
  const getUsers = function(){
    axios.get("http://localhost:8080/user-api/")
    .then((response)=>{
      users.value = response.data
    })
  }

  const getProduct = function(productId){
    axios.get("http://localhost:8080/product-api/" + productId)
    .then((response)=>{
      console.log(response.data)
      product.value = response.data
    })
  }

  const reviewLike = function(reviewId){
    axios.put("http://localhost:8080/review-api/like/" + reviewId)
    .then((res)=>{
      return axios.get("http://localhost:8080/review-api/review/" + product.value.productId)
    })
    .then((response)=>{
      window.location.reload()
    })
  }

  const getProductImages = function(productId){
    axios.get("http://localhost:8080/product-api/image/" + productId)
    .then((response)=>{
      console.log(response.data)
      productImages.value = response.data
    })
  }
  
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

  const getProductReviews = function(productId){
    axios.get("http://localhost:8080/review-api/review/" + productId)
    .then((response)=>{
      reviews.value = response.data
    })
  }
  return {
    registProduct,
    getProduct,
    getProductImages,
    product,
    productImages,
    reviews,
    getProductReviews,
    getUsers,
    users,
    reviewLike,
    help,
    late,
    registReview,
   }
}, {persist : true})
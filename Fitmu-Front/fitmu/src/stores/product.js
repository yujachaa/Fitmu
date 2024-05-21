import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import {useRoute, useRouter} from 'vue-router'
import axios from 'axios'

export const useProductStore = defineStore('product', () => {

  const route = useRoute()
  const router = useRouter()
  const product = ref({})
  const productImages = ref([])
  const productAllImages = ref([])
  const products = ref([])
  const reviews = ref([])
  const users = ref([])
  const inquirys = ref([])
  const addresses = ref([])
  const defaultAddress = ref(null)
  const searchProducts = ref([])
  const selectedProduct = ref([])
  const productLike = ref([])


  const YesProduct = function(id){
    axios.post("http://localhost:8080/product-api/scrap/" + id + "/user/" + sessionStorage.getItem("loginUser"))
  }

  const NoProduct = function(id){
    axios.delete("http://localhost:8080/product-api/scrap/" + id + "/user/" + sessionStorage.getItem("loginUser"))
  }

  const getProductLike = function(){
    axios.get("http://localhost:8080/user-api/user/" + sessionStorage.getItem("loginUser") + "/product-scrap").
    then((response)=>{
      productLike.value = response.data
    })
  }

  const getProducts = function(){
    axios.get("http://localhost:8080/product-api/product")
   .then((response)=>{
    products.value = response.data
   })
  }

  const getProductALLImages = function(){
    axios.get("http://localhost:8080/product-api/image")
    .then((response)=>{
      productAllImages.value = response.data
    })
  }

  const searchProductforTag = function(word){
    let searchCondition = {
      'key' : 'name',
      'word' : word,
      'orderBy' : null,
      'orderByDir' : null
    }
    axios.get("http://localhost:8080/product-api/search", {
      params: searchCondition
    })
    .then((response)=>{
      searchProducts.value = response.data
    })
  }

  const addressDelete = function(id){
    axios.delete("http://localhost:8080/address-api/address/" + id)
   .then((response)=>{
    router.go()
   })
  }

  const deleteAddress = function(){
    defaultAddress.value = null
  }

  const registAddress = function(address){
    axios.post("http://localhost:8080/address-api/address/" + sessionStorage.getItem("loginUser"), address)
    .then((response)=>{
      router.go()
    })
  }

  const getAddress = function(id){
    axios.get("http://localhost:8080/address-api/address/user/" + id)
    .then((response)=>{
      addresses.value = response.data
    })
  }

  const getDefaultAddress = function(id){
    axios.get("http://localhost:8080/address-api/address/default/" + id)
    .then((response)=>{
      defaultAddress.value = response.data
    })
  }

  const getProductInquiry = function(productId){
    axios.get("http://localhost:8080/inquiry-api/product/" + route.params.productId)
    .then((response)=>{
      inquirys.value = response.data
    })
  }

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

  const registInquiry = function(inquiry){
    axios.post("http://localhost:8080/inquiry-api/regist", inquiry)
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

  //세일상품리스트 가져오기
  const saleList = ref([])
  const getSaleList = function(){
    axios.get("http://localhost:8080/product-api/discount/0")
    .then((res)=>{
      saleList.value = res.data
    })
  }
  
  return {
    deleteAddress,
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
    getProductInquiry,
    inquirys,
    registInquiry,
    getAddress,
    getDefaultAddress,
    addresses,
    defaultAddress,
    registAddress,
    addressDelete,
    searchProductforTag,
    searchProducts,
    products,
    getProductALLImages,
    productAllImages,
    selectedProduct,
    getProducts,
    getProductLike,
    productLike,
    YesProduct,
    NoProduct,
    getSaleList,
    saleList,
   }
}, {persist : true})
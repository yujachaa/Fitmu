<template>

  <div class="container">
    <div class="section">

      <div class="section-title">
        <div class="small-title">
          <h4>인기 상품 모아보기💫</h4>
          <span>핫한 리뷰를 확인해보세요</span>
        </div>
      </div>
      <div class="total-number">
        <span class="total-number">전체 </span> <span v-if="totalProductLength">{{
          totalProductLength.toLocaleString('ko-KR') }}</span>
      </div>

      <div class="popular d-flex justify-content-between">
        <!-- v-for 넣기 -->
        <div v-for="product in popularProductList" :key="product">
          <div class="popular-pic">
            <img v-if="getProductMainImage(product.productId)" class="pic"
              :src="`src/assets/image/product/${getProductMainImage(product.productId)}`" alt="이미지"
              @click="productDetail(product.productId)">
          </div>
          <div class="product-info">
            <div class="brand">
              {{ product.brand }}
            </div>
            <div class="product-name">
              {{ product.name }}
            </div>
            <div class="price-box">
              <span class="discount">
                {{ product.discountRate }}%
              </span>
              <span class="price">
                {{ product.specialPrice.toLocaleString('ko-KR') }}
              </span>
            </div>
            <div>
              ⭐<span class="rating" v-if="product.ratingCnt > 0">
                {{ (product.ratingSum / product.ratingCnt).toFixed(1) }}
              </span>
              <span class="rating" v-else>0</span>
              <span class="review"> 리뷰 {{ product.ratingCnt }}</span>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useProductStore } from '@/stores/product'


const router = useRouter()
const productStore = useProductStore()

productStore.getProductALLImages()
productStore.getProducts()

const category = computed(() => {
  return productStore.category
})

const popularProductList = computed(() => {
  return productStore.popularProductList
})

const totalProductLength = computed(() => {
  return productStore.popularProductList.length
})


const getImageName = function (productId) {
  const files = productStore.productAllImages.filter((productImg) => productImg.productId == productId)
  if (files[0] == undefined)
    return ''
  return files[0].fileName
  // return (files != undefined) ? files[0].fileName : '';
}

const getProductMainImage = function (productId) {
  if (productStore.productAllImages.length > 0) {
    return getImageName(productId);
  }
  return '';
}

const productDetail = function (productId) {
  router.push({ name: 'productDetail', params: { 'productId': productId } })
}


</script>

<style scoped>
hr {
  margin: 0;
}

.popular-pic {
  width: 270px;
  height: 270px;
  border-radius: 8px;
  object-fit: cover;
  object-fit: cover;
  overflow: hidden;
}

.popular-pic>.pic {
  border-radius: 8px;
  object-fit: cover;
  width: 100%;
  height: 100%;
  transition: all 0.2s linear;
}

.popular-pic:hover .pic {
  width: 100%;
  height: 100%;
  transform: scale(1.1);
  cursor: pointer;
}

.sale-pic {
  width: 220px;
  height: 220px;
  border-radius: 8px;
  /* height: 400px; */
  object-fit: cover;
  overflow: hidden;
  /* border-radius: 5px; */
}

.sale-pic>.pic {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
  transition: all 0.1s linear;
}

.sale-pic:hover .pic {
  width: 100%;
  height: 100%;
  transform: scale(1.1);
  cursor: pointer;
}

.product-info {
  margin-top: 5px;
  margin-bottom: 15px;
}

.price-box {
  font-size: larger;
  margin-top: 5px;
}

.brand {
  font-size: smaller;
  color: gray;
}

.discount {
  color: #34C5F0;
  font-weight: bold;
}

.price {
  margin-left: 5px;
  font-weight: bold;
}

.rating {
  margin-right: 5px;
  font-weight: bold;
}

.review {
  margin-left: 5px;
  color: gray;
}


.carousel {
  text-align: start;
}

.carousel__slide {
  padding: 0;
}

.product-info {
  justify-content: start;
}


.popular {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
}


.container {
  width: 100%;
  display: flex;
  padding-left: 80px;
  padding-right: 80px;
}

.section {
  margin-bottom: 20px;
  width: 100%;
}

.pic_carousel__item {
  object-fit: cover;
  max-height: 400px;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.product_carousel__item {
  /* width: 100%;
    display: flex;
    flex-direction: column; */
  justify-content: start;
  align-items: start;
}

/* .product_carousel_pic {
    width: 100%;
    height: 100%;
    width: 220px;
    height: 220px;
    object-fit: cover;
    border-radius: 8px;
} */

.section-title {
  width: 100%;
  height: 50px;
  display: flex;
  margin-top: 30px;
  margin-bottom: 30px;
  justify-content: space-between;
  align-items: center;
}

.section-title a {
  font-size: 18px;
  font-weight: bold;
  color: #34C5F0;
}

.small-title>h4 {
  font-weight: bold;
}

.nav-btn {
  margin-top: 20px;
}

.radio-button {
  background-color: rgb(241, 241, 241);
  border-radius: 75px;
  margin-left: 10px;
}

.radio-button:hover {
  background-color: rgb(223, 223, 223);
}

.radio-button:active {
  background-color: rgb(54, 54, 54);
  color: white
}


.btn-check:checked+.btn,
.btn.active,
.btn.show,
.btn:first-child:active,
:not(.btn-check)+.btn:active {
  background-color: rgb(54, 54, 54);
  color: white
}

.total-number {
  margin-top: 20px;
  margin-bottom: 20px;
  margin-right: 10px;
}
</style>
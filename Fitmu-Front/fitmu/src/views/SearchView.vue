<template>
  <div class="container">
    <div class="test">
      <div class="what-story">
        <div class="small-title">
          <h4>커뮤니티</h4>
          <h4 v-if="totalStoryLength" class="commu-cnt">{{ totalStoryLength.toLocaleString('ko-KR') }}</h4>
        </div>
        <div>
          <a @click="goPopular">더보기</a>
        </div>
      </div>
      <div class="test2">
        <div class="one-pic" v-for="(story, index) in popularList.slice(0, 6)" :key="story">
          <div class="sub-img">
            <img class="subimg" :src="`src/assets/image/story/${story.image}`" alt="" @click="goDetail(story.storyId)">
            <div class="main-img-info3">
              <i id="book" :class="{ setBlue: isScrap(story.storyId) }" class="bi bi-bookmark-fill"
                @click="YesBook(story.storyId, story)"></i>
              <i id="book2" :class="{ setBlue: isScrap(story.storyId) }" class="bi bi-bookmark"
                @click="YesBook(story.storyId, story)"></i>
            </div>
          </div>
          <div class="infoo">
            <div class="font-bold">{{ story.title }}</div>
            <div class="profile-info">
              <div class="profile-image">
                <img class="story-profile-img" src="@/assets/image/profile.png" alt="댓글프로필이미지">
              </div>
              <div class="user-nickname">
                <div v-if="popularNick(index)">{{ popularNick(index) }}</div>
              </div>
            </div>
            <div class="story-info-box">
              <span class="story-info">스크랩</span>
              <span class="story-info" v-if="scrapCntList > 0">{{ scrapCntList[index].toLocaleString('ko-KR') }}</span>
              •
              <span class="story-info">조회</span>
              <span class="story-info">{{ story.viewCnt.toLocaleString('ko-KR') }}</span>
            </div>
          </div>
        </div>
      </div>

      <hr>

      <div class="section">
        <div class="what-story">
          <div class="small-title">
            <h4>쇼핑</h4>
            <h4 v-if="totalProductLength" class="commu-cnt">{{
              totalProductLength.toLocaleString('ko-KR') }}</h4>
          </div>
          <div>
            <a @click="goPopular">더보기</a>
          </div>
        </div>


        <div class="popular d-flex justify-content-between">
          <!-- v-for 넣기 -->
          <div v-for="product in categoryList.slice(0,8)" :key="product">
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
  </div>
</template>

<script setup>
import { useStoryStore } from '@/stores/story';
import { useUserStore } from '@/stores/user';
import { ref, onBeforeMount, onMounted, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { useProductStore } from '@/stores/product'


const productStore = useProductStore()
const storyStore = useStoryStore()
const userStore = useUserStore()
const router = useRouter()

productStore.getProductALLImages()
userStore.getUserList()
storyStore.getStoryScrap()
storyStore.getCategoryScrapCntList("1")


const productResult = computed(()=>{
    return productStore.searchList
})

const storyResult = computed(()=>{
    return storyStore.searchList
})


//여기서부터 확인하기

const scrapCntList = computed(() => {
  return storyStore.scrapCntList
})

const totalStoryLength = computed(() => {
  return storyStore.categoryStoryList.length
})

const storyScrap = computed(() => {
  return storyStore.storyScrap
})


const YesBook = function (id, story) {
  if (isScrap(id)) {
    let index = storyScrap.value.findIndex((scrap) => scrap.storyId == id)
    storyScrap.value.splice(index, 1)
    storyStore.NoBook(id)
  } else {
    console.log(storyScrap.value)
    storyScrap.value.push(story)
    storyStore.YesBook(id)
  }
}

const isScrap = function (id) {
  for (let i = 0; i < storyScrap.value.length; i++) {
    if (storyScrap.value[i].storyId == id) {
      return true
    }
  }
  return false
}

const popularList = computed(() => {
  return storyStore.popularList
})

const getUserNick = (userId) => {
  const user = userStore.userList.find(user => user.userId === userId);
  return user ? user.nickname : '';
};


const popularNick = function (idx) {
  if (userStore.userList.length > 0) {
    return getUserNick(storyStore.popularList[idx].userId);
  }
  return '';
}

const goDetail = function (storyId) {
  router.push({ name: 'storyDetail', params: { 'storyId': storyId } })
}




const category = computed(() => {
  return productStore.category
})

const categoryList = computed(() => {
  return productStore.categoryList
})

const totalProductLength = computed(() => {
  return productStore.categoryList.length
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

const goPopular = function () {
  router.push({ name: 'reviewPopular' })
}
const goSale = function () {
  router.push({ name: 'sale' })
}

const changeCategory = function (category) {
  productStore.category = category
  productStore.getCategoryList(category)
}

</script>

<style scoped>
#book {
  position: absolute;
  opacity: 0.5;
}

.container {
  margin-top: 45px;
  width: 100%;
  display: flex;
  padding-left: 80px;
  padding-right: 80px;
  height: 100%;
  margin-bottom: 45px;
}

.test {
  width: 100%;
}

.test2 {
  margin-top: 20px;
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.what-story {
  width: 100%;
  height: 50px;
  display: flex;
  margin-top: 20px;
  justify-content: space-between;
  align-items: center;
}

.what-story a {
  font-size: 18px;
  font-weight: bold;
  color: #34C5F0;
}

.small-title {
  display: flex;
  margin-bottom: 10px;
}

.small-title>h4 {
  font-weight: bold;
}

.one-pic {
  display: flex;
  width: 32%;
  margin-bottom: 10px;
  flex-direction: column;
  justify-content: center;
}

.sub-img {
  position: relative;
  width: 100%;
  height: 250px;
  object-fit: cover;
  overflow: hidden;
  border-radius: 5px;
}

.sub-img>.subimg {
  width: 100%;
  height: 100%;
  border-radius: 5px;
  transition: all 0.1s linear;
}

.main-img-info3 {
  position: absolute;
  top: 85%;
  left: 88%;
  color: white;
}

.infoo {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 10px;
}

.font-bold {
  font-weight: bold;
}

.total-number {
  margin-right: 10px;
}

.story-profile-img {
  width: 20px;
  height: 20px;
  margin-right: 10px
}

.profile-info {
  display: flex;
}

.story-info-box {
  margin-top: 5px;
  font-size: 14px;
  color: rgb(122, 122, 122);
}

.story-info {
  margin: 0 2px;
}

.commu-cnt {
  margin-left: 10px;
  color: #34C5F0;
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
  margin-top: 20px;
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
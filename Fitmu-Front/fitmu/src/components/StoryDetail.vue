<template>

  <div class="total">
    <div class="container d-flex flex-column align-items-center align-items-stretch">
      <div class="title">
        <h2>{{ story.title }}</h2>
      </div>
      <div class="user-info d-flex flex-row justify-content-between">
        <div>
          <img class="profile-image" src="@/assets/image/profile.png" alt="프로필">
        </div>
        <div class="user-nickname">
          <h4>
            {{ getUserNick(story.userId) }}
          </h4>
        </div>
        <!-- 현재 사용자의 글이 아닐때만 팔로우 버튼 활성화 -->
        <div class="follow-button-box flex-grow-1" v-if="!isSameUSer">
          <button class="follow-btn" :class="{ followed: isFollowed }"
            @click="followToggle(story.userId)">{{ followMsg }}</button>
        </div>
      </div>
      <hr>
      <div class="story-box">
        <!-- 스토리 사진 -->
        <div>
          <img ref="img" class="story-img" :src="`/src/assets/image/story/${story.image}`" alt="스토리사진">
        </div>

        <!-- 스토리 내용 -->
        <div class="story-content">
          <p>{{ story.content }}</p>
        </div>

        <div class="story-info">
          <span>작성 </span><span>{{ story.createdDate }}</span>
          •
          <span>수정 </span><span>{{ story.modifiedDate }}</span>
          •
          <span>좋아요 </span><span>{{ story.liked.toLocaleString('ko-KR') }}</span>
          •
          <span>스크랩 </span><span>{{ storyScrapCnt.toLocaleString('ko-KR') }}</span>
          •
          <span>조회 </span><span>{{ story.viewCnt.toLocaleString('ko-KR') }}</span>
        </div>
        <!-- user-info 복붙해서 한번 더 넣기 -->
      </div>
      <hr>
      <div class="comment-box ">
        <div class="comment-count">
          <span>댓글</span>
          <span class="blue">{{ commentListLength }}</span>
        </div>
        <div class="comment-input d-flex align-items-center">
          <div class="">
            <img class="comment-profile-img" src="@/assets/image/profile.png" alt="댓글작성자프로필">
          </div>
          <div class="flex-fill">
            <input type="text" id="comment" class="input" placeholder="칭찬과 격려의 댓글은 작성자에게 큰 힘이 됩니다 :)"
              v-model="comment.content" @keyup.enter="registComment()">
          </div>
        </div>

        <!-- v-for 넣기 -->
        <div class="comment-list" v-for="comment in commentList" :key="comment">
          <div class="one-comment d-flex">
            <div class="">
              <img class="comment-profile-img" src="@/assets/image/profile.png" alt="댓글프로필이미지">
            </div>
            <div class="flex-fill">
              <div class="comment-nickname">
                <h4>{{ getUserNick(comment.userId) }}</h4>
              </div>
              <div>
                <p class="comment-content">{{ comment.content }}</p>
              </div>
              <div>
                <span class="comment-info">{{ comment.createdDate }}</span>
              </div>
            </div>
          </div>

        </div>
      </div>

    </div>
  </div>
  <div class="screen">
    <div class="to-top" @click="toTop">맨위로</div>

  </div>
  <div v-if="TAGS">
    <div v-for="(tag, index) in tags" :key="tag.tagId"
      :style="{ position: 'absolute', left: tag.left/100 * width + elementPositionX - 10 + 'px', top: tag.top/100 * height + elementPositionY - 10 + 'px' , zIndex:999}">
      <Popper :hover=true interactive disableClickAway>
        <button id="btn" ref="autobtn">+</button>
        <label class="btn" for="btn">+</label>
        <template #content>
          <div class="content2" @click = "goProductDetail(tag.productId)">
            <div class="garo">
              <img class="mini-img" :src="`/src/assets/image/product/${getProductImage(tag.productId)}`" alt="이미지">
              <div class="productInfo">
                <span class="productBrand">{{ getSelectedProductInfo(tag.productId).brand }}</span>
                <span class="productName">{{ getSelectedProductInfo(tag.productId).name }}</span>
              </div>
            </div>
          </div>
        </template>
      </Popper>
    </div>
  </div>
</template>

<script setup>
import { useMouseInElement, watchDebounced, useElementBounding } from '@vueuse/core'
import { ref, onMounted, computed, onBeforeMount } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useStoryStore } from '@/stores/story'
import { useUserStore } from '@/stores/user'
import { useProductStore } from '@/stores/product'
import { useCommentStore } from '@/stores/comment'
import { useWindowScroll } from '@vueuse/core'

const img = ref(null)
const { elementPositionX, elementPositionY } = useMouseInElement(img)

const route = useRoute()
const router = useRouter()
const storyStore = useStoryStore()
const productStore = useProductStore()
const userStore = useUserStore()
const commentStore = useCommentStore()
const { top, right, bottom, left, width, height } = useElementBounding(img)


//제일 먼저 실행되도록 setup으로 옮김
commentStore.getCommentList(route.params.storyId)
storyStore.getStory(route.params.storyId)
storyStore.getStoryScrapCount(route.params.storyId)
userStore.getUserList()
storyStore.getTags()
productStore.getProductALLImages()
productStore.getProducts()
// storyId.value = route.params.storyId




const story = computed(()=>{
  return storyStore.story
})
const storyScrapCnt = computed(()=>{
  return storyStore.storyScrapCnt
})

const commentListLength = computed(()=>{
  return commentStore.commentList.length
})

const commentList = computed(()=>{
  return commentStore.commentList
})

const tags = computed(()=>{
  return storyStore.tags
})

const products = computed(()=> {
  return productStore.products
})


const comment = ref({
  storyId: route.params.storyId,
  userId: sessionStorage.getItem("loginUser"),
  content: ""
})

const TAGS = computed(() => {
  return storyStore.tags.length > 0 ? true : false;
})

const getProductImage = function (productId) {
  if (productStore.productAllImages.filter(image => image.productId == productId).length == 0) {
    return "1.jpg";
  }
  return productStore.productAllImages.filter(image => image.productId == productId)[0].fileName
}

// const selectedProduct = computed(()=>{
//   return productStore.selectedProduct
// })

const getSelectedProductInfo = function (id) {
  if(products.value.length > 0){
    return products.value.find(product => product.productId === id)
  }else{
    return {brand : "오류", name : "오류"}
  }
}


// onBeforeMount(() => {

// })
// onMounted(() => {
// })

const registComment = function () {
  console.log(comment.value)
  commentStore.registComment(comment.value)
}

const getUserNick = function (userId) {
  return userStore.userList.filter((user) => user.userId == userId)[0].nickname
}

const isFollowed = ref(false)
const followMsg = ref("팔로우")

const followToggle = function (followingId) {
  if (isFollowed.value) {
    userStore.unfollow(sessionStorage.getItem("loginUser"), followingId)
    isFollowed.value = false
    followMsg.value = "팔로우"
  } else {
    userStore.follow(sessionStorage.getItem("loginUser"), followingId)
    isFollowed.value = true
    followMsg.value = "팔로잉"
  }
}

const isSameUSer = computed(() => {
  return storyStore.story.userId == sessionStorage.getItem('loginUser') ? true : false
})

const toTop = function () {
  window.scrollTo({
    top: 0,
    left: 0,
    behavior: "smooth"
  })
}

//스크롤

const { x, y } = useWindowScroll()
// console.log(x.value) // read current x scroll value
// y.value = 100 // scroll y to 100

const btnShow = ref(false)

const onScroll = function () {
  if (window.scrollY > 200) {
    btnShow.value = true
  } else {
    btnShow.value = false
  }
}

const goProductDetail = function(id){
  router.push({name : "productinfo", params : {productId : id}})
}

</script>
<script>
import { defineComponent, ref } from "vue"
import Popper from "vue3-popper";

export default defineComponent({
  name: 'App',
  components: {
    Popper
  },
})
</script>

<style scoped>
.content2{
  display: flex;
  width: 400px;
  height: 120px;
  padding: 20px;
  background-color: white;
  border: 1px solid #DBDDE0;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.content2:hover{
  display: flex;
  width: 400px;
  height: 120px;
  padding: 20px;
  background-color: #edeff2;
  border: 1px solid #edeff2;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  cursor: pointer;
}

#btn {
  display: none;
}

.btn {
  display: flex;
  border: 1px solid #34C5F0;
  width: 25px;
  height: 25px;
  justify-content: center;
  align-items: center;
  padding-left: 5px;
  padding-right: 6px;
  background-color: rgb(52, 197, 240 , 0.7);
  color: white;
  font-weight: bold;
  border-radius : 20px;
}
.mini-img {
  width: 80px;
  height: 80px;
  border-radius: 8px;
  margin-right: 15px;
}
.garo {
  display: flex;
}

.productBrand {
  color: #DBDDE0;
  font-weight: bold;
}

.productName {
  font-size: 18px;
  font-weight: bold;
}

.productInfo {
  display: flex;
  flex-direction: column;
}
.container {
  margin-top: 40px;
  width: 50%;
}

.total {
  position: absolute;
  width: 100%;
  z-index: 0;
}

.to-top {
  position: sticky;
  top: 100px;
  z-index: 999;

}

.screen {
  position: relative;
  height: 1000px;
  width: 100%;
  z-index: 0;
}

.area {
  position: absolute;
  top: 100%;
  left: 100%;
  width: 10000px;
  height: 10000px;
}

.scroll-btn {
  position: sticky;
  top: 10px;
  left: 10px;
  width: 100px;
  height: 100px;
  background: red;
  z-index: 999;
}

.title>h2 {
  font-weight: bold;
  margin-bottom: 20px;
}

.story-img {
  width: 100%;
}

.story-content {
  margin: 10px;
}

.one-comment {
  margin-bottom: 20px;
}

.comment-content {
  margin-bottom: 5px;
}

.comment-info {
  font-size: 14px;
  color: rgb(164, 164, 164);
}

.comment-input {
  width: 100%;
  margin-bottom: 20px;
}

.comment-profile-img {
  width: 25px;
  height: 25px;
  margin-right: 10px
}

.profile-image {
  margin: 5px;
  width: 30px;
  height: 30px;
}

.user-info {
  width: 100%;
  align-items: center;
}

.follow-button-box {
  display: flex
}

.follow-btn {
  margin-left: auto;

}

/* .user-nickname{
} */

.story-info {
  font-size: small;
  color: gray;
}

.story-info>span {
  margin: 3px;
}

.comment-box {
  width: 100%;
}

.comment-count {
  font-weight: bold;
  margin-bottom: 5px;
}

.comment-count>span {
  margin-left: 5px;
}

/* .one-comment-box {
  padding: 10px;
} */

.follow-btn {
  height: 40px;
  background-color: #34C5F0;
  color: white;
  font-weight: bold;
  border: none;
  border-radius: 5px;
}

.followed {
  border: 1px solid gray;
  background-color: white;
  color: gray;
}

.user-nickname {
  margin-right: auto;
}

.user-nickname>h4 {
  font-weight: bold;
}

.input {
  margin: 10px 5px;
  width: 100%;
  border: 1px solid rgb(185, 185, 185);
  border-radius: 4px;
  height: 40px;
  padding-left: 7px;
}

.input:hover {
  background-color: rgb(247, 247, 247);
}

.input::placeholder {
  color: rgb(176, 176, 176);
}

.input:focus {
  outline: 1px solid rgb(114, 202, 230, 0.5);
  border-color: #72cae6;
}

.blue {
  color: #34C5F0;
}

#aside {
  width: 10000px;
  height: 10000px;
  ;
}
</style>
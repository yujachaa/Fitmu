<template>

  <div class="total">
    <div class="width25">

    </div>
    <div class="container d-flex flex-column align-items-stretch">
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
        <div class="follow-button-box flex-grow-1" v-if="!isSameUser">
          <button class="follow-btn" :class="{ followed: isFollowed(story.userId) }" @click="followToggle(story)">{{
            followMsg}}</button>
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
            <input ref="input" type="text" id="comment" class="input" placeholder="칭찬과 격려의 댓글은 작성자에게 큰 힘이 됩니다 :)"
              v-model="comment.content" @keyup.enter="registComment()">
          </div>
        </div>
        <!-- v-for 넣기 -->
        <div v-if="commentList.length == 0">
          <p>첫번째 댓글의 주인공이 되어주세요!</p>
        </div>
        <div v-else class="comment">
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
    <div class="sticky-container">
      <div class="sidebar">
        <div class="circle" @click="YesBook(story.storyId, story)">
          <div class="bookmark">
            <i id="book" v-if="isScrap(story.storyId)" class="bi bi-bookmark-fill blue"></i>
            <i id="book2" v-else class="bi bi-bookmark"></i>
          </div>
        </div>
        <div class="circle" @click="goComment">
          <i class="bi bi-chat"></i>
        </div>
        <div class="circle" @click="goTop">
          <i class="bi bi-arrow-up"></i>
        </div>
      </div>
    </div>
  </div>
  <div v-if="TAGS">
    <div v-for="(tag, index) in tags" :key="tag.tagId"
      :style="{ position: 'absolute', left: tag.left / 100 * width + elementPositionX - 10 + 'px', top: tag.top / 100 * height + elementPositionY - 10 + 'px', zIndex: 999 }">
      <Popper :hover=true interactive disableClickAway>
        <button id="btn" ref="autobtn">+</button>
        <label class="btn" for="btn">+</label>
        <template #content>
          <div class="content2" @click="goProductDetail(tag.productId)">
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
import { useWindowScroll, useScroll } from '@vueuse/core'

const el = ref < HTMLElement | null > (null)
const { x, y } = useScroll(el, { behavior: "smooth" })

const goTop = function () {
  window.scrollTo({
    top: 0,
    behavior: 'smooth' // Smooth scroll animation
  });
}
const input = ref(null)

const goComment = function () {
  input.value.focus()
  input.value.scrollIntoView({ behavior: 'smooth', block: 'start' })

  setTimeout(function () {
    window.scrollBy(0, -100); // 입력창을 중간에서 위로 100px 이동
  }, 700)
}

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
storyStore.getStoryScrap()
userStore.getFollowing(sessionStorage.getItem("loginUser"));


const followingList = computed(() => {
  return userStore.followingList
})



const storyScrap = computed(() => {
  return storyStore.storyScrap
})

const story = computed(() => {
  return storyStore.story
})
const storyScrapCnt = computed(() => {
  return storyStore.storyScrapCnt
})

const commentListLength = computed(() => {
  return commentStore.commentList.length
})

const commentList = computed(() => {
  return commentStore.commentList
})

const tags = computed(() => {
  return storyStore.tags
})

const products = computed(() => {
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
  if (products.value.length > 0) {
    return products.value.find(product => product.productId === id)
  } else {
    return { brand: "오류", name: "오류" }
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

const isFollowed = function (followingId) {
  if (followingList.value.length > 0) {
    return followingList.value.some((user) => user.userId == followingId)
  }
  return false;
}

// const isFollowed = ref(false)
const followMsg = ref("팔로우")

const followToggle = function (followingStory) {
  if (isFollowed(followingStory.userId)) {
    let index = followingList.value.findIndex((user) => user.userId == followingStory.userId)
    followMsg.value = "팔로우"
    followingList.value.splice(index, 1)
    userStore.unfollow(sessionStorage.getItem("loginUser"), followingStory.userId)
  } else {
    followingList.value.push(followingStory)
    followMsg.value = "팔로잉"
    userStore.follow(sessionStorage.getItem("loginUser"), followingStory.userId)
  }
}

const isSameUser = computed(() => {
  return storyStore.story.userId == sessionStorage.getItem('loginUser') ? true : false
})

const goProductDetail = function (id) {
  router.push({ name: "productinfo", params: { productId: id } })
}

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
.comment {
  overflow-y: auto;
  height: 500px;
}

.circle {
  display: flex;
  width: 60px;
  height: 60px;
  border: 1px solid #C2C8CD;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 50px;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
  cursor: pointer;
}

.circle2 {
  display: flex;
  width: 60px;
  height: 60px;
  border: 1px solid #C2C8CD;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 50px;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
  cursor: pointer;
  position: fixed;
  top: 77%;
  left: 94%;
}

.circle:hover {
  display: flex;
  width: 60px;
  height: 60px;
  border: 1px solid #74787a;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 50px;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

.sidebar {
  display: flex;
  flex-direction: column;
  position: sticky;
  top: 200px;
  bottom: 50px;
  margin-left: 288px;
  margin-right: 27px;
  margin-top: 200px;
  height: 300px;

}

.sticky-container {
  width: 100%;
  height: 1600px;
}

.displayNone {
  width: 25%;
  justify-content: start;
}

.aside {
  display: flex;
  justify-content: start;
}

#sticky {
  position: sticky;
  width: 80px;
  top: 100px;
  z-index: 1000;
}

.content2 {
  display: flex;
  width: 400px;
  height: 120px;
  padding: 20px;
  background-color: white;
  border: 1px solid #DBDDE0;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.content2:hover {
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

.width25 {
  width: 100%;
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
  background-color: rgb(52, 197, 240, 0.7);
  color: white;
  font-weight: bold;
  border-radius: 20px;
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
  width: 2500px;
  height: 100%;
  margin-left: 0px;
  margin-right: 0px;
}

.total {
  width: 100%;
  height: 100%;
  display: flex;
}

.to-top {
  position: sticky;
  top: 100px;

}

.screen {
  height: 1000px;
  width: 100%;
}

.area {
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
  width: 75px;
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
  height: 100%;
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
</style>
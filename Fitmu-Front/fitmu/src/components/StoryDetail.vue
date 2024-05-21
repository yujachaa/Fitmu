<template>

  <div class="total">
    <div class="container d-flex flex-column align-items-center align-items-stretch">
      <div class="title">
        <h2>{{ storyStore.story.title }}</h2>
      </div>
      <div class="user-info d-flex flex-row justify-content-between">
        <div>
          <img class="profile-image" src="@/assets/image/profile.png" alt="프로필">
        </div>
        <div class="user-nickname">
          <h4>
            {{ getUserNick(storyStore.story.userId) }}
          </h4>
        </div>
        <!-- 현재 사용자의 글이 아닐때만 팔로우 버튼 활성화 -->
        <div class="follow-button-box flex-grow-1" v-if="!isSameUSer">
          <button class="follow-btn btn btn-primary" :class="{ followed: isFollowed }"
            @click="followToggle(storyStore.story.userId)">{{ followMsg }}</button>
        </div>
      </div>
      <hr>
      <div class="story-box">
        <!-- 스토리 사진 -->
        <div>
          <img class="story-img" :src="`/src/assets/image/story/${storyStore.story.image}`" alt="스토리사진">
        </div>

        <!-- 스토리 내용 -->
        <div class="story-content">
          <p>{{ storyStore.story.content }}</p>
        </div>

        <div class="story-info">
          <span>작성 </span><span>{{ storyStore.story.createdDate }}</span>
          •
          <span>수정 </span><span>{{ storyStore.story.modifiedDate }}</span>
          •
          <span>좋아요 </span><span>{{ storyStore.story.liked }}</span>
          •
          <span>스크랩 </span><span>{{ storyStore.storyScrapCnt }}</span>
          •
          <span>조회 </span><span>{{ storyStore.story.viewCnt }}</span>
        </div>
        <!-- user-info 복붙해서 한번 더 넣기 -->
      </div>
      <hr>
      <div class="comment-box ">
        <div class="comment-count">
          <span>댓글</span>
          <span class="blue">{{ commentStore.commentList.length }}</span>
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
        <div class="comment-list" v-for="comment in commentStore.commentList" :key="comment">
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
</template>

<script setup>
import { ref, onMounted, computed, onBeforeMount } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useStoryStore } from '@/stores/story'
import { useUserStore } from '@/stores/user'
import { useCommentStore } from '@/stores/comment'

const route = useRoute()
const router = useRouter()
const storyStore = useStoryStore()
const userStore = useUserStore()
const commentStore = useCommentStore()

const storyId = ref()
const story = ref({})

const comment = ref({
  storyId: route.params.storyId,
  userId: sessionStorage.getItem("loginUser"),
  content: ""
})

// onMounted(() => {
//   commentStore.getCommentList(route.params.storyId)
//   storyId.value = route.params.storyId
//   storyStore.getStory(route.params.storyId)
//   storyStore.getStoryScrapCount(route.params.storyId)
//   userStore.getUserList()
// })
onBeforeMount(() => {
  commentStore.getCommentList(route.params.storyId)
  storyId.value = route.params.storyId
  storyStore.getStory(route.params.storyId)
  storyStore.getStoryScrapCount(route.params.storyId)
  userStore.getUserList()
})

const registComment = function () {
  console.log(comment.value)
  commentStore.registComment(comment.value)
}

const getUserNick = function (userId) {
  console.log(userId)
  console.log(userStore.userList.filter((user)=> user.userId == userId)[0])
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
import { useWindowScroll } from '@vueuse/core'

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

</script>

<style scoped>
.container {
  margin-top: 40px;
  width: 50%;
}
.total{
  position: absolute;
  width: 100%;
  z-index: 1;
}

.to-top {
  position: sticky;
  top: 100px;
  z-index: 999;

}

.screen{
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
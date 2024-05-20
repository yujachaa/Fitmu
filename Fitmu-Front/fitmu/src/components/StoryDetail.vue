<template>
  <div>
    <div class="container d-flex flex-column align-items-center">
      <div class="title">
        <h1>{{ storyStore.story.title }}</h1>
      </div>
      <div class="user-info d-flex flex-row justify-content-between">
        <div>
          <img class="profile-image" src="@/assets/image/profile.png" alt="프로필">
        </div>
        <div class="user-nickname">
          유저닉네임
        </div>
        <div class="follow-button-box flex-grow-1">
          <button class="follow-btn btn btn-primary">팔로우</button>
        </div>
      </div>

      <div class="story-box">
        <!-- 스토리 사진 -->
        <div class="story-img">
          <img :src="`/src/assets/image/story/${storyStore.story.image}`" alt="스토리사진">
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

      <div class="comment-box ">
        <div class="comment-count">
          <span>댓글</span>
          <span class="blue">{{ }}</span>
        </div>
        <div class="comment-input">
          <div class="comment-profile-img">
            <img src="" alt="댓글프로필">
          </div>
          <div class="input">
            <input type="text" class="form-control" placeholder="칭찬과 격려의 댓글은 작성자에게 큰 힘이 됩니다 :)" v-model="comment.content" @keyup.enter="registComment">
          </div>
        </div>

        <!-- v-for 넣기 -->
        <div class="comment-list" v-for="comment in commentStore.commentList" :key="comment">
          <div class="one-comment-box">
            <div class="comment-profile-img">
              <img src="" alt="댓글프로필">
            </div>
            <div class="one-comment">
              <div class="comment-nickname">
                <h4>{{}}</h4>
              </div>
              <div class="comment-content">
                <p>리뷰내용내용내용이요~~~~~</p>
              </div>
              <div class="comment-info">
                <span>작성시간</span>
              </div>
            </div>
          </div>

        </div>
      </div>

    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
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

onMounted(() => {
  storyId.value = route.params.storyId
  storyStore.getStory(route.params.storyId)
  storyStore.getStoryScrapCount(route.params.storyId)
  commentStore.getCommentList(route.params.storyId)
  userStore.getUserList()
})

const registComment = function(){
  commentStore.registComment(comment.value)
}



// const getCommentNickname = computed((userId)=>{
//   const commentUser = userStore.userList.find((user) => user.userId == userId)

//     return commenUser.nickname
// })

</script>

<style scoped>
.container {
  width: 50%;
}

.profile-image {
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
  margin: 2px;
}

.comment-box {
  width: 100%;
}

.comment-count {
  font-weight: bold;
}
</style>
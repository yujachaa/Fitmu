<template>
  <div class="container top ">
    <div class="profile-box d-flex flex-column align-items-center justify-content-center shadow-sm">
      <div class="profile-box-item">
        <img class="profile-image" src="@/assets/image/profile.png" alt="프로필">
      </div>
      <div class="profile-nickname">
        {{ userStore.user.nickname }}
      </div>
      <div class="follow-info">
        <span class="mx-1">팔로워</span>
        <span class="mx-1">{{ userStore.followerList.length }}</span>
        |
        <span class="mx-1">팔로잉</span>
        <span class="mx-1">{{ userStore.followingList.length }}</span>
      </div>
      <div class="profile-box-item">
        <button class="btn btn-outline-secondary mt-2" @click="goSetting">설정</button>
      </div>

      <hr>

      <div class="icons d-flex align-items-center profile-box-item">
        <div class="icon-group d-flex flex-column align-items-center">
          <RouterLink class="unline icon" :to="{ name: 'scrapbook' }"> <i class="bi bi-bookmark"> </i></RouterLink>
          <div class="icon-title">스크랩북</div>
          <div class="icon-num">{{ userStore.storyScrapList.length }}</div>
        </div>
        <div class="icon-group d-flex flex-column align-items-center">
          <RouterLink class="unline icon" :to="{ name: 'zzim' }"> <i class="bi bi-heart"></i> </RouterLink>
          <div class="icon-title">찜</div>
          <div class="icon-num">{{ userStore.productScrapList.length }}</div>
        </div>
      </div>
    </div>
    <div class="mystory">
      <div class="container">
        <div class="section">
          <div class="section-title">
            <div class="small-title">
              <h4>내가 쓴 게시글</h4>
            </div>
            <div>
              <a>더보기</a>
            </div>
          </div>

          <div class="popular d-flex align-items-start">
            <!-- v-for 넣기 -->
            <div v-for="story in storyStore.recent6List" :key="story">
              <div class="popular-pic mx-2">
                <img class="pic" :src="`src/assets/image/story/${story.image}`" alt="이미지">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="seller-regist d-flex justify-content-center" v-if="isUser">
      <div>
        <button class="btn btn-outline-secondary btn-regist"><RouterLink to="@/components/SellerRegist.vue">판매자 등록하기</RouterLink></button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useStoryStore } from '@/stores/story';
import { useUserStore } from '@/stores/user';
import { computed, onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

const userStore = useUserStore()
const storyStore = useStoryStore()
const router = useRouter()

onMounted(() => {
  userStore.getUser();
  userStore.getFollowing(sessionStorage.getItem("loginUser"));
  userStore.getFollower(sessionStorage.getItem("loginUser"));
  storyStore.getUserStory();
  userStore.getProductScrap(sessionStorage.getItem("loginUser"));
  userStore.getStoryScrap(sessionStorage.getItem("loginUser"));
});


const goSetting = function () {
  router.push({ name: 'setting' })
}

const isUser = computed(()=>{
  return sessionStorage.getItem("role") == "u" ? true : false
})


</script>

<style scoped>

.seller-regist{
  width: 250px;
}
.top {
  padding-left: 80px;
  padding-right: 80px;
  
}
.mystory{
  width: 800px;
}

.container {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  padding-left: 80px;
  padding-right: 80px;
}

.section {
  margin-bottom: 20px;
  width: 100%;
}

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

.popular {
  display: flex;
  flex-wrap: wrap;
  width: 110%;
}

.popular-pic {
  width: 200px;
  height: 200px;
  border-radius: 8px;
  object-fit: cover;
  object-fit: cover;
  overflow: hidden;
  margin-bottom: 20px;
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


.profile-image {
  width: 150px;
  height: 150px;
  border-radius: 70%;
  padding: 0;
  margin-bottom: 10px;
}

.profile-nickname {
  font-size: x-large;
  font-weight: bold;
  margin-bottom: 10px;
}

.profile-box {
  margin-top: 50px;
  border: 1px solid rgb(212, 212, 212);
  border-radius: 5px;
  padding: 20px;
  width: 250px;
  height: 500px;
  font-size: 14px;
  color: rgb(96, 96, 96)
}

.profile-box-item {
  margin: 5px 0;
}

.icon-group {
  width: 100px;

}
/* 
.btn-regist {
  margin-top: 20px;
  height: 40px;
  background-color: #34C5F0;
  color: white;
  font-weight: bold;
  border: 1px solid #34C5F0;
  border-radius: 5px;
} */



hr {
  width: 100%;
  /* color: rgb(212, 212, 212); */
}

.unline {
  text-decoration: none;
  color: black;
}

.unline:hover {
  color: #34C5F0;
}

.icon {
  font-size: 27px;
}
</style>
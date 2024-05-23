<template>
  <div class="container">
    <div class="test">
      <div class="what-story">
        <div class="small-title">
          <h3>3대몇🏋️</h3>
          <span>최고기록 경신를 모두에게 자랑해보세요!</span>
        </div>
        <div class="total-number">
          <span class="total-number">전체 </span> <span v-if="totalStoryLength">{{
            totalStoryLength.toLocaleString('ko-KR') }}</span>
        </div>
      </div>
      <div class="test2">
        <div class="one-pic" v-for="(story, index) in categoryStoryList.slice(0, 18)" :key="story">
          <div class="sub-img">
            <img class="subimg" :src="`src/assets/image/story/${story.image}`" alt="" @click="goDetail(story.storyId)">
            <div class="main-img-info3">
              <i id="book" v-if="isScrap(story.storyId)" @click="YesBook(story.storyId, story)"
                class="bi bi-bookmark-fill setBlue"></i>
              <i id="book2" v-else @click="YesBook(story.storyId, story)" class="bi bi-bookmark"></i>
            </div>
          </div>
          <div class="infoo">
            <div class="font-bold">{{ story.title }}</div>
            <div class="profile-info">
              <div class="profile-image">
                <img class="story-profile-img" src="@/assets/image/profile.png" alt="댓글프로필이미지">
              </div>
              <div class="user-nickname">
                <div v-if="nick(index)">{{ nick(index) }}</div>
              </div>
            </div>
            <div class="story-info-box">
              <span class="story-info">스크랩</span>
              <span class="story-info" v-if="scrapCntList">{{
                scrapCntList[index].toLocaleString('ko-KR') }}</span>
              •
              <span class="story-info">조회</span>
              <span class="story-info">{{ story.viewCnt.toLocaleString('ko-KR') }}</span>
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
import { onBeforeMount, onMounted, computed } from 'vue';
import { useRouter } from 'vue-router'

const storyStore = useStoryStore()
const userStore = useUserStore()
const router = useRouter()

storyStore.getRandom()
storyStore.getCategoryList("3")
userStore.getUserList()
storyStore.getStoryScrap()
storyStore.getCategoryScrapCntList("3")
// onBeforeMount(() => {
// })

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

const categoryStoryList = computed(() => {
  return storyStore.categoryStoryList
})

const getUserNick = (userId) => {
  const user = userStore.userList.find(user => user.userId === userId);
  return user ? user.nickname : '';
};


const nick = function (idx) {
  if (userStore.userList.length > 0) {
    return getUserNick(storyStore.categoryStoryList[idx].userId);
  }
  return '';
}

const goDetail = function (storyId) {
  router.push({ name: 'storyDetail', params: { 'storyId': storyId } })
}

</script>

<style scoped>
.setBlue {
  color: #34C5F0;
}

#book {
  position: absolute;
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
  margin-bottom: 10px;
}

.small-title>h3 {
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

.sub-img:hover>.subimg {
  width: 100%;
  height: 100%;
  border-radius: 5px;
  transform : scale(1.1);
  cursor : pointer;
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
</style>
<template>
  <div class="container">
    <div class="test">
      <div class="what-story">
        <div class="small-title">
          <h3>지금 가장 인기있는 게시글 🔥</h3>
        </div>

      </div>
      <div class="test2">
        <div class="one-pic" v-for="(story, index) in popularList.slice(0, 16)" :key="story">
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
            <span class="font-bold" v-if="popularNick(index)">{{ popularNick(index) }} 님의 </span>
            <span>{{ story.title }}</span>
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
storyStore.getPopularList()
storyStore.getRecentList()
userStore.getUserList()
storyStore.getStoryScrap()
// onBeforeMount(() => {
// })

const storyScrap = computed(() => {
  return storyStore.storyScrap
})
// const testStory = computed(()=>{
//     return storyStore.story
// })

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

const popularList = computed(()=>{
    return storyStore.popular10List
})

const getUserNick = (userId) => {
  const user = userStore.userList.find(user => user.userId === userId);
  return user ? user.nickname : '';
};

const popularNick = function (idx) {
    if (userStore.userList.length > 0) {
        return getUserNick(storyStore.popular10List[idx].userId);
    }
    return '';
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

.small-title>h3 {
  font-weight: bold;
}

.one-pic {
  width: 32%;
  margin-bottom: 10px;
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
  margin-top: 10px;
}

.font-bold {
  font-weight: bold;
  color: #34C5F0;
}
</style>
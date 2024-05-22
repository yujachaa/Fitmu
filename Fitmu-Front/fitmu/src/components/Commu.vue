<template>
    <div class="container toptop">
        <div class="main-img" @click="goDetail(randomStory.storyId)">
            <img class="mainimg" :src="`/src/assets/image/story/${randomStory.image}`" alt="랜덤게시글사진">
            <div class="main-img-info">
                <p class="title">{{ randomStory.title }}</p>
                <div class="user-info">
                    <img class="mini-img" src="@/assets/2.jpg" alt="랜덤게시글작성자프로필사진">
                    <p v-if="randomNick">{{ randomNick }}</p>
                    <p></p>
                </div>
            </div>
        </div>
        <div class="promo">
            <carousel class="carousel" :items-to-show="1" :autoplay=4000 :wrapAround=true>
                <slide class="slide" v-for="slide in 4" :key="slide">
                    <img class="slide-img" :src="`src/assets/${slide}.jpg`" alt="">
                </slide>

                <!-- <template #addons>
                    <navigation />
                </template> -->
            </carousel>
        </div>
    </div>
    <br>
    <div class="container">
        <div class="test">
            <div class="what-story">
                <div class="small-title">
                    <!-- 인기글 상위 6개 -->
                    <h4>지금 제일 핫한 게시글 🔥</h4>
                    <span>다른 유저들이 관심 있는 콘텐츠를 확인해보세요!</span>
                </div>
                <div>
                    <a @click="goPopular">더보기</a>
                </div>
            </div>
            <carousel class="story-carousel" v-bind="settings" :breakpoints="breakpoints" :mouseDrag="false"
                :touchDrag="false">
                <slide v-for="(story, index) in popular10List" :key="story">
                    <div class="slide1">
                        <img class="product_carousel_pic" :src="`src/assets/image/story/${story.image}`" alt="이미지" @click="goDetail(story.storyId)">
                        <div class="main-img-info2">
                            <div class="user-info2">
                                <img class="mini-img2" src="@/assets/2.jpg" alt="하이루">
                                <p class = "ninckname" v-if="popularNick(index)">{{ popularNick(index) }}</p>
                                <div class="bookmark">
                                    <i id="book" :class="{setBlue : isScrap(story.storyId)}" @click = "YesBook(story.storyId, story)" class="bi bi-bookmark-fill"></i>
                                    <i id="book2" :class="{setBlue : isScrap(story.storyId)}" @click = "YesBook(story.storyId, story)" class="bi bi-bookmark"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </slide>
                <template #addons>
                    <navigation />
                </template>
            </carousel>
        </div>
    </div>
    <div class="container">
        <div class="test">
            <div class="what-story">
                <div class="small-title">
                    <h4>오늘의 운동 팁 대방출! 😊</h4>
                </div>
                <div>
                    <a @click="goTip">더보기</a>
                </div>
            </div>
            <div class="test2">
                <div class="one-pic" v-for="(story,index) in recent4List" :key="story">
                    <div class="sub-img">
                        <img class="subimg" :src="`src/assets/image/story/${story.image}`" alt="" @click="goDetail(story.storyId)">
                        <div class="main-img-info3">
                            <i id="book" :class="{setBlue : isScrap(story.storyId)}" class="bi bi-bookmark-fill" @click = "YesBook(story.storyId, story)"></i>
                            <i id="book2" :class="{setBlue : isScrap(story.storyId)}" class="bi bi-bookmark" @click = "YesBook(story.storyId, story)"></i>
                        </div>
                    </div>
                    <div class="infoo" >
                        <span class="font-bold" v-if="recentNick(index)">{{ recentNick(index) }} 님의 </span>
                        <span>{{ story.title }}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <RouterView />
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

const storyScrap = computed(()=>{
    return storyStore.storyScrap
})
// const testStory = computed(()=>{
//     return storyStore.story
// })

const YesBook = function(id, story){
    if(isScrap(id)){
        let index = storyScrap.value.findIndex((scrap)=> scrap.storyId == id)
        storyScrap.value.splice(index, 1)
        storyStore.NoBook(id)
    }else{
        storyScrap.value.push(story)
        storyStore.YesBook(id)
    }
}

const isScrap = function(id){
    for(let i=0; i<storyScrap.value.length; i++){
        if(storyScrap.value[i].storyId == id){
            return true
        }
    }
    return false
}

const recent4List = computed(()=>{
    return storyStore.recent4List
})

const popular10List = computed(()=>{
    return storyStore.popular10List
})

const randomStory = computed(()=>{
    return storyStore.randomStory
})

const getUserNick = (userId) => {
    const user = userStore.userList.find(user => user.userId === userId);
    return user ? user.nickname : '';
};

const randomNick = computed(() => {
    if (userStore.userList.length > 0) {
        return getUserNick(storyStore.randomStory.userId);
    }
    return '';
});

const popularNick = function (idx) {
    if (userStore.userList.length > 0) {
        return getUserNick(storyStore.popular10List[idx].userId);
    }
    return '';
}

const recentNick = function (idx) {
    if (userStore.userList.length > 0) {
        return getUserNick(storyStore.recent4List[idx].userId);
    }
    return '';
}

const goDetail = function(storyId){
    router.push({name: 'storyDetail', params: {'storyId' : storyId}})
}

const goPopular = function(){
    router.push({name: 'popular'})
}

const goTip = function(){
    router.push({name: 'tip'})
}

</script>

<script>
import 'vue3-carousel/dist/carousel.css'
import { defineComponent } from 'vue'
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel'


export default defineComponent({
    name: 'Breakpoints',
    components: {
        Carousel,
        Slide,
        Navigation,
    },
    data: () => ({
        // carousel settings
        settings: {
            itemsToShow: 1,
            snapAlign: 'center',
        },
        // breakpoints are mobile first
        // any settings not specified will fallback to the carousel settings
        breakpoints: {

            // 700px and up
            600: {
                itemsToShow: 1,
                snapAlign: 'center',
            },
            // 700px and up
            850: {
                itemsToShow: 3,
                snapAlign: 'center',
            },
            1000: {
                itemsToShow: 4,
                snapAlign: 'center',
            },
            // 1024 and up
            1200: {
                itemsToShow: 6,
                snapAlign: 'center',
            }
        },
    }),
})

</script>

<style scoped>
.ninckname{
    width : 100px;
}
.setBlue{
    color : #34C5F0;
}
#book {
    position: absolute;
    opacity: 0.5;
}

.toptop {
    margin-top: 20px;
}

.infoo {
    margin-top: 10px;
}

.font-bold {
    font-weight: bold;
    color: #34C5F0;
}

.bookmark {
    color: white;
}

.container {
    width: 100%;
    display: flex;
    padding-left: 80px;
    padding-right: 80px;
    height: 400px;
}

.main-img {
    position: relative;
    width: 70%;
    height: 400px;
    object-fit: cover;
    overflow: hidden;
    border-radius: 5px;
}

.sub-img {
    position: relative;
    width: 100%;
    height: 200px;
    object-fit: cover;
    overflow: hidden;
    border-radius: 5px;
}

.main-img>.mainimg {
    width: 100%;
    height: 100%;
    border-radius: 5px;
    transition: all 0.1s linear;
}

.sub-img>.subimg {
    width: 100%;
    height: 100%;
    border-radius: 5px;
    transition: all 0.1s linear;
}

.main-img:hover .mainimg {
    width: 100%;
    height: 100%;
    border-radius: 5px;
    transform: scale(1.1);
    cursor: pointer;
}

.sub-img:hover .subimg {
    width: 100%;
    height: 100%;
    border-radius: 5px;
    transform: scale(1.1);
    cursor: pointer;
}

.title {
    font-size: 30px;
    font-weight: bold;
}

.user-info {
    display: flex;
}

.user-info2 {
    display: flex;
}

.main-img-info {
    position: absolute;
    top: 75%;
    left: 5%;
    color: white;
}

.main-img-info2 {
    position: absolute;
    top: 85%;
    left: 10%;
    color: white;
    font-weight: bold;
}

.main-img-info3 {
    position: absolute;
    top: 85%;
    left: 90%;
    color: white;
}

.mini-img {
    width: 20px;
    height: 20px;
    object-fit: cover;
    border-radius: 10px;
}

.mini-img2 {
    width: 20px;
    height: 20px;
    object-fit: cover;
    border-radius: 10px;
}

.promo {
    width: 30%;
    margin-left: 20px;
    height: 400px;
    border-radius: 5px;
    object-fit: cover;
}

.carousel {
    width: 100%;
    border-radius: 5px;
    object-fit: cover;
}

.slide {
    width: 100%;
    border-radius: 5px;
    object-fit: cover;
    overflow: hidden;
}

.slide-img {
    width: 100%;
    height: 400px;
    border-radius: 5px;
    object-fit: cover;
    transition: all 0.1s linear;
}

.slide:hover>.slide-img {
    width: 100%;
    height: 400px;
    border-radius: 5px;
    transform: scale(1.1);
    cursor: pointer;
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

.small-title>h4 {
    font-weight: bold;
}


.story-carousel {
    margin-top: 30px;
    width: 100%;
    border-radius: 5px;
    object-fit: cover;
}

.test {
    width: 100%;
}

.test2 {
    margin-top: 20px;
    width: 100%;
    display: flex;
    gap: 20px;
}

.slide1 {
    position: relative;
    width: 100%;
    border-radius: 5px;
    object-fit: cover;
    overflow: hidden;
    margin: 10px;
}

.product_carousel_pic {
    width: 100%;
    height: 250px;
    border-radius: 5px;
    object-fit: cover;
    transition: all 0.1s linear;
}

.slide1:hover>.product_carousel_pic {
    width: 100%;
    height: 250px;
    border-radius: 5px;
    transform: scale(1.1);
    cursor: pointer;
}

.one-pic{
    width: 25%;
}
</style>
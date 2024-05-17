<template>
    <div class="container toptop">
        <div class="main-img">
            <img class="mainimg" src="@/assets/1.jpg" alt="">
            <div class="main-img-info">
                <p class="title">제목인것</p>
                <div class="user-info">
                    <img class="mini-img" src="@/assets/2.jpg" alt="하이루">
                    <p>닉네임인것</p>
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
                    <h4>이런 사진 찾고 있나요?</h4>
                    <span>좋아하실 만한 인테리어 콘텐츠를 추천해드려요</span>
                </div>
                <div>
                    <a>더보기</a>
                </div>
            </div>
            <carousel class="story-carousel" v-bind="settings" :breakpoints="breakpoints" :mouseDrag="false"
                :touchDrag="false">
                <slide v-for="slide in 6" :key="slide">
                    <div class="slide1">
                        <img class="product_carousel_pic" :src="`src/assets/${slide}.jpg`" alt="이미지">
                        <div class="main-img-info2">
                            <div class="user-info2">
                                <img class="mini-img2" src="@/assets/2.jpg" alt="하이루">
                                <p>닉네임인것</p>
                                <div class="bookmark">
                                    <i class="bi bi-bookmark-star"></i>
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
                    <a>더보기</a>
                </div>
            </div>
            <div class="test2">
                <div v-for="index in 4">
                    <div class="sub-img">
                        <img class="subimg" :src="`src/assets/${index}.jpg`" alt="">
                        <div class="main-img-info3">
                            <i class="bi bi-bookmark-star"></i>
                        </div>
                    </div>
                    <div class="infoo">
                        <span class="font-bold">운동 전 몸풀기는 필수! </span>
                        <span>꼭 실천해서 다치지 말아요~</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script setup>

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
    margin-left: 40px;
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
    left: 5%;
    color: white;
    font-weight: bold;
}

.main-img-info3 {
    position: absolute;
    top: 85%;
    left: 88%;
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
</style>
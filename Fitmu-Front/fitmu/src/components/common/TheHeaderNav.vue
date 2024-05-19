<template>
    <div id="toptoptop" class="container">
        <div class="top">
            <div>
                <RouterLink class="logo" :to="{ name: 'commu' }">
                    <h1 class="jua-regular">핏뮤<span class="fitmu">Fitmu</span></h1>
                </RouterLink>
            </div>
            <div class="category">
                <RouterLink class="commu-nav" :to="{ name: 'commu' }">
                    <span :class="{ blue: flag }">커뮤니티</span>
                </RouterLink>
                <RouterLink class="shop-nav" :to="{ name: 'shop' }">
                    <span :class="{ blue: shopFlag }">쇼핑</span>
                </RouterLink>
            </div>
            <div class="search">
                <i class="bi bi-search"></i>
                <input type="text" id="searchbar" class="form-control" placeholder="검색어를 입력하세요.">
            </div>
            <div class="users">
                <div class="login-users" v-if="islogin">
                    <RouterLink class="unline icon" :to="{ name: 'scrapbook' }"> <i class="bi bi-bookmark"> </i>
                    </RouterLink>
                    <RouterLink class="unline icon" :to="{ name: 'zzim' }"> <i class="bi bi-heart"></i> </RouterLink>
                    <div class="dropdown-center icon">
                        <a class="dropdown profile" data-bs-toggle="dropdown" aria-expanded="false">
                            <div class="profile-box">
                                <img class="profile-image" src="@/assets/image/profile.png" alt="프로필">
                                <!-- <i class="bi bi-emoji-smile"></i> -->
                            </div>
                        </a>
                        <ul class="dropdown-menu">
                            <li><RouterLink class="dropdown-item" :to="{name : 'profile'}">마이페이지</RouterLink> </li>
                            <li><a class="dropdown-item" href="#">판매자 신청</a></li>
                            <li><a class="dropdown-item" href="#">고객센터</a></li>
                            <li><a class="dropdown-item" @click="logout">로그아웃</a></li>
                        </ul>
                    </div>
                    <!-- <span class="unline" @click="logout">로그아웃</span> -->
                </div>
                <div class="not-login-user" v-else>
                    <RouterLink class="unline" :to="{ name: 'login' }"><span>로그인</span></RouterLink>
                    <span>|</span>
                    <RouterLink class="unline" :to="{ name: 'regist' }"><span>회원가입</span></RouterLink>
                    <span>|</span>
                    <span class="unline">고객센터</span>
                </div>
                <button class="btn" @click = "registForm">글쓰기</button>
            </div>
        </div>
    </div>
    <hr>
    <div class="container">
        <div v-if="flag" class="top">
            <div class="commu-category-first">
                <RouterLink :to="{ name: 'commu' }">
                    <span :class="selected">홈</span>
                </RouterLink>
                <span :class="selected1">홈트레이닝</span>
                <span :class="selected2">오운완</span>
                <span :class="selected3">3대몇</span>
                <span :class="selected4">운동팁</span>
                <span :class="selected5">러닝</span>
            </div>
        </div>
        <div v-else-if="shopFlag" class="top">
            <div class="shop-category-first">
                <RouterLink :to="{ name: 'shop' }">
                    <span :class="selected9">쇼핑홈</span>
                </RouterLink>
                <span :class="selected6">단백질/보충제</span>
                <span :class="selected7">다이어트식단</span>
                <span :class="selected8">의류</span>
                <RouterLink :to="{name : 'productRegist'}">상품등록</RouterLink>
            </div>
        </div>
        <div v-else-if="mypageFlag" class="top">
            <div class="mypage-category">
                <RouterLink :to="{ name: 'profile' }">
                    <span :class="selected10">프로필</span>
                </RouterLink>
                <span :class="selected11">나의 쇼핑</span>
                <span :class="selected12">나의 리뷰</span>
                <span :class="selected13">스크랩북</span>
                <span :class="selected14">나의 문의</span>
                <span :class="selected15">설정</span>
            </div>
        </div>
    </div>
    <hr class="under-hr">
</template>

<script setup>
import { RouterLink, RouterView, useRoute, useRouter } from "vue-router";
import { ref, computed } from "vue";
import { useUserStore } from "@/stores/user"

const router = useRouter();
const route = useRoute();
const userStore = useUserStore();

const role = ref(sessionStorage.getItem("role"))

const logout = function () {
    console.log(sessionStorage.getItem("loginUser"))
    userStore.logout();
}

const islogin = computed(()=>{
    
    if(sessionStorage.getItem("loginUser") == null)
        return false;
    else
        return true;
})

const selected = computed(() => {
    if (route.name === "commu") {
        return { blue: true }
    }
})
// const selected1 = computed(()=>{
//     if(route.name === "commu"){
//         return {blue : true}
//     }
// })
// const selected2 = computed(()=>{
//     if(route.name === "commu"){
//         return {blue : true}
//     }
// })
// const selected3 = computed(()=>{
//     if(route.name === "commu"){
//         return {blue : true}
//     }
// })
// const selected4 = computed(()=>{
//     if(route.name === "commu"){
//         return {blue : true}
//     }
// })
// const selected5 = computed(()=>{
//     if(route.name === "commu"){
//         return {blue : true}
//     }
// })
const selected9 = computed(() => {
    if (route.name === "shop") {
        return { blue: true }
    }
})
// const selected6 = computed(()=>{
//     if(route.name === "shop"){
//         return {blue : true}
//     }
// })
// const selected7 = computed(()=>{
//     if(route.name === "commu"){
//         return {blue : true}
//     }
// })
// const selected8 = computed(()=>{
//     if(route.name === "commu"){
//         return {blue : true}
//     }
// })
const selected10 = computed(()=>{
    if(route.name === "profile"){
        return {blue : true}
    }
})
// const selected11 = computed(()=>{
//     if(route.name === "commu"){
//         return {blue : true}
//     }
// })
// const selected12 = computed(()=>{
//     if(route.name === "commu"){
//         return {blue : true}
//     }
// })
// const selected13 = computed(()=>{
//     if(route.name === "commu"){
//         return {blue : true}
//     }
// })
// const selected14 = computed(()=>{
//     if(route.name === "commu"){
//         return {blue : true}
//     }
// })
// const selected15 = computed(()=>{
//     if(route.name === "commu"){
//         return {blue : true}
//     }
// })

const flag = computed(() => {
    return route.fullPath == "/" ? true : false;
})

const shopFlag = computed(()=>{
    return route.fullPath == "/shop" ? true : false;
})

const mypageFlag = computed(()=>{
    return (!flag.value && !shopFlag.value)
})

const registForm = function(){
    router.push({name : "storyregist"})
}


</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Jua&display=swap');

#toptoptop {
    margin-top: 20px;
}

.under-hr{
    margin-bottom : 0px;
}

.blue {
    color: #34C5F0;
}

.unline {
    text-decoration: none;
    color: black;
}

.unline:hover {
    color: #34C5F0;
}

.btn {
    width: 80px;
    border: 1px solid #34C5F0;
    color: white;
    background-color: #34C5F0;
    margin-left: 10px;
}

.btn:hover {
    background-color: #257f9b;
    border: 1px solid #257f9b;
}

.top {
    width: 100%;
    display: flex;
    align-items: center;
    padding-left: 80px;
    padding-right: 80px;
    justify-content: space-between;
}

.category {
    padding-right: 100px;
    display: flex;
    justify-content: start;
    align-items: end;
    padding-bottom: 10px;
}

.category span {
    font-weight: bold;
    font-size: 19px;
}

.search {
    position: relative;
    width: 200px;
}

.search>input {
    width: 250px;
}

.search>i {
    position: absolute;
    left: 7%;
    top: 1%;
}

.commu-category-first {
    display: flex;
    gap: 30px;
    font-weight: bold;
}

.commu-category-first a {
    text-decoration: none;
    color: black;
}

.shop-category-first {
    display: flex;
    gap: 30px;
    font-weight: bold;
}

.shop-category-first a {
    text-decoration: none;
    color: black;
}

.mypage-category{
    display: flex;
    width: 100%;
    gap: 30px;
    font-weight: bold;
    justify-content: center;
}

.mypage-category a {
    text-decoration: none;
    color: black;
}

.commu-nav {
    text-decoration: none;
    color: black;
}

.commu-nav p {
    font-weight: 200;
}

.commu-nav:hover {
    text-decoration: none;
    color: #34C5F0;
}

.shop-nav {
    text-decoration: none;
    color: black;
}

.shop-nav:hover {
    text-decoration: none;
    color: #34C5F0;
}


.logo {
    margin-right: auto;
    text-decoration: none;
    color: black;
}

/* .logo h1{
    font-weight: bolder;
} */

.fitmu {
    color: #34C5F0;
    font-size: smaller;
    margin-left: 5px;

}

.category {
    display: flex;
    padding-top: 10px;
    justify-content: start;
    padding-left: 50px;
    gap: 30px;
}

.users {
    margin-left: auto;
    align-items: center;
    display: flex;
    gap: 8px;
    font-size: 14px;
}
.login-users{
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.not-login-user{
    align-items: center;
    display: flex;
    gap: 8px;
    font-size: 14px;
}

.category p {
    font-weight: bold;
}

.jua-regular {
    font-family: "Jua", sans-serif;
    font-weight: 100;
    font-style: normal;
}

.icon {
    font-size: 27px;
    margin-right: 10px;
}

.profile {
    width: 100px;
    height: 100px;
    font-size: 40px;
}

.profile-image {
    width: 35px;
    height: 35px;
    border-radius: 70%;
    padding: 0;
    margin-bottom: 10px;
}

/* .profile-box:hover {
    width: 40px;
    height: 40px;
    border: 2px solid #34C5F0;
    border-radius: 70%;
} */

.profile-image:hover{
    border: 3px solid #34C5F0;
    border-radius: 70%;
}
</style>
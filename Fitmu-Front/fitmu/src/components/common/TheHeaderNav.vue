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
                    <span :class="{ blue: commuflag }">커뮤니티</span>
                </RouterLink>
                <RouterLink class="shop-nav" :to="{ name: 'shop' }">
                    <span :class="{ blue: shopFlag }">쇼핑</span>
                </RouterLink>
            </div>
            <div class="search">
                <i class="bi bi-search search-icon"></i>
                <!-- <div class="flex-fill"> -->
                <input type="text" id="comment" class="input" placeholder="검색어를 입력하세요." @keyup.enter="">
                <!-- </div> -->
                <!-- <input type="text" id="searchbar" class="form-control" placeholder="검색어를 입력하세요."> -->
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
                            <li>
                                <RouterLink class="dropdown-item" :to="{ name: 'profile' }">마이페이지</RouterLink>
                            </li>
                            <li v-if="role == 'u'">
                                <RouterLink class="dropdown-item" :to="{ name: 'seller-regist' }">판매자 신청</RouterLink>
                            </li>
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
                <button class="btn" @click="registForm">글쓰기</button>
            </div>
        </div>
    </div>
    <hr>
    <div class="container">
        <div v-if="commuflag" class="top">
            <div class="commu-category-first">
                <RouterLink :to="{ name: 'commu' }">
                    <span class="commu-category-second" :class="selected">홈</span>
                </RouterLink>
                <RouterLink :to="{name:'homeTraining'}">
                    <span class="commu-category-second" :class="selected1">홈트레이닝</span>
                </RouterLink>
                <RouterLink :to="{name:'today'}">
                    <span class="commu-category-second" :class="selected2">오운완</span>
                </RouterLink>
                <RouterLink :to="{name:'three'}">
                <span class="commu-category-second" :class="selected3">3대몇</span>
                </RouterLink>
                <RouterLink :to="{name:'tip'}">
                <span class="commu-category-second" :class="selected4">운동팁</span>
                </RouterLink>
                <RouterLink :to="{name:'running'}">
                    <span class="commu-category-second" :class="selected5">러닝</span>
                </RouterLink>
            </div>
        </div>
        <div v-else-if="shopFlag" class="top">
            <div class="shop-category-first">
                <RouterLink :to="{ name: 'shop' }">
                    <span class="shop-category-second" :class="selected9">쇼핑홈</span>
                </RouterLink>
                <RouterLink :to="{ name: 'protein'}">
                    <span class="shop-category-second" :class="selected6">단백질/보충제</span>
                </RouterLink>
                <RouterLink :to="{ name: 'diet'}">
                    <span class="shop-category-second" :class="selected7">다이어트식단</span>
                </RouterLink>
                <RouterLink :to="{ name: 'clothes'}">
                    <span class="shop-category-second" :class="selected8">의류</span>
                </RouterLink>
                <RouterLink :to="{ name: 'productRegist' }" class="for-seller">상품등록</RouterLink>
            </div>
        </div>
        <div v-else-if="mypageFlag" class="top">
            <div class="mypage-category">
                <RouterLink :to="{ name: 'profile' }">
                    <span class="mypage-category-second" :class="selected10">프로필</span>
                </RouterLink>
                <RouterLink :to="{ name: 'my-order' }">
                    <span class="mypage-category-second" :class="selected11">나의 쇼핑</span>
                </RouterLink>
                <RouterLink :to="{name: 'my-review'}">
                    <span class="mypage-category-second" :class="selected12">나의 리뷰</span>
                </RouterLink>
                <RouterLink :to="{name: 'my-scrapbook'}">
                    <span class="mypage-category-second" :class="selected13">스크랩북</span>
                </RouterLink>
                <RouterLink :to="{name: 'my-inquiry'}">
                    <span class="mypage-category-second" :class="selected14">나의 문의</span>
                </RouterLink>
                <RouterLink :to="{ name: 'setting' }">
                    <span class="mypage-category-second" :class="selected15">설정</span>
                </RouterLink>
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

const islogin = computed(() => {
    if (sessionStorage.getItem("loginUser") == null)
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
const selected10 = computed(() => {
    if (route.name === "profile") {
        return { blue: true }
    }
})
const selected11 = computed(() => {
    if (route.name === "my-order") {
        return { blue: true }
    }
})
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
const selected15 = computed(() => {
    if (route.name === "setting") {
        return { blue: true }
    }
})

const commuflag = computed(() => {
    const pathsToCheck = ["/homeTraining", "/popluar", "/running", "/three", "/today", "/tip"];
    if (route.fullPath === "/") {
        return true;
    }
    else if (pathsToCheck.some(path => route.fullPath.includes(path))) {
        return true;
    }
    else if (route.fullPath.startsWith('/storydetail')) {
        return true;
    }
    return false;
});

const shopFlag = computed(() => {
    const pathsToCheck = ["/shop", "/clothes", "/diet", "/protein", "/reviewPopular", "/sale"];
    if (pathsToCheck.some(path => route.fullPath.includes(path))) {
        return true;
    }
    else if (route.fullPath.startsWith('/productdetail')) {
        return true;
    }
    return false;
})

const mypageFlag = computed(() => {
    return (!commuflag.value && !shopFlag.value)
})

const registForm = function () {
    router.push({ name: "storyregist" })
}


</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Jua&display=swap');

#toptoptop {
    margin-top: 20px;
}

.under-hr {
    margin-bottom: 0px;
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
    /* padding-right: 50px; */
    display: flex;
    justify-content: start;
    align-items: end;
    padding-bottom: 10px;
    margin-right: auto;
}

.category span {
    font-weight: bold;
    font-size: 19px;
}
.commu-category-second:hover {
    color: #34C5F0;
}

.search {
    position: relative;
    width: 300px;
    display: flex;
    align-items: center;
}



.search>i {
    position: absolute;
    left: 5%;
    top: 30%;
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

.shop-category-second:hover{
    color: #34C5F0;
}

.mypage-category {
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

.mypage-category-second:hover{
    color: #34C5F0;
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

.login-users {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.not-login-user {
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

.profile-image:hover {
    border: 3px solid #34C5F0;
    border-radius: 70%;
}


.input {
    margin: 10px 5px;
    width: 100%;
    border: 1px solid rgb(185, 185, 185);
    border-radius: 4px;
    height: 40px;
    padding-left: 30px;
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

.for-seller:hover{
    color:pink;
}
</style>
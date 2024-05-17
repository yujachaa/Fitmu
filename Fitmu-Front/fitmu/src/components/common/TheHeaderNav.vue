<template>
    <div id = "toptoptop" class="container">
        <div class="top">
            <div>
                <RouterLink class="logo" :to="{ name: 'commu' }">
                    <h1 class="jua-regular">핏뮤<span class="fitmu">Fitmu</span></h1>
                </RouterLink>
            </div>
            <div class="category">
                <RouterLink class="commu-nav" :to="{ name: 'commu' }">
                    <span :class = "{blue : flag}">커뮤니티</span>
                </RouterLink>
                <RouterLink class="shop-nav" :to="{ name: 'shop' }">
                    <span :class = "{blue : !flag}">쇼핑</span>
                </RouterLink>
            </div>
            <div class="search">
                <i class="bi bi-search"></i>
                <input type="text" class="form-control" placeholder="검색어를 입력하세요.">
            </div>
            <div class="users">
                <RouterLink class = "unline" :to="{name : 'login'}"><span>로그인</span></RouterLink>
                <span>|</span>
                <RouterLink class = "unline" :to="{name : 'regist'}"><span>회원가입</span></RouterLink>
                <span>|</span>
                <span class = "unline">고객센터</span>
                <button class="btn">글쓰기</button>
            </div>
        </div>
    </div>
    <hr>
    <div class="container">
        <div v-if = "flag" class="top">
            <div class="commu-category-first">
                <RouterLink :to="{ name: 'commu' }">
                    <scan :class = "selected">홈</scan>
                </RouterLink>
                <scan :class = "selected1">홈트레이닝</scan>
                <scan :class = "selected2">오운완</scan>
                <scan :class = "selected3">3대몇</scan>
                <scan :class = "selected4">운동팁</scan>
                <scan :class = "selected5">러닝</scan>
            </div>
        </div>
        <div v-else class="top">
            <div class="shop-category-first">
                <RouterLink :to="{ name: 'shop' }">
                    <scan :class = "selected9">쇼핑홈</scan>
                </RouterLink>
                <scan :class = "selected6">단백질/보충제</scan>
                <scan :class = "selected7">다이어트식단</scan>
                <scan :class = "selected8">의류</scan>
            </div>
        </div>
    </div>
    <hr style="margin-bottom : 0px;">
</template>

<script setup>
import { RouterLink, RouterView, useRoute, useRouter } from "vue-router";
import {ref, computed} from "vue";

const route = useRoute();

const selected = computed(()=>{
    if(route.name === "commu"){
        return {blue : true}
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
const selected9 = computed(()=>{
    if(route.name === "shop"){
        return {blue : true}
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

console.log(route.fullPath);
const flag = computed(()=>{
    return route.fullPath == "/" ? true : false;
})


</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Jua&display=swap');

#toptoptop{
    margin-top : 20px;
}

.blue{
    color : #34C5F0;
}

.unline{
    text-decoration: none;
    color : black;
}

.unline:hover{
    color : #34C5F0;
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
    padding-bottom : 10px;
}

.category span{
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
    gap : 30px;
    font-weight: bold;
}

.commu-category-first a{
    text-decoration: none;
    color : black;
}

.shop-category-first {
    display: flex;
    gap : 30px;
    font-weight: bold;
}

.shop-category-first a{
    text-decoration: none;
    color : black;
}

.commu-nav {
    text-decoration: none;
    color: black;
}

.commu-nav p{
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

.category p {
    font-weight: bold;
}

.jua-regular {
    font-family: "Jua", sans-serif;
    font-weight: 100;
    font-style: normal;
}
</style>
<template>
    <div class="container">
        <div class="top">
            <div>
                <RouterLink class="logo" :to="{ name: 'commu' }">
                    <h1 class="jua-regular">핏뮤<span class="fitmu">Fitmu</span></h1>
                </RouterLink>
            </div>
            <div class="category">
                <RouterLink class="commu-nav" :to="{ name: 'commu' }">
                    <h4>커뮤니티</h4>
                </RouterLink>
                <RouterLink class="shop-nav" :to="{ name: 'shop' }">
                    <h4>쇼핑</h4>
                </RouterLink>
            </div>
            <div class="search">
                <i class="bi bi-search"></i>
                <input type="text" class="form-control" placeholder="검색어를 입력하세요.">
            </div>
            <div class="users">
                <span>로그인</span>
                <span>|</span>
                <span>회원가입</span>
                <span>|</span>
                <span>고객센터</span>
                <button class="btn">글쓰기</button>
            </div>
        </div>
    </div>
    <hr>
    <div class="container">
        <div class="top">
            <div class="commu-category-first">
                <RouterLink :to="{ name: 'commu' }">
                    <h5>홈</h5>
                </RouterLink>
                <h5>홈트레이닝</h5>
                <h5>오운완</h5>
                <h5>3대몇</h5>
                <h5>운동팁</h5>
                <h5>러닝</h5>
            </div>
        </div>
        <div class="top">
            <div class="shop-category-first">
                <RouterLink :to="{ name: 'shop' }">
                    <h5>쇼핑홈</h5>
                </RouterLink>
                <h5>단백질/보충제</h5>
                <h5>다이어트식단</h5>
                <h5>의류</h5>
            </div>
        </div>
    </div>
    <hr style="margin-bottom : 0px;">
</template>

<script setup>
import { RouterLink, RouterView } from "vue-router";
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Jua&display=swap');

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

.container {
    margin-top: 20px;
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
}

.shop-category-first {
    display: flex;
}

.commu-nav {
    text-decoration: none;
    color: black;
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

h4 {
    font-weight: bold;
}

.jua-regular {
    font-family: "Jua", sans-serif;
    font-weight: 100;
    font-style: normal;
}
</style>
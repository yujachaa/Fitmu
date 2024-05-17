<template>
    <div class="container">
        <div>
            <RouterLink class="logo" :to="{ name: 'commu' }">
                <h1>핏뮤<span class="fitmu">Fitmu</span></h1>
            </RouterLink>
        </div>
        <div class="category col col-lg-4">
            <RouterLink class="commu-nav" :to="{ name: 'commu' }">
                <h4>커뮤니티</h4>
            </RouterLink>
            <RouterLink class="shop-nav" :to="{ name: 'shop' }">
                <h4>쇼핑</h4>
            </RouterLink>
        </div>
        <div class="search col col-lg-3">
            <input type="text">
        </div>
        <div class="users">
            <span>로그인</span>
            <span>|</span>
            <span>회원가입</span>
            <span>|</span>
            <span>고객센터</span>
            <button>글쓰기</button>
        </div>
    </div>
    <hr>
    <div class="container">
        <div class="commu-category-first">
            <RouterLink :to = "{name : 'commu'}"><h5>홈</h5></RouterLink>
            <h5>홈트레이닝</h5>
            <h5>오운완</h5>
            <h5>3대몇</h5>
            <h5>운동팁</h5>
            <h5>러닝</h5>
        </div>
    </div>
    <div class="container">
        <div class="shop-category-first">
            <h5>단백질/보충제</h5>
            <h5>다이어트식단</h5>
            <h5>의류</h5>
        </div>
    </div>
    <hr>
</template>

<script setup>
import { RouterLink, RouterView } from "vue-router";
</script>

<style scoped>
.container {
    width: 100%;
    margin-top: 10px;
    display: flex;
    align-items: center;
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
    text-decoration: none;
    color: black;
}

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
    align-items: center;
    display: flex;
    gap: 15px;
}

h4 {
    font-weight: bold;
}

</style>
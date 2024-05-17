<template>
    <div class="login">
        <h1>
            <RouterLink class = "logo" :to="{ name: 'commu' }">핏뮤<span class="fitmu">Fitmu</span></RouterLink>
        </h1>
        <div class="input">
            <input class="id" type="email" placeholder="이메일" v-model="user.email"/>
            <input class="pw" type="password" placeholder="비밀번호" v-model="user.password" @keyup.enter="login"/>
        </div>
        <button id="btn-login" @click="login">로그인</button>
        <RouterLink class = "regist" :to="{ name: 'regist' }">회원가입</RouterLink>
        <span class = "problem">로그인에 문제가 있으신가요?</span>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useUserStore } from '../stores/user'

const store = useUserStore()

const user = ref({
    email: "",
    password: ""
})

const login = function () {
    store.login(user.value)
}

// onMounted(() => {
//     store.getUserList()
// })
</script>

<style scoped>
.fitmu {
    color: #34C5F0;
    font-size: smaller;
    margin-left: 5px;

}

.problem{
    margin-top : 10px;
    color : rgb(200, 190, 190);
}

.regist{
    text-decoration: none;
    color : black;
    margin-top : 10px;
}
#btn-login {
    margin-top: 20px;
    width: 270px;
    height : 40px;
    background-color: #34C5F0;
    color: white;
    font-weight: bold;
    border : none;
    border-radius: 5px;
}

#btn-login:hover {
    background-color: #2990b0;
}

.input {
    display: flex;
    flex-direction: column;
    justify-content: center;
    border-collapse: collapse;
    margin-top : 20px;
}

.id {
    width : 270px;
    height : 50px;
    padding : 10px;
    border-start-start-radius: 5px;
    border-start-end-radius: 5px;
    border: 1px solid rgb(219, 213, 213);
}

.id:focus {
    outline: 3px solid rgb(114, 202, 230, 0.5);
    border-color: #72cae6;
    z-index: 999;
}

.pw {
    width : 270px;
    height : 50px;
    padding : 10px;
    border-end-end-radius: 5px;
    border-end-start-radius: 5px;
    border: 1px solid rgb(219, 213, 213);
}

.pw:focus {
    outline: 3px solid rgb(114, 202, 230, 0.5);
    border-color: #72cae6;
    z-index: 999;
}

.login {
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

}

.logo{
    text-decoration: none;
    color : black;
    font-weight: bold;
}

body{
    background-color: rgb(176, 41, 41);
}
</style>
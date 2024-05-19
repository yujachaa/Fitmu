<template>
    <div>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { RouterLink, RouterView } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const router = useRouter()
axios.get("http://localhost:8080/kakao-api/kakao-login/" + route.query.code)
.then((res) => {
    sessionStorage.setItem("loginUser", res.data.userId)
    sessionStorage.setItem("role", res.data.role)
    router.push({name : "commu"})
    window.alert("로그인 성공")
})
.catch((err) => {
    window.alert("로그인 실패")
    router.push({name : "login"})
})


</script>

<style scoped></style>
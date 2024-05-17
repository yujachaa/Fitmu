<template>
    <div class='regist'>
        <div class="total">
            <h1 class="title">
                <RouterLink class="logo" :to="{ name: 'home' }">핏뮤<span class="fitmu">Fitmu</span></RouterLink>
            </h1>
            <div>
                <h4 class="label">회원가입</h4>
            </div>
            <hr>
            <form class="row g-3 needs-validation" novalidate>
                <div class="input">
                    <label for="name" class="form-label">
                        <h5 class="label">이메일</h5>
                    </label>
                    <div class="row justify-content-center">
                        <div class="input-group col-sm-1">
                            <div class="col-sm-5">
                                <input type="text" class="form-control" placeholder="이메일" v-model="prevemail">
                            </div>
                            <div class="input-group-text">@</div>
                            <div class="col-sm">
                                <select class="form-select" name="" id="" v-model="afteremail">
                                    <option value="">선택해주세요</option>
                                    <option value="">naver.com</option>
                                    <option value="">daum.net</option>
                                    <option value="">직접입력은 죄송</option>
                                </select>
                            </div>
                            <div class="invalid-feedback">필수 입력 항목입니다.</div>
                        </div>
                        <button class="btn btn-primary btn-email">이메일 인증하기</button>
                    </div>
                </div>
    
    
                <div class="input">
                    <label for="pw" class="form-label">
                        <h5 class="label">비밀번호</h5>
                    </label>
                    <div id="passwordHelpBlock" class="form-text">
                        영문,숫자를 포함한 8자 이상의 비밀번호를 입력해주세요.
                    </div>
                    <input type="password" id="pw" class="form-control" aria-describedby="passwordHelpBlock"
                        placeholder="비밀번호" v-model="user.password">
                    <div class="invalid-feedback">필수 입력 항목입니다.</div>
                </div>
    
                <div class="input">
                    <label for="pwcheck" class="form-label">
                        <h5 class="label">비밀번호 확인</h5>
                    </label>
                    <input type="password" id="pwcheck" class="form-control" placeholder="비밀번호 확인">
                    <div class="invalid-feedback">확인을 위해 비밀번호를 한 번 더 입력해주세요.</div>
                </div>
    
                <div class="input">
                    <label for="id" class="form-label">
                        <h5 class="label">닉네임</h5>
                    </label>
                    <div id="idHelp" class="form-text">
                        다른 유저와 겹치지 않도록 입력해주세요.(2~20자)
                    </div>
                    <input type="text" id="id" class="form-control" aria-describedby="idHelp" placeholder="아이디를 입력하세요."
                        v-model="user.id">
                    <div class="invalid-feedback">필수 입력 항목입니다.</div>
                </div>
    
                <!-- 약관동의 -->
                <div class="input">
                    <label class="form-label">
                        <h5 class="label">약관동의</h5>
                    </label>
                    <div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                            <label class="form-check-label" for="invalidCheck">
                                이용약관 <span>(필수)</span>  
                            </label>
                            <div class="invalid-feedback">필수 항목에 동의해주세요.</div>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                            <label class="form-check-label" for="invalidCheck">
                                개인정보수집 및 이용동의 <span>(필수)</span>  
                            </label>
                            <div class="invalid-feedback">필수 항목에 동의해주세요.</div>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                            <label class="form-check-label" for="invalidCheck">
                                개인정보 마케팅 활용 동의 <span>(선택)</span>  
                            </label>
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary btn-regist" @click="registUser()" type="submit">회원가입하기</button>
            </form>


        </div>
        <p>이미 아이디가 있으신가요? <RouterLink id="tologin" :to="{ name: 'login' }">로그인</RouterLink>
        </p>
    </div>

</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useUserStore } from '../stores/user'



const store = useUserStore()
const prevemail = ref("")
const afteremail = ref("")

const user = ref({
    id: "",
    password: "",
    name: "",
    email: prevemail.value + "@" + afteremail.value
})

const registUser = function () {
    store.registUser(user.value)
}





</script>

<style scoped>
.label {
    font-weight: 600;
}

.title {
    font-weight: bold;
}

.fitmu {
    color: #34C5F0;
    font-size: smaller;
    margin-left: 5px;

}

#tologin {
    color: black;
    font-weight: bold;
    margin: 5px;

}

.btn-email {
    margin-top: 10px;
    height: 40px;
    background-color: rgb(246, 246, 246);
    color: rgb(168, 168, 168);
    border: solid 1px rgb(218, 218, 218);
    font-weight: bold;
    border-radius: 5px;
}

.btn-email:active {
    background-color: #34C5F0;
    color: white;
    border: none;
}

.btn-regist {
    margin-top: 20px;
    height: 40px;
    background-color: #34C5F0;
    color: white;
    font-weight: bold;
    border: none;
    border-radius: 5px;
}

.btn-regist:hover {
    background-color: #2990b0;
}

h1 {
    text-align: center;
    margin-bottom: 30px;
}

.input {
    padding: 10px;
}

.form-text {
    margin-bottom: 5px;
}

.logo {
    text-decoration: none;
    color: black;
}

.total {
    margin: 10px;
    width: 400px;
    display: flex;
    flex-direction: column;
}

.regist {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
}
</style>
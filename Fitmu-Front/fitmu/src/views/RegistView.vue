<template>
    <div class='regist'>
        <div class="total">
            <h1 class="title">
                <RouterLink class="logo jua-regular" :to="{ name: 'commu' }">핏뮤<span class="fitmu">Fitmu</span>
                </RouterLink>
            </h1>
            <div>
                <h4 class="label">회원가입</h4>
            </div>
            <hr>
            <form class="row g-3 needs-validation" novalidate>
                <div class="input">
                    <label class="form-label" for="prevemail">
                        <h5 class="label">이메일</h5>
                    </label>
                    <div class="row justify-content-center">
                        <div class="input-group col-sm-1">
                            <div class="col-sm-5">
                                <input type="text" class="form-control" value="" placeholder="이메일" id="prevemail"
                                    v-model.trim="prevemail" :disabled="checked">
                            </div>
                            <div class="input-group-text" id="gol">@</div>
                            <div class="col-sm">
                                <select class="form-select" id="afteremail" v-model.trim="afteremail"
                                    :disabled="checked">
                                    <option value="" selected>선택해주세요</option>
                                    <option value="naver.com">naver.com</option>
                                    <option value="daum.net">daum.net</option>
                                    <option value="sorry.com">직접입력은 죄송</option>
                                </select>
                            </div>
                            <div class="invalid-feedback">필수 입력 항목입니다.</div>
                        </div>
                        <button class="btn btn-primary btn-email" type="button" @click="emailCheck"
                            :disabled="checked">이메일 인증하기</button>
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
                        placeholder="비밀번호" v-model.trim="user.password">
                    <div class="invalid-feedback">필수 입력 항목입니다.</div>
                </div>

                <div class="input">
                    <label for="pwcheck" class="form-label">
                        <h5 class="label">비밀번호 확인</h5>
                    </label>
                    <div class="form-text" id="pwcheckHelp">{{ pwcheckMsg }}</div>
                    <input type="password" id="pwcheck" class="form-control" placeholder="비밀번호 확인" aria-describedby="pwcheckHelp"
                        v-model.trim="pwcheck" @keyup="pwcheckCheck">
                </div>

                <div class="input">
                    <label for="nickname" class="form-label">
                        <h5 class="label">닉네임</h5>
                    </label>
                    <div id="nickHelp" class="form-text">
                        {{ nickMsg }}
                    </div>
                    <input type="text" id="nickname" class="form-control" aria-describedby="nickHelp"
                        placeholder="닉네임을 입력하세요." v-model.trim="user.nickname" @keyup="nickCheck">
                    <div class="invalid-feedback">필수 입력 항목입니다.</div>
                </div>

                <!-- 약관동의 -->
                <div class="input">
                    <label class="form-label" for="totalAgree">
                        <h5 class="label">약관동의</h5>
                    </label>
                    <div class="checkbox1">
                        <div class="form-check">
                            <input id="totalAgree" class="form-check-input" type="checkbox" value=""
                                v-model="totalAgree">
                            <label for="totalAgree"> 전체동의</label>
                        </div>
                        <hr>
                        <div class="form-check">
                            <input v-model="agree1" class="form-check-input" type="checkbox" value="false"
                                id="invalidCheck1" required>
                            <label class="form-check-label" for="invalidCheck1">
                                이용약관 <span>(필수)</span>
                            </label>
                            <div class="invalid-feedback">필수 항목에 동의해주세요.</div>
                        </div>
                        <div class="form-check">
                            <input v-model="agree2" class="form-check-input" type="checkbox" value="false"
                                id="invalidCheck2" required>
                            <label class="form-check-label" for="invalidCheck2">
                                개인정보수집 및 이용동의 <span>(필수)</span>
                            </label>
                            <div class="invalid-feedback">필수 항목에 동의해주세요.</div>
                        </div>
                        <div class="form-check">
                            <input v-model="agree3" class="form-check-input" type="checkbox" value="false"
                                id="invalidCheck3" required>
                            <label class="form-check-label" for="invalidCheck3">
                                개인정보 마케팅 활용 동의 <span>(선택)</span>
                            </label>
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary btn-regist" @click="registUser" type="button">회원가입하기</button>
            </form>


        </div>
        <p>이미 아이디가 있으신가요? <RouterLink id="tologin" :to="{ name: 'login' }">로그인</RouterLink>
        </p>
    </div>

</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { useUserStore } from '@/stores/user'
import { useRouter } from 'vue-router';

const router = useRouter()


onMounted(()=>{
    store.getUserList()
})

const agree1 = ref(false)
const agree2 = ref(false)
const agree3 = ref(false)

const totalAgree = ref(false)

watch((totalAgree), (newValue, oldVaue) => {
    if (totalAgree.value) {
        if (!agree1.value) {
            agree1.value = true
        }
        if (!agree2.value) {
            agree2.value = true
        }
        if (!agree3.value) {
            agree3.value = true
        }
    } else {
        if (agree1.value) {
            agree1.value = false
        }
        if (agree2.value) {
            agree2.value = false
        }
        if (agree3.value) {
            agree3.value = false
        }
    }
})


const store = useUserStore()
const prevemail = ref("")
const afteremail = ref("")

const user = ref({
    email: "",
    password: "",
    nickname: "",
    agreeTOS: 0,
    agreePICU: 0,
    agreePromotion: 0
})

const pwcheck = ref("")
const checked = ref(false)

const emailCheck = function () {
    if (prevemail.value == "" || afteremail.value == "") {
        window.alert("이메일을 입력해주세요!")
        return
    }

    let email = prevemail.value + "@" + afteremail.value

    //이메일 중복확인
    store.emailCheck(email)
        .then(result => {
            // console.log(result);  // true 또는 false
            if (result) {
                window.alert("사용가능한 이메일입니다.")
                checked.value = true
            } else {
                window.alert("사용할 수 없는 이메일입니다. 다시입력해주세요.")
            }
        })
        .catch(err => {
            console.error("이메일 확인 중 오류 발생:", err);
        });
}

const registUser = function () {
    if (user.value.password.length < 8) {
        window.alert("비밀번호 8자 이상으로 입력해주세요!")
        return
    }
    if (user.value.password != pwcheck.value) {
        window.alert("비밀번호가 일치하지 않습니다!")
        return
    }

    if (nickMsg.value != "사용가능한 닉네임입니다! ✔️"){
        window.alert("닉네임을 확인해주세요!")
        return
    }

    user.value.email = prevemail.value + "@" + afteremail.value
    if (agree1.value) {
        user.value.agreeTOS = 1
    }
    if (agree2.value)
        user.value.agreePICU = 1
    if (agree3.value)
        user.value.agreePromotion = 1

    store.registUser(user.value)
        .then(result => {
            console.log(result);  // true 또는 false
            if(result){
                router.push({ name: 'login' });
                window.alert("회원가입을 축하합니다!")
            } else {
                router.go()
                window.alert("회원가입에 실패했습니다. 다시 시도해주세요.")
            }
        })
        .catch(err => {
            console.error("회원가입 중 오류 발생:", err);
        });
}

//닉네임 체크
const nickMsg = ref("다른 유저와 겹치지 않도록 입력해주세요.(2~20자)")
const nickCheck = function(){
    const nickExist = store.userList.some(existuser => existuser.nickname === user.value.nickname)
    
    if(nickExist){
        nickMsg.value = "이미 존재하는 닉네임입니다."
    } else if(user.value.nickname.length < 2 ) {
        nickMsg.value = "2자 이상 입력해주세요."
    } else if (user.value.nickname.length > 20) {
        nickMsg.value = "20자 이하로 입력해주세요."
    } else {
        nickMsg.value = "사용가능한 닉네임입니다! ✔️"
    }
}

//비밀번호 확인 체크
const pwcheckMsg = ref("확인을 위해 비밀번호를 한 번 더 입력해주세요.")
const pwcheckCheck = function(){
    const isEqual = pwcheck.value == user.value.password ? true : false

    if(isEqual){
        pwcheckMsg.value = "비밀번호가 일치합니다! ✔️"
    } else {
        pwcheckMsg.value = "비밀번호가 일치하지 않습니다."
    }
}



</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Jua&display=swap');

.blue{
    color: #34C5F0;
}
.red{
    color: rgb(195, 38, 38)
}

.jua-regular {
    font-family: "Jua", sans-serif;
    font-weight: 100;
    font-style: normal;
}

.checkbox1 {
    width: 100%;
    height: 170px;
    padding: 15px;
    border: 1px solid #EAEDEF;
}

hr {
    color: #EAEDEF;
}

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
    background-color: #34C5F0;
    color: white;
    border: none;
    font-weight: bold;
    border-radius: 5px;
}

.btn-email:hover {
    background-color: #26768e;
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
    background-color: #26768e;
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

#gol {
    z-index: -1;
}
</style>
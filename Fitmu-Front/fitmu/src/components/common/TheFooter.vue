<template>
    <div class = "footertop">
        <div class = "garo1">
            <RouterLink class="logo" :to="{ name: 'commu' }">
                <h1 class="jua-regular">핏뮤<span class="fitmu">Fitmu</span></h1>
            </RouterLink>
            <div class ="sero">
                <div>
                    <span class = "white">이용약관</span>
                    <span class = "seroline">|</span>
                    <span class = "blue">개인정보처리방침</span>
                </div>
                <span class = "haha">본 사이트의 모든 콘텐츠는 공유 가능합니다. 언제든지 사용하셔도 좋습니다.</span>
                <span>Copyright © SSAFY All Rights Released.</span>
            </div>
            <div class ="sero hoho">
                <span>(주)핏뮤플레이스 <span>|</span> 대표이사 이윤주 <span>|</span> 대전 어딘가 있겠지..</span>
                <span>jeonsm22@daum.net | 사업자등록번호 010-7939-4420</span>
                <span>인생 첫 프로젝트.. 정말 힘드네요</span>
                <span>한 학기동안 고생하셨습니다.</span>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.hoho{
    color : #CDD3D5;
}
.haha{
    color : #8590A2;
}
.seroline{
    margin-left : 3px;
    margin-right: 3px;
}
.blue{
    color : blue;
}
.white{
    color : gray;
}
.sero{
    display : flex;
    flex-direction: column;
    gap : 5px;
}
.garo1{
    display : flex;
    justify-content: space-between;
    align-items: center;
    height : 100%;
    width : 100%;
}
.footertop{
    display : flex;
    width : 100%;
    height : 200px;
    background-color: #F7F9FA;
    padding-left : 130px;
    padding-right : 130px;
    padding-top : 20px;
    padding-bottom : 20px;
    align-items: center;
    justify-content: space-between;
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
.jua-regular {
    font-family: "Jua", sans-serif;
    font-weight: 100;
    font-style: normal;
}
</style>
<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { watch } from 'vue'
import OpenAI from 'openai'
import TheFooter from '@/components/common/TheFooter.vue'
import {useRoute} from 'vue-router'
import {useUserStore} from '@/stores/user'

import { useIdle } from '@vueuse/core'

const { idle, lastActive } = useIdle(60 * 60 * 1000) // 1시간
const userStore = useUserStore()
watch((idle), (newValue, oldValue)=>{
    if(sessionStorage.getItem("loginUser") != null && idle){
        userStore.logout()
        window.alert("이용 시간이 만료되었습니다. 다시 로그인 해주세요.")
    }
})

const route = useRoute()

const goTop = function(){
  window.scrollTo({
    top: 0,
    behavior: 'smooth' // Smooth scroll animation
  });
}
</script>

<script>
export default {
    name: 'app',
    data() {
        return {
            participants: [
                {
                    id: 'user1',
                    name: '고객센터',
                    imageUrl: 'https://avatars3.githubusercontent.com/u/1915989?s=230&v=4'
                }
            ], // the list of all the participant of the conversation. `name` is the user name, `id` is used to establish the author of a message, `imageUrl` is supposed to be the user avatar.
            titleImageUrl: 'https://a.slack-edge.com/66f9/img/avatars-teams/ava_0001-34.png',
            messageList: [
                { type: 'text', author: `user1`, data: { text: `안녕하세요. 핏뮤봇이에요.` } }
            ], // the list of the messages to show, can be paginated and adjusted dynamically
            newMessagesCount: 0,
            isChatOpen: false, // to determine whether the chat window should be open or closed
            showTypingIndicator: '', // when set to a value matching the participant.id it shows the typing indicator for the specific user
            colors: {
                header: {
                    bg: '#34C5F0',
                    text: '#ffffff'
                },
                launcher: {
                    bg: '#34C5F0'
                },
                messageList: {
                    bg: '#ffffff'
                },
                sentMessage: {
                    bg: '#C1F1FF',
                    text: '#222222'
                },
                receivedMessage: {
                    bg: '#E5F9FF',
                    text: '#222222'
                },
                userInput: {
                    bg: '#f4f7f9',
                    text: '#565867'
                }
            }, // specifies the color scheme for the component
            alwaysScrollToBottom: true, // when set to true always scrolls the chat to the bottom when new events are in (new message, user starts typing...)
            messageStyling: true // enables *bold* /emph/ _underline_ and such (more info at github.com/mattezza/msgdown)
        }
    },
    methods: {
        sendMessage(text) {
            if (text.length > 0) {
                this.newMessagesCount = this.isChatOpen ? this.newMessagesCount : this.newMessagesCount + 1
                // this.onMessageWasSent({author : 'user1', type : 'text', data : text})

            }
        },
        async onMessageWasSent(message) {
            // called when the user sends a message
            this.messageList = [...this.messageList, message]
            const response = await this.getGPTResponse(message.data.text)
            this.messageList = await [...this.messageList, { author: 'user1', type: 'text', data: { text: response } }]
        },
        openChat() {
            // called when the user clicks on the fab button to open the chat
            this.isChatOpen = true
            this.newMessagesCount = 0
        },
        closeChat() {
            // called when the user clicks on the botton to close the chat
            this.isChatOpen = false
        },
        handleScrollToTop() {
            // called when the user scrolls message list to top
            // leverage pagination for loading another page of messages
        },
        handleOnType() {
            console.log('Emit typing event')
        },
        editMessage(message) {
            const m = this.messageList.find(m => m.id === message.id);
            m.isEdited = true;
            m.data.text = message.data.text;
        },
        async getGPTResponse(text) {
            try {
                const openai = new OpenAI({
                    apiKey: "sk-proj-aJPzqQbOWxLpQw5dFlvGT3BlbkFJ9PMO8rOSmlCBAbE1ww6a",
                    dangerouslyAllowBrowser: true,
                })

                const prompt = text

                const response = await openai.chat.completions.create({
                    messages: [
                        { role: 'system', content: '당신은 이제 운동을 주제로 한 커뮤니티와 쇼핑몰을 하나로 만든 핏뮤의 챗봇입니다. 당신은 이제 핏뮤에 대해 자세히 알고있어야 합니다. 고객센터 번호는 01079394420입니다. 누가 물어보면 알려주세요' },
                        { role: 'user', content: text }
                    ],
                    model: 'gpt-3.5-turbo',
                })
                return response.choices[0].message.content
            } catch (error) {
                return "죄송합니다. 다시 질문해주세요."
            }
        }
    }
}
</script>
<template>
    <div>
        <RouterView />
        <TheFooter />
    </div>
    <beautiful-chat :participants="participants" :titleImageUrl="titleImageUrl" :onMessageWasSent="onMessageWasSent"
        :messageList="messageList" :newMessagesCount="newMessagesCount" :isOpen="isChatOpen" :close="closeChat"
        :icons="icons" :open="openChat" :showEmoji="true" :showEdition="true" :showDeletion="true"
        :deletionConfirmation="true" :showTypingIndicator="showTypingIndicator" :showLauncher="true"
        :showCloseButton="true" :colors="colors" :alwaysScrollToBottom="alwaysScrollToBottom"
        :disableUserListToggle="false" :messageStyling="messageStyling" @onType="handleOnType" @edit="editMessage" />
    <div v-if="route.name != 'storyDetail'" class ="circle2" @click = "goTop">
        <i class="bi bi-arrow-up"></i>
    </div>
</template>

<style scoped>

.circle2{
  display : flex;
  width : 60px;
  height : 60px;
  border : 1px solid #C2C8CD;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  border-radius : 50px;
  justify-content: center;
  align-items: center;
  margin-bottom : 20px;
  cursor : pointer;
  position : fixed;
  bottom : 90px;
  right : 27px;
}
</style>

<template>
    <div class="reaction-detail">
        <img :src="imagePath" alt="">
        <h3 class="name">{{displayName}}</h3>
        <div class="status">{{content}}</div>
        <p class="comment">{{comment}}</p>
    </div>
</template>

<script>
    export default {
        name: "UserReactionDetail",
        props:{
            eventId: Number,
            userId: Number
        },
        data(){
            return {
                displayName : String,
                imagePath : String,
                content : String,
                comment : String
            }
        },
        methods:{
            // GET /event/{eventId}/user/{userId}
            // レスポンスめも
            //"statusId": 1,
            // "userName": "asakatu-kun",
            // "displayName": "朝活くん",
            // "imagePath": "https://avatars0.githubusercontent.com/u/50159106",
            // "content": "https://4.bp.blogspot.com/-m56DCo_VDbQ/UU--ubQ1vTI/AAAAAAAAO84/CWFZIAw-zxY/s1600/kaizoku_mark.png",
            // "comment": "おしゃべりしたい！"
        }
    }
</script>

<style scoped>

</style>

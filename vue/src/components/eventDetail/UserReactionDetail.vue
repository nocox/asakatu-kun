<template>
    <div class="reaction-detail">
        <img :src="imagePath" alt="">
        <h3 class="name">{{displayName}}</h3>
        <div class="status">{{content}}</div>
        <p class="comment">{{comment}}</p>
    </div>
</template>

<script>

    import event from "../../../api/event"

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
            getUserReaction:function (eventId, userId) {
                event.getUserReaction(eventId,userId)
                    .then( data =>{
                        this.displayName = data.data.displayName;
                        this.imagePath = data.data.imagePath;
                        this.content = data.data.content;
                        this.comment = data.data.comment;
                    })
                    .catch(()=>{
                        alert('something is error, please retry later');
                    })
            }
        }
    }
</script>

<style scoped>

</style>

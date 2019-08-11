<template>
    <div id="demo-reaction-list">
        <span> {{myReaction.userStatusContent}} </span>
        |
        <span v-for="reaction in reactionList" v-bind:key=reaction.id>
            <input type="radio" v-bind:id="reaction.id" v-model="checked" v-bind:value="reaction.id" style="display:none">
            <label v-bind:for="reaction.id">
                <span> {{reaction.userStatusContent}} </span>
            </label>
        </span>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "demo-reaction-list",

        data() {
            return {
                reactionList: [],
                userName: this.$store.state.userName,
                eventId: 1,
                myReaction: "",
                checked: ""
            }
        },
        methods: {
            getReactionList: function(){
                const reactionListResponse = axios.get('http://localhost:8080/reaction/list', {withCredentials: true});
                reactionListResponse.then(response => {
                        console.log("response.data.reactionList");
                        console.log(response.data);
                        console.log(response.data.data.objectList);
                        this.reactionList =  response.data.data.objectList;
                    }
                ).catch(function (error) {
                  console.log(error);
                });
            },
            getMyReaction: function () {
                const reactionListResponse = axios.get('http://localhost:8080/reaction/my/' + this.eventId, {withCredentials: true});
                reactionListResponse.then(response => {
                        console.log("response.data");
                        console.log(response.data);
                        this.myReaction =  response.data.data;
                    }
                ).catch(function (error) {
                    console.log(error);
                });
            },
            changeMyReaction: function (reactionId) {
                const reactionListResponse = axios.put('http://localhost:8080/reaction/change/' + this.eventId + '/' + reactionId ,new FormData() ,{withCredentials: true});
                reactionListResponse.then(response => {
                        console.log("response.data");
                        console.log(response.data);
                        this.myReaction =  response.data.data;
                    }
                ).catch(function (error) {
                    console.log(error);
                });
            }
        },
        watch: {
            checked: function() {
                this.changeMyReaction(this.checked);
            }
        },
        created(){
          this.getReactionList();
          this.getMyReaction();

        }
    }
</script>

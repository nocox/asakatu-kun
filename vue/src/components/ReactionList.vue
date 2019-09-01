<template>
    <div id="demo-reaction-list">
        <i v-bind:class="myReaction.userStatusContent"></i>
        |
        <span v-for="reaction in reactionList" v-bind:key=reaction.id>
            <input type="radio" v-bind:id="reaction.id" v-model="checked" v-bind:value="reaction.id" style="display:none">
            <label v-bind:for="reaction.id">
                <i v-bind:class="reaction.userStatusContent"></i>
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
                        console.log("response.data");
                        console.log(response.data);
                        this.reactionList =  response.data;
                    }
                ).catch(function (error) {
                  console.log(error);
                });
            },
            getMyReaction: function () {
                const reactionListResponse = axios.get('http://localhost:8080/reaction/myself/' + this.eventId, {withCredentials: true});
                reactionListResponse.then(response => {
                        console.log("response.data");
                        console.log(response.data);
                        this.myReaction =  response.data;
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
                        this.myReaction =  response.data;
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

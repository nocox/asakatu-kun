<template>
    <div id="demo-reaction-list">
        <span v-for="(reaction, index) in reactionList" v-bind:key=index>
          <span> {{reaction.userStatusContent}} </span>
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
                userName: this.$store.state.userName
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
            }
        },
        created(){
          this.getReactionList();
        }
    }
</script>

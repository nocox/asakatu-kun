<template>
    <div id="events">
        <div>
            <router-link to="/events/all">すべて</router-link>|
            <router-link to="/events/joined">参加済み</router-link>
        </div>
        <div>
            <router-view/>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Events",
        data() {
            return {
            }
        },
        methods: {
        }
    }
</script>

<style scoped lang="scss">

</style>
<template>
    <div id="events" class="event-list">
        <section>
            <div class="event-list-switch clearfix">
                    <router-link to="/events" class="event-list-switch-button">ホーム</router-link>
                    <router-link to="/events/joined" class="event-list-switch-button">参加イベント</router-link>
            </div>

            <div class="event-list-content">
                <router-view/>
            </div>
        </section>
    </div>
</template>

<script>
    export default {
        name: "Events",
        data() {
            return {}
        },
        methods: {}
    }
</script>

<style scoped>
    .event-list {
        margin: 0 0;
        height: inherit;
        background: #f5f5f7;

    }
    .event-list-switch {
        display: flex;
        background-color: #ffffff;
    }

    .event-list-switch-button {
        margin: auto;
        color: black;
        font-size: 14px;
        font-weight: bold;
        width: 30%;
        min-width: 100px;
        text-align: center;
        border-bottom: white 4px solid;
        text-decoration: none;
    }

    .router-link-exact-active {
        border-bottom: grey 4px solid;
    }

    .event-list-content{
        margin-top: 1rem;
        height: max-content;
        padding: 2em 1em 1em;
    }

</style>
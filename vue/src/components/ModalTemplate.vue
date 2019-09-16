<template>
    <transition name="modal">
        <div class="modal-mask">
            <div class="modal-wrapper" @click.self="$emit('close')">
                <div class="modal-container">
                    <div class="modal-header">
                        <slot name="header"></slot>
                    </div>

                    <div class="modal-body">
                        <slot name="body"></slot>
                    </div>

                    <div class="modal-footer">
                        <slot name="footer"></slot>
                    </div>
                </div>
            </div>
        </div>
    </transition>
</template>

<script>
    export default {
        name: "ModalTemplate"
    }
</script>

<style scoped>
    #modal {
        margin: 3vw;
    }

    #modal ul {
        overflow: hidden;
        padding: 0;
        list-style: none;
    }

    #modal .modalArea ul li {
        display: inline-block;
        width: 100px;
        text-align: center;
        line-height: 30px;
        background: #ddd;
        font-size: 14px;
        cursor: pointer;
        transition: background 0.3s, color 0.3s;
    }

    #modal .modalArea ul li:hover {
        color: #fff;
        background: #42b983
    }

    .modal-mask {
        position: fixed;
        z-index: 9998;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, .5);
        display: table;
        transition: opacity .3s ease;
    }

    .modal-wrapper {
        height: 100%;
        display: flex;
        align-items: center;
    }

    .modal-container {
        width: 80%;
        max-width: 840px;
        margin: 0 auto;
        padding: 40px 3vw 20px;
        border: 2px solid #aaa;
        border-radius: 13px;
        text-align: center;
        background: #fff;
        box-sizing: border-box;
        box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
        transition: all .3s ease;
    }

    .modal-header{
        margin-top: 0;
        color: black;
        font-size: 24px;
        font-weight: bold;
        text-align: left;
    }

    .modal-body {
        margin: 10px 0;
    }

    .link_area {
        overflow: hidden;
        max-width: 410px;
        margin: auto;
        text-align: center;
    }

    .link_area .modal-default-button {
        cursor: pointer;
        display: inline-block;
        background: #e6e6e6;
        width: 180px;
        line-height: 40px;
        text-decoration: none;
        color: #000;
        margin: 0 10px;
        font-size: 14px;
    }

   .modal-enter {
       opacity: 0;
   }

    .modal-leave-active {
        opacity: 0;
    }

    .modal-enter .modal-container,
    .modal-leave-active .modal-container {
        transform: translateY(30px);
    }
</style>
<template>
    <main class="event-create">
        <h2>イベントを作成</h2>
        <form
                id="event-create-form"
                @submit="checkEventCreateForm"
        >
            <div v-if="errors.length">
                <p>Please correct the following error(s):</p>
                <ul>
                    <li v-for="error in errors" :key="error">{{ error }}</li>
                </ul>
            </div>
            <p>
                <label for="name">イベント名
                <input
                        id="name"
                        v-model="request.eventTitle"
                        type="text"
                        name="name"
                >
                </label>
            </p>
            <p class="form__start">
                <label for="startDate"> 開始日時
                  <div class="event-create-form--twoColumn">
                    <input
                            id="startDate"
                            v-model="request.startDate"
                            type="date"
                            name="startDate"
                    >
                    <input
                            id="startTime"
                            v-model="startTime"
                            type="time"
                            name="startTime"
                    >
                  </div>

                </label>
            </p>
            <p>
                <label for="endDate">終了日時
                  <div class="event-create-form--twoColumn">
                    <input
                              id="endDate"
                              v-model="request.endDate"
                              type="date"
                              name="endDate"
                      >
                      <input
                              id="endTime"
                              v-model="endTime"
                              type="time"
                              name="endTime"
                      >
                    </div>
                    </label>
            </p>

            <p>
                <label for="address">開催住所
                <input
                        id="address"
                        v-model="request.address"
                        type="text"
                        name="address"
                >
                </label>
            </p>
            <p>
                <label for="storeName">店名
                <input
                        id="storeName"
                        v-model="request.storeName"
                        type="text"
                        name="storeName"
                >
                </label>
            </p>
            <p>
                <label for="seatInfo">開催場所のどこらへんに集合するか
                <input
                        id="seatInfo"
                        v-model="request.seatInfo"
                        type="text"
                        name="seatInfo"
                >
                </label>
            </p>
            <p>
                <label for="eventDetail">イベント詳細
                <textarea
                        name="eventDetail"
                        id="eventDetail"
                        cols="30"
                        rows="10"
                        v-model="request.eventDetail"
                        placeholder="イベント内容を入力してください"
                >
              </textarea>
              </label>
            </p>
            <input
                    type="submit"
                    value="Submit"
                    id="event_create--submit"
                    class="primaryButton"
            >
        </form>
    </main>
</template>

<script>
    import event from "../api/event";


    export default {
        name: "EventCreate",
        data() {
            return {
                request: {
                    eventTitle: "",
                    eventDetail: "",
                    startDate: "",
                    endDate: "",
                    address: "",
                    storeName: "",
                    seatInfo: "",
                },
                startTime: "",
                endTime: "",
                eventDetail: "",
                errors: []
            }
        },
        methods: {
            checkEventCreateForm: function (e) {
                e.preventDefault();
                this.errors = [];
                if (!this.request.eventTitle) {
                    this.errors.push("Name required.");
                }
                if (!this.request.eventDetail) {
                    this.errors.push("Detail required.");
                }
                if (!this.request.startDate) {
                    this.errors.push('start date required.');
                }
                console.log(this.startTime);

                this.request.startDate += "T" + this.startTime;
                this.request.endDate += "T" + this.endTime;

                if (!this.errors.length) {
                    this.createEvent(this.request);
                }
            },
            createEvent(request) {
                event.createEvent(request)
                    .then(() => {
                        this.$router.push('/events');
                    })
                    .catch(error => {
                        console.error(error);
                        alert("some thing is error");
                    })
            }
        }
    }
</script>

<style scoped lang="scss">
    @import "../assets/css/base";
    @import "../assets/css/button";

    .event-create {
      margin: 48px 16px;

      .primaryButton {
        margin-top: 2rem;
      }

      h2 {
        font-size: 24px;
        text-align: center;
      }
      form {
        margin: 0 auto;
      }

      p {
        margin-top: 1.4rem;

        label {
          display: block;
          font-size: 0.85rem;
          width:100%;
        }

        input , textarea {
          box-sizing: border-box;
          min-height: 44px;
          margin-top: 0.3rem;
          font-size:1rem;
          border: 1px solid #FFB100;
          padding:0.5rem;
          border-radius: 0.25rem;
          width:100%;
        }
      }

      &-form{
        &--twoColumn {
          display: flex;
          > input {
            width: 40%;
            & + input {
              margin-left: 5%;
            }
          }
        }
      }
    }
</style>

export const USER_REGISTRATION_ERROR = {
    //todo: vue jsでenum使うの案外面倒だったので、TSにしたいなーーーーと思った。
    //ここのメッセージはこういう制約あったわ。って思い出せるように。ユーザーに見せてもよい想定で書きます。
    //response をstringで返すことにしたので、一旦細かいことはきにしなくてもOK
    USER_NAME_LENGTH_ERROR: {
        eVal: "1",
        text: "ユーザー名の長さは3文字以上50文字以下で入力してください。",
        name: "USER_NAME_LENGTH_ERROR"
    },
    USER_NAME_ALREADY_USED: {
        eVal: "2",
        text: "ユーザー名はすでに使われています。",
        name: "USER_NAME_ALREADY_USED"
    },
    PASSWORD_LENGTH_ERROR: {
        eVal: "3",
        text: "パスワードは6文字以上20文字以下で入力してください",
        name: "PASSWORD_LENGTH_ERROR"
    },
    INCORRECT_PASSWORD: {
        eVal: "5",
        text: "パスワードまたはユーザー名が違います。",
        name: "INCORRECT_PASSWORD"
    },
    MAIL_ADDRESS_ALREADY_USED: {
        eVal: "7",
        text: "メールアドレスはすでに使用されています。",
        name: "MAIL_ADDRESS_ALREADY_USED"
    }
};

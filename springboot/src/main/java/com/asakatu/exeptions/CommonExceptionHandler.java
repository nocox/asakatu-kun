package com.asakatu.exeptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice //①コントローラークラスに対する共通処理を実装
public class CommonExceptionHandler
        extends ResponseEntityExceptionHandler { //②独自の例外処理を実装

    @ExceptionHandler //③任意例外に対する例外処理を実装
    public ResponseEntity<Object> handleRuntimeException(
            RuntimeException exception, //③任意の例外を設定
            WebRequest request) {
        return handleExceptionInternal( //④handleExceptionInternalで任意のレスポンスを返却
                exception, //例外
                new ExceptionBody(exception), //レスポンスボディ
                new HttpHeaders(), //レスポンスヘッダー
                HttpStatus.INTERNAL_SERVER_ERROR,  //レスポンスステータス
                request); //リクエスト
    }
}


class ExceptionBody {

    private final String status;
    private Object data;



    public ExceptionBody(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    ExceptionBody(RuntimeException exception) {
        this.status = "500";
        this.data = new ExceptionMessage(exception);
    }

    public String getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}


class ExceptionMessage {

    private String message;

    ExceptionMessage(RuntimeException exception) {
        this.message = exception.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

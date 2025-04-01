package com.week3.homework.advices;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ApiResponse<T> {
    private LocalDateTime Timestamp;
    private T data;
    private ApiError error;


    public ApiResponse(){
        this.Timestamp = LocalDateTime.now();
    }

    public ApiResponse(T data){
        this();
        this.data = data;
    }

    public  ApiResponse(ApiError error){
        this();
        this.error = error;
    }



}

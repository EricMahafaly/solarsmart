package com.solarsmart.frontoffice.models.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private String message = "";
    private boolean isSuccess;

    @JsonProperty("data")
    private T body;

    private ApiResponse(){

    }


    public static <T> ApiResponseBuilder<T> success(){
        return ApiResponse.<T>builder().isSuccess(true);
    }

}

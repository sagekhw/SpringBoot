package com.sagekhw.authJwt.interfaces;

import lombok.Data;

@Data
public class SessionRequestDto {

    private String email;

    private String password;

}

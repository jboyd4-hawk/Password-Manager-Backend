package com.jboyd.hawk.passwordmanagerbackend.dtos;

import lombok.Data;

@Data
public class UserDto {
    private long userId;
    private String username;
    private String password;
}

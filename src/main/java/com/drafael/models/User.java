package com.drafael.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Getter @Setter
    private String nickName;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String password;



}

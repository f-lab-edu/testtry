package com.kleague.kleaguefinder.domain;

import javax.persistence.Entity;

@Entity
public class Member {

    private Long id;
    private String memberName;
    private String memberId;
    private String memberPassword;

    public Member() {
    }

}

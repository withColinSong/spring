package com.song.jwt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Authority {

    @Id
    @Column(name = "authority_name")
    private String authorityName;
}

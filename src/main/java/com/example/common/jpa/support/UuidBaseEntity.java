package com.example.common.jpa.support;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class UuidBaseEntity implements Serializable {
    // NOTE MySQL: binary(16)
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;
}

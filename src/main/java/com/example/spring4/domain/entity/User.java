package com.example.spring4.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author dkotov
 * @since 30.11.2021
 */
@Getter
@Setter
public class User {
    UUID id;
    String firstName;
    String lastName;
    String email;
    Address address;
}

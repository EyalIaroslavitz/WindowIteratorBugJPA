package com.example.window.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record Address(String city, String streetName,int zipCode) {
}

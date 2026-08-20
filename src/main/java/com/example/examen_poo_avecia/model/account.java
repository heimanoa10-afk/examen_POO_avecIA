package com.example.examen_poo_avecia.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class account {

    private String id;

    @NotNull
    private accountType accountType;

    public account(accountType accountType) {
        this.id = UUID.randomUUID().toString();
        this.accountType = accountType;
    }
}

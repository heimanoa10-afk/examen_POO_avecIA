package com.example.examen_poo_avecia.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class transaction {

    private String id;

    private Instant createdAt;

    @NotNull
    private transactionType transactionType;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Le montant doit être positif")
    private BigDecimal amount;

    @NotBlank
    private String reason;

    @NotNull
    private String accountId;

    public transaction(transactionType transactionType, BigDecimal amount, String reason, String accountId) {
        this.id = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
        this.transactionType = transactionType;
        this.amount = amount;
        this.reason = reason;
        this.accountId = accountId;
    }
}
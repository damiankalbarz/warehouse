package com.example.warehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ProductSold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Id produktu nie może być puste")
    private Long productId;
    @NotNull(message = "Ilość nie może być pusta")
    private int quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Override
    public String toString() {
        return "ProductSold{" +
                "id=" + id +
                ", pid='" + productId + '\'' +
                ", qu='" + quantity + '\'' +
                '}';
    }

}

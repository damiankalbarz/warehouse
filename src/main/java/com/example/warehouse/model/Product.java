package com.example.warehouse.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Nazwa produktu nie może być pusta")
    private String name;
    @NotNull(message = "Cena nie może być pusta")
    private double price;
    @NotNull(message = "Ilość nie może być pusta")
    private int quantity;
}

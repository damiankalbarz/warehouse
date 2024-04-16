package com.example.warehouse.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    @JsonIgnore
    @ManyToMany(mappedBy = "productList")
    @Transient
    private List<Invoice> invoices;
}

package com.example.warehouse.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Data saleDate;
    private String methodOfPayment;

    @OneToMany(cascade = CascadeType.ALL)
    List<Product> productList = new ArrayList<>();
    @ManyToOne
    private Client client;



}

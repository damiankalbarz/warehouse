package com.example.warehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date saleDate;
    private String methodOfPayment;

    @Transient
    private List<Product> productList = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;



}

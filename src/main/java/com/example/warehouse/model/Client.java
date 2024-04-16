package com.example.warehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Client
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surename;
    private Long nip;
    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Invoice> invoiceList= new ArrayList<>();
}

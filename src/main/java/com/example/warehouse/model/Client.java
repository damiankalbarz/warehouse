package com.example.warehouse.model;

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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Invoice> invoiceList= new ArrayList<>();
}

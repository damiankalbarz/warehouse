package com.example.warehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
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
    @NotNull(message = "Data sprzedaży nie może być pusta")
    private Date saleDate;
    @NotBlank(message = "Metoda płatności nie może być pusta")
    private String methodOfPayment;
    private double totalPrice;

    @OneToMany(mappedBy = "invoice",  cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductSold> productSoldList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    public void addProductSold(ProductSold productSold, Double price) {
        productSoldList.add(productSold);
        this.totalPrice += price * productSold.getQuantity();
        productSold.setInvoice(this);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", saleDate=" + saleDate +
                ", methodOfPayment='" + methodOfPayment + '\'' +
                ", totalPrice=" + totalPrice +
                ", listtt= " + productSoldList+
                '}';
    }
}

package com.example.warehouse;

import com.example.warehouse.model.Invoice;
import com.example.warehouse.model.ProductSold;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductSoldTest {

    @Test
    public void testSetProductId() {
        ProductSold productSold = new ProductSold();
        productSold.setProductId(123L);
        assertEquals(123L, productSold.getProductId());
    }

    @Test
    public void testSetQuantity() {
        ProductSold productSold = new ProductSold();
        productSold.setQuantity(5);
        assertEquals(5, productSold.getQuantity());
    }

    @Test
    public void testSetInvoice() {
        ProductSold productSold = new ProductSold();
        Invoice invoice = new Invoice();
        productSold.setInvoice(invoice);
        assertEquals(invoice, productSold.getInvoice());
    }
}

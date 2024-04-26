package com.example.warehouse;

import com.example.warehouse.model.Client;
import com.example.warehouse.model.Invoice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    @Test
    public void testSetName() {
        Client client = new Client();
        client.setName("John");
        assertEquals("John", client.getName());
    }

    @Test
    public void testSetSurename() {
        Client client = new Client();
        client.setSurename("Doe");
        assertEquals("Doe", client.getSurename());
    }

    @Test
    public void testSetNip() {
        Client client = new Client();
        client.setNip(1234567890L);
        assertEquals(1234567890L, client.getNip());
    }

    @Test
    public void testAddInvoice() {
        Client client = new Client();
        Invoice invoice = new Invoice();
        client.getInvoiceList().add(invoice);
        assertTrue(client.getInvoiceList().contains(invoice));
        assertEquals(client, invoice.getClient());
    }

    @Test
    public void testRemoveInvoice() {
        Client client = new Client();
        Invoice invoice = new Invoice();
        client.getInvoiceList().add(invoice);
        client.getInvoiceList().remove(invoice);
        assertFalse(client.getInvoiceList().contains(invoice));
        assertNull(invoice.getClient());
    }
}

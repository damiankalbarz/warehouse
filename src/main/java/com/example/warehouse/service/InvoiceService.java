package com.example.warehouse.service;

import com.example.warehouse.model.Invoice;
import com.example.warehouse.model.Product;
import com.example.warehouse.model.ProductSold;
import com.example.warehouse.repository.InvoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    private ProductService productService;


    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
    }

    public void addProductToInvoice(Long invoiceId, ProductSold productSold) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            Optional<Product> optionalProduct = productService.getProductById(productSold.getProductId());
            if(optionalProduct.isPresent())
            {
                Product product = optionalProduct.get();
                double price = product.getPrice();
                invoice.addProductSold(productSold,price);
                product.setQuantity(product.getQuantity()-productSold.getQuantity());
                if(product.getQuantity()>=0){
                    System.out.println(invoice);
                    invoiceRepository.save(invoice);
                    productService.saveProduct(product);
                }
                else {
                    throw new EntityNotFoundException("Quantity is below 0");
                }
            }
        } else {
            throw new EntityNotFoundException("Invoice not found with id: " + invoiceId);
        }
    }
}

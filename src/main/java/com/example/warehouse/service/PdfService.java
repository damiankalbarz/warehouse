package com.example.warehouse.service;

import com.example.warehouse.model.Invoice;
import com.example.warehouse.model.ProductSold;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.Optional;
import com.itextpdf.layout.element.Table;


@Service
public class PdfService {


    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;


    public byte[] generateInvoicePdf(Invoice invoice) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        


        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdfDoc);

        // Nagłówek faktury
        Paragraph header = new Paragraph("FAKTURA").setFontSize(20).setBold();
        header.setTextAlignment(TextAlignment.CENTER);
        document.add(header);

        // Informacje o fakturze
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Nr faktury: " + invoice.getId()));
        document.add(new Paragraph("Data sprzedaży: " + invoice.getSaleDate()));
        document.add(new Paragraph("Metoda płatności: " + invoice.getMethodOfPayment()));

        // Informacje o kliencie
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Dane klienta:"));
        document.add(new Paragraph("ID klienta: " + invoice.getClient().getId()));
        document.add(new Paragraph("Imię i nazwisko: " + invoice.getClient().getName() + " " + invoice.getClient().getSurename()));
        document.add(new Paragraph("NIP: " + invoice.getClient().getNip()));

        // Tabela z listą produktów
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Lista produktów:"));
        Table table = new Table(new float[]{4, 2});
        table.setWidth(UnitValue.createPercentValue(100));
        table.addCell("Produkt");
        table.addCell("Ilosc");
        table.addCell("Cena");

        for (ProductSold productSold : invoice.getProductSoldList()) {
            table.addCell(productSold.getProductId().toString());
            table.addCell(Integer.toString(productSold.getQuantity()));
        }
        document.add(table);

        document.close();
        pdfDoc.close();

        kafkaTemplate.send("invoice", String.valueOf(invoice.getId()), outputStream.toByteArray());

        return outputStream.toByteArray();
    }
}

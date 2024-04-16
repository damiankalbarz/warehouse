package com.example.warehouse.service;

import com.example.warehouse.model.Invoice;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.Optional;

@Service
public class PdfService {

    public byte[] generateInvoicePdf(Invoice invoice) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Invoice ID: " + invoice.getId()));
        document.add(new Paragraph("Sale Date: " + invoice.getSaleDate()));
        document.add(new Paragraph("Method of Payment: " + invoice.getMethodOfPayment()));
        document.add(new Paragraph("Client"));
        document.add(new Paragraph("Id: "+ invoice.getClient().getId()));
        document.add(new Paragraph("Full name: "+ invoice.getClient().getName()+" "+ invoice.getClient().getSurename()));
        document.add(new Paragraph("NIP: "+ invoice.getClient().getNip()));

        document.close();
        pdfDoc.close();

        return outputStream.toByteArray();
    }
}

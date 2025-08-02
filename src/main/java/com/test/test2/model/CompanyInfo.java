package com.test.test2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false, length = 200)
    private String companyName;

    @Column(name = "contact_name", nullable = false, length = 200)
    private String contactName;

    @Column(name = "street_address", nullable = false, length = 200)
    private String streetAddress;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 120)
    private String email;

    @Column(name = "website", length = 255)
    private String website;

    @Column(name = "payment_instructions", columnDefinition = "TEXT")
    private String paymentInstructions;

    @Column(name = "invoice_template_url", nullable = false, length = 500)
    private String invoiceTemplateUrl;

    @Column(name = "pdf_font_path", length = 500)
    private String pdfFontPath;
}

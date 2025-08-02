package com.test.test2.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoice_line_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_location_id")
    private ServiceLocation serviceLocation;

    @Column(name = "month_billed", nullable = false)
    private Integer monthBilled;

    @Column(name = "year_billed", nullable = false)
    private Integer yearBilled;

    @Column(name = "number_of_services", nullable = false)
    private Integer numberOfServices;

    @Column(name = "item_summary_text", nullable = false, columnDefinition = "TEXT")
    private String itemSummaryText;

    @Column(name = "amount_charged_cents", nullable = false)
    private Long amountChargedCents;



}

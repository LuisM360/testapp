package com.test.test2.model;


import com.test.test2.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "service_locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "street_address", nullable = false, length = 200)
    private String streetAddress;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @Column(name = "location_notes", columnDefinition = "TEXT")
    private String locationNotes;

    @Column(name = "is_primary_address", nullable = false)
    private Boolean isPrimaryAddress = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", length = 20)
    private AddressType addressType;







}

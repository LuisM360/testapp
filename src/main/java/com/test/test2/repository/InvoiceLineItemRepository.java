package com.test.test2.repository;

import com.test.test2.model.InvoiceLineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceLineItemRepository extends JpaRepository<InvoiceLineItem, Long> {
}

package com.bloomberg.datawarehouse.entity;

import com.bloomberg.datawarehouse.annotation.RequiredParam;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

/***
 * @author monzer
 * The entity the has been reflected into the database to save the deals
 */
@Entity
@Table(schema = "BLOOMBERG", name = "deal")
@Data
@NoArgsConstructor
public class Deal {

    @Setter
    @Id
    @Column(name = "id", nullable = false)
    @RequiredParam(message = "Deal ID cannot be empty")
    private String id;

    @Column(name = "order_currency", nullable = false)
    @RequiredParam(message = "Deal Ordering currency cannot be empty")
    private Currency orderCurrency;

    @Column(name = "to_currency", nullable = false)
    @RequiredParam(message = "Deal To Currency cannot be empty")
    private Currency toCurrency;

    @Column(name = "deal_date", updatable = false)
    @RequiredParam(message = "Deal date cannot be empty")
    private LocalDateTime dealDate;

    @Column(name = "deal_amount", nullable = false)
    @RequiredParam(message = "Deal amount cannot be empty")
    private BigDecimal dealAmount;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;


}

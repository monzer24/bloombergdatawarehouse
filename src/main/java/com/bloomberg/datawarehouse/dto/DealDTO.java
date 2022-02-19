package com.bloomberg.datawarehouse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Date;

/***
 * @author monzer
 * Used to display the deal details on the web pages.
 */
@Data
@NoArgsConstructor
public class DealDTO implements Serializable {
    private String dealCode;
    private Currency orderCurrency;
    private Currency toCurrency;
    private LocalDateTime dealDate;
    private BigDecimal dealAmount;
}

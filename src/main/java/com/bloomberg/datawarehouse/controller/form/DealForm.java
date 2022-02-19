package com.bloomberg.datawarehouse.controller.form;

import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * @author monzer
 * The form that holds the new added deals request parameters
 */
@Data
@NoArgsConstructor
public class DealForm {

    private String dealId;
    private String orderCurrencyIsoCode;
    private String toCurrencyIsoCode;
    private String dealDate;
    private double dealAmount;

}

package com.bloomberg.datawarehouse.controller.form.validation;

import com.bloomberg.datawarehouse.controller.form.DealForm;
import com.bloomberg.datawarehouse.facade.DealFacade;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Currency;

/***
 * @author monzer
 * Validates the new added deal forms that have been submitted from the endpoint (web page)
 */
@Component("dealFormValidator")
public class DealFormValidation implements Validator {

    @Resource(name = "dealFacade")
    private DealFacade dealFacade;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(DealForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(!(target instanceof DealForm)){
            errors.rejectValue("deal", "deal.form.error");
            return;
        }
        DealForm deal = (DealForm) target;
        // check deal id
        if(deal.getDealId() == null || deal.getDealId().isEmpty()){
            errors.rejectValue("dealId", "deal.id.empty");
        }
        if(dealFacade.doesDealExist(deal.getDealId())){
            errors.rejectValue("dealId", "deal.id.invalid");
            return;
        }

        // check ordering currency iso code
        if(deal.getOrderCurrencyIsoCode() == null || deal.getOrderCurrencyIsoCode().isEmpty()){
            errors.rejectValue("orderCurrencyIsoCode", "deal.orderCurrencyIsoCode.empty");
        }
        if(Currency.getInstance(deal.getOrderCurrencyIsoCode()) == null){
            errors.rejectValue("orderCurrencyIsoCode", "deal.orderCurrencyIsoCode.invalid");
        }

        // check to currency iso code
        if(deal.getToCurrencyIsoCode() == null || deal.getToCurrencyIsoCode().isEmpty()){
            errors.rejectValue("toCurrencyIsoCode", "deal.toCurrencyIsoCode.empty");
        }
        if(Currency.getInstance(deal.getToCurrencyIsoCode()) == null){
            errors.rejectValue("toCurrencyIsoCode", "deal.toCurrencyIsoCode.invalid");
        }

        // check deal date
        if(deal.getDealDate() == null){
            errors.rejectValue("dealDate", "deal.dealDate.empty");
            return;
        }
        try {
            if (LocalDateTime.parse(deal.getDealDate()).isAfter(LocalDateTime.now())) { // deals cannot be added in future
                errors.rejectValue("dealDate", "deal.dealDate.invalid");
            }
        }catch (DateTimeParseException e){
            errors.rejectValue("dealDate", "deal.dealDate.invalid");
        }

        if(deal.getDealAmount() <= 0){
            errors.rejectValue("dealAmount", "deal.dealAmount.invalid");
        }
    }
}

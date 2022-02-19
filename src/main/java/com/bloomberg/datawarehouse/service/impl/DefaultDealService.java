package com.bloomberg.datawarehouse.service.impl;

import com.bloomberg.datawarehouse.annotation.RequiredParam;
import com.bloomberg.datawarehouse.entity.Deal;
import com.bloomberg.datawarehouse.exception.DealException;
import com.bloomberg.datawarehouse.exception.DealExceptionType;
import com.bloomberg.datawarehouse.repository.DealRepository;
import com.bloomberg.datawarehouse.service.DealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/***
 * @author monzer
 * DefaultDealService is the default implementation for the deal service
 */
@Service("dealService")
public class DefaultDealService implements DealService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDealService.class);

    @Resource(name="dealRepository")
    private DealRepository dealRepository;

    @Override
    public Deal createDeal(Deal deal) throws DealException {
        try{
            // service validation
            validateNewDeals(deal);
        }catch (IllegalAccessException e){
            // not likely to be caught -due to setAccessible(true)- but had to catch it to avoid the compilation error
            LOG.error("Validation not completed due to {}", e.getMessage());
            throw new DealException(e.getMessage(), DealExceptionType.ACCESS_ERROR);
        }

        if(this.getDealById(deal.getId()).isPresent()){
            LOG.error("Deal already exists for ID {}", deal.getId());
            throw new DealException("Deal already exists for ID" + deal.getId(), DealExceptionType.DUPLICATED_DEAL);
        }
        LOG.info("Creating a deal with ID: {}", deal.getId());
        // Saving the amount based on the ordering currency digits
        deal.setDealAmount(deal.getDealAmount().setScale(deal.getOrderCurrency().getDefaultFractionDigits(), RoundingMode.HALF_UP));
        deal.setCreationDate(LocalDateTime.now());
        return dealRepository.save(deal);
    }

    @Override
    public Optional<Deal> getDealById(String dealId){
        if(dealId == null || dealId.isBlank() || dealId.isEmpty()){
            return Optional.empty();
        }
        LOG.info("Getting deal with ID {}", dealId);
        return dealRepository.findById(dealId);
    }

    @Override
    public List<Deal> getAllDeals() {
        LOG.info("Getting all deals");
        return dealRepository.findAll();
    }

    @Override
    public List<Deal> getAllDealsSorted(){
        LOG.info("Getting all deals after sorting on creation date");
        return getAllDeals().stream().sorted(Comparator.comparing(Deal::getCreationDate).reversed()).collect(Collectors.toList());
    }

    // Deal validation through reflection
    private void validateNewDeals(Deal deal) throws DealException, IllegalAccessException {
        // getting the model attributes
        List<Field> fields = getObjectFields(deal);

        // Filtering for the required attributes for validation
        List<Field> annotatedFields = fields.stream().filter(field -> field.isAnnotationPresent(RequiredParam.class)).collect(Collectors.toList());
        for (Field field:annotatedFields) {
            field.setAccessible(true);
            Object f = field.get(deal);
            if(f == null){
                throw new DealException(field.getAnnotation(RequiredParam.class).message(), DealExceptionType.MISSING_ATTRIBUTE);
            }
        }
    }

    private List<Field> getObjectFields(Object ob){
        Class<?> clazz = ob.getClass();
        List<Field> currentFields = Arrays.asList(clazz.getDeclaredFields());
        List<Field> superFields = Arrays.asList(clazz.getSuperclass().getDeclaredFields());
        List<Field> fields = new ArrayList<>();
        fields.addAll(currentFields);
        fields.addAll(superFields);
        return fields;
    }
}

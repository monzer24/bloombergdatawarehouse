package com.bloomberg.datawarehouse.mapper;

import com.bloomberg.datawarehouse.dto.DealDTO;
import com.bloomberg.datawarehouse.entity.Deal;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;


/***
 * @author monzer
 * DealMapper class to populate the entitiy to DTO, and vice versa
 */
@Component
public class DealMapper {

    public DealDTO convertToDTO(Deal deal){
        DealDTO dto = new DealDTO();
        if(deal != null){
            dto.setDealCode(deal.getId());
            dto.setOrderCurrency(deal.getOrderCurrency());
            dto.setToCurrency(deal.getToCurrency());
            dto.setDealDate(deal.getDealDate());
            dto.setDealAmount(generateAmount(deal.getDealAmount(), deal.getOrderCurrency()));
        }
        return dto;
    }

    public Deal convertToEntity(DealDTO deal){
        Deal entity = new Deal();
        if(deal != null){
            entity.setId(deal.getDealCode());
            entity.setOrderCurrency(deal.getOrderCurrency());
            entity.setToCurrency(deal.getToCurrency());
            entity.setDealDate(deal.getDealDate());
            entity.setDealAmount(generateAmount(deal.getDealAmount(), deal.getOrderCurrency()));
        }
        return entity;
    }

    public List<DealDTO> convertAll(Collection<Deal> deals){
        if(CollectionUtils.isEmpty(deals)){
            return Collections.emptyList();
        }
        return deals.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private BigDecimal generateAmount(BigDecimal amount, Currency curr){
        return amount.setScale(curr.getDefaultFractionDigits(), RoundingMode.HALF_UP);
    }

}

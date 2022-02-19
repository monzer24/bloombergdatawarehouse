package com.bloomberg.datawarehouse.facade.impl;

import com.bloomberg.datawarehouse.dto.DealDTO;
import com.bloomberg.datawarehouse.entity.Deal;
import com.bloomberg.datawarehouse.exception.DealException;
import com.bloomberg.datawarehouse.facade.DealFacade;
import com.bloomberg.datawarehouse.mapper.DealMapper;
import com.bloomberg.datawarehouse.service.DealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/***
 * @author monzer
 * The default implementation for the Deal Facade layer
 */
@Service("dealFacade")
public class DefaultDealFacade implements DealFacade {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDealFacade.class);

    @Resource(name = "dealMapper")
    private DealMapper mapper;

    @Resource(name = "dealService")
    private DealService dealService;

    @Override
    public DealDTO createNewDeal(DealDTO deal) throws DealException {
        Deal entity = getDealService().createDeal(getMapper().convertToEntity(deal));
        LOG.info("Deal {} was created successfully", deal.getDealCode());
        return mapper.convertToDTO(entity);
    }

    @Override
    public boolean doesDealExist(String dealId) {
        LOG.info("Checking whether deal {} does exist or not", dealId);
        return getDealById(dealId).isPresent();
    }

    @Override
    public Optional<DealDTO> getDealById(String dealId) {
        Optional<Deal> dealById = getDealService().getDealById(dealId);
        return dealById.isEmpty() ? Optional.empty() : Optional.ofNullable(mapper.convertToDTO(dealById.get()));
    }

    @Override
    public List<DealDTO> getAllDeals() {
        return getMapper().convertAll(getDealService().getAllDealsSorted());
    }

    public DealMapper getMapper() {
        return mapper;
    }

    public DealService getDealService() {
        return dealService;
    }
}

package com.bloomberg.datawarehouse.service;

import com.bloomberg.datawarehouse.entity.Deal;
import com.bloomberg.datawarehouse.exception.DealException;

import java.util.List;
import java.util.Optional;

/***
 * @author monzer
 * DealService the service responsible to validate the deal added and presisst them into the database
 */
public interface DealService {

    Deal createDeal(Deal deal) throws DealException;

    Optional<Deal> getDealById(String dealId);

    List<Deal> getAllDeals();

    List<Deal> getAllDealsSorted();

}

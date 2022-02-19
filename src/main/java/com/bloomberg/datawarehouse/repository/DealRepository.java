package com.bloomberg.datawarehouse.repository;

import com.bloomberg.datawarehouse.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/***
 * @author monzer
 * The repository that connects to the database to insert and retrieve the deals
 */
@Repository
public interface DealRepository extends JpaRepository<Deal, String>, JpaSpecificationExecutor<Deal> {
}
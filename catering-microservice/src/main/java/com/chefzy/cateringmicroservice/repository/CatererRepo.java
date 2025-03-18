package com.chefzy.cateringmicroservice.repository;

import com.chefzy.cateringmicroservice.entity.Caterer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatererRepo extends JpaRepository<Caterer,Long> {

    @Query("select c from Caterer c WHERE c.eventType =?1")
    List<Caterer> findAllCaterersByEvent(String eventType);
}
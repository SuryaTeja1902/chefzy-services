package com.chefzy.chefmicroservice.repository;

import com.chefzy.chefmicroservice.entity.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepo extends JpaRepository<Chef, Long> {


}

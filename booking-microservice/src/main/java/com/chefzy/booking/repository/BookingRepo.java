package com.chefzy.booking.repository;

import com.chefzy.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {
    @Query("select b from Booking b WHERE b.chefId =?1")
    Booking findBookingByChefId(Long chefId);
    @Query("select b from Booking b WHERE b.catererId =?1")
    Booking findBookingByCatererId(Long catererId);

    List<Booking> findAllByConsumerId(Long consumerId);

    List<Booking> findAllByCatererId(Long catererId);

    List<Booking> findAllByChefId(Long chefId);

}

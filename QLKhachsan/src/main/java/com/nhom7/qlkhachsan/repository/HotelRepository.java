package com.nhom7.qlkhachsan.repository;

import com.nhom7.qlkhachsan.entity.hotel.Hotel;
import com.nhom7.qlkhachsan.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    Hotel save(Hotel hotel);
    //Set<Hotel> findAllByUser(User user);
}

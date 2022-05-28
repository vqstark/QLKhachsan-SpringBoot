package com.nhom7.qlkhachsan.repository;

import com.nhom7.qlkhachsan.entity.hotel.Hotel;
import com.nhom7.qlkhachsan.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> getAllByAddress(String addressHotel);

    List<Hotel> findAllByOwner(User owner);

//    List<Hotel> getAllByName(String nameHotel);

    Hotel findByName(String nameHotel);

    List<Hotel> findAllByNameContaining(String name);

//    @Query(value = "select * from hotel h where h.name = name " +
//            "union select * from hotel h where h.address = address", nativeQuery = true)
//    List<Hotel> searchByNameAndAddress(String name, String address);
}

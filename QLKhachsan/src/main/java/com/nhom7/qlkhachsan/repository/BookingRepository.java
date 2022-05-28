package com.nhom7.qlkhachsan.repository;

import com.nhom7.qlkhachsan.entity.hotel.BookingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingRoom, Long> {
    List<BookingRoom> findAllByUserBookId(Long userId);
}

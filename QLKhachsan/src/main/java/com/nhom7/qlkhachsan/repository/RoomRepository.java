package com.nhom7.qlkhachsan.repository;

import com.nhom7.qlkhachsan.entity.hotel.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query(value = "select * from room r where r.hotel_id = id", nativeQuery = true)
    List<Room> getAllRoomsByHotelID(Long id);

    @Query(value = "select * from Room r where r.hotel_id = idHotel group by upper(r.type)", nativeQuery = true)
    List<Room> getSomeTypeOfRoom(@Param("idHotel") Long idHotel);

    List<Room> getAllByHotelHasRoomsId(Long idHotel);

    Room findByRoomName(String roomname);

//    @Query(value = "select * from room r where r.hotel = idHotel and r.status = 1", nativeQuery = true)
//    List<Room> getEmptyRoom(Long idHotel);
    List<Room> findAllByHotelHasRoomsIdAndStatus(Long idHotel, boolean status);
}

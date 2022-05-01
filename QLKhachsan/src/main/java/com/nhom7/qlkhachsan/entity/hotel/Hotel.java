package com.nhom7.qlkhachsan.entity.hotel;

import com.nhom7.qlkhachsan.entity.BaseEntity;
import com.nhom7.qlkhachsan.entity.rating.Comment;
import com.nhom7.qlkhachsan.entity.rating.Follow;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="hotel")
public class Hotel extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "hotelIsFollowed")
    private Set<Follow> follows;

    @OneToMany(mappedBy = "hotelIsCommented")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "hotelIsBooked")
    private Set<BookingRoom> bookingRoomSet;
}

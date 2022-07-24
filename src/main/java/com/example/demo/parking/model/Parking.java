package com.example.demo.parking.model;

import com.example.demo.parkingslot.model.ParkingSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "PARKING")
public class Parking {

    @Id
    @Column(name = "PARKING_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parkingId;

    @Version
    private Long version;

    private String parkingNumber;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ParkingSlot> parkingSlotList = new ArrayList<>();

}

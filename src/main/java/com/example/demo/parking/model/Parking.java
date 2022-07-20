package com.example.demo.parking.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

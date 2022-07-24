package com.example.demo.parkingslot.model;

import com.example.demo.base.utils.DateUtils;
import com.example.demo.parking.model.Parking;
import com.example.demo.vehicle.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "PARKING_SLOT")
public class ParkingSlot {

    @Id
    @Column(name = "PARKING_SLOT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parkingSlotId;

    @Version
    @Column(name = "VERSION")
    private Long version;

    private String parkingSlotNumber;

    private Long distanceFromTheEntrance;

    private Integer size;

    private Boolean isAvailable;

    @Column(name = "DATE_CREATED")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, shape = JsonFormat.Shape.STRING)
    private Date dateCreated;

    @Column(name = "DATE_UPDATED")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, shape = JsonFormat.Shape.STRING)
    private Date dateUpdated;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "VEHICLE_ID")
    private Vehicle vehicle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PARKING_ID")
    private Parking parking;


    @PrePersist
    public void beforePersist() {
        dateCreated = new Date();
    }

    @PreUpdate
    public void beforeUpdate() {
        dateUpdated = new Date();
    }

    public void removeVehicle(){
        this.vehicle.setIsPark(false);
        this.vehicle = null;
        this.isAvailable = true;

    }
}

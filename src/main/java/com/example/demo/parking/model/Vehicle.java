package com.example.demo.parking.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.example.demo.parking.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VEHICLE")
@Getter
@Setter
public class Vehicle {

    @Id
    @Column(name = "VEHICLE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vehicleId;

    @Version
    private Long version;

    private String plateNumber;

    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, shape = JsonFormat.Shape.STRING)
    private LocalDateTime entryTime;

    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, shape = JsonFormat.Shape.STRING)
    private LocalDateTime exitTime;

    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, shape = JsonFormat.Shape.STRING)
    private LocalDateTime returnTime;

    private Integer size;

    private Boolean isPark;

    private BigDecimal fee;

    @OneToOne()
    @JoinColumn(name = "PARKING_SLOT_ID",referencedColumnName = "PARKING_SLOT_ID")
    private ParkingSlot parkingSlot;
}

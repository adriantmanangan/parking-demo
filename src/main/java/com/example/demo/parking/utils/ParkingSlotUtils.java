package com.example.demo.parking.utils;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.example.demo.parking.model.ParkingSlot;

public class ParkingSlotUtils {

    public static ParkingSlot getNearestParkingSlot(List<ParkingSlot> parkingSlotList) {
        return parkingSlotList.stream()
            .min(Comparator.comparing(ParkingSlot::getDistanceFromTheEntrance))
            .orElseThrow(NoSuchElementException::new);
    }

}

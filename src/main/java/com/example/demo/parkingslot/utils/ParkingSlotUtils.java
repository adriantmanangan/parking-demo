package com.example.demo.parkingslot.utils;

import com.example.demo.base.constants.Size;
import com.example.demo.base.converter.SizeEnumConverter;
import com.example.demo.parking.exception.ParkingException;
import com.example.demo.parkingslot.model.ParkingSlot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.demo.base.ResponseErrorCode.VEHICLE_NOT_FOUND;

public class ParkingSlotUtils {

    public static ParkingSlot getNearestParkingSlot(List<ParkingSlot> parkingSlotList, Size size) {
        Integer convertedSize = SizeEnumConverter.sizeToInteger(size);
        List<ParkingSlot> parkingSlots = getParkingSlotPerSize(parkingSlotList, SizeEnumConverter.getPossibleSize(convertedSize));
        return parkingSlots.stream().min(Comparator.comparing(ParkingSlot::getDistanceFromTheEntrance)).orElseThrow(NoSuchElementException::new);
    }

    private static List<ParkingSlot> getParkingSlotPerSize(List<ParkingSlot> parkingSlotList, List<Integer> sizes) {
        List<ParkingSlot> parkingSlots = new ArrayList<>();
        try {
            parkingSlots = parkingSlotList.stream().filter(parkingSlot -> sizes.contains(parkingSlot.getSize())).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ParkingException(VEHICLE_NOT_FOUND, "parking.size.no.available");
        }
        return parkingSlots;
    }

}

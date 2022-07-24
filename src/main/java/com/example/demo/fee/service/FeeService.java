package com.example.demo.fee.service;

import com.example.demo.base.utils.TimeUtils;
import com.example.demo.fee.Fee;
import com.example.demo.fee.Vehicle;
import com.example.demo.parkingslot.model.ParkingSlot;
import com.example.demo.vehicle.dto.VehicleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FeeService {

    private final List<Vehicle> vehicleList;
    private final List<Fee> feeList;

    /**
     * Compute fee based on vehicle size,checking beans implementing FeeCalculator and gets its specific rate.
     *
     * @param parkingSlot parkingSlot
     * @param vehicleDto  vehicleDto
     **/
    public void compute(VehicleDto vehicleDto, ParkingSlot parkingSlot) {
        Vehicle vehicle = vehicleList
            .stream()
            .filter(vehicleClass -> vehicleClass.isSize(parkingSlot)).findFirst().orElseThrow(NullPointerException::new);
        BigDecimal hourlyRate = vehicle.getHourlyRate(vehicleDto);
        computeFees(vehicleDto, hourlyRate);
    }

    public void computeFees(VehicleDto vehicleDto, BigDecimal hourlyRate) {
        BigDecimal hour = getHourlyDifference(vehicleDto);
        Fee fee = feeList.stream().filter(feeClass -> feeClass.isApply(hour)).findFirst().orElseThrow(NullPointerException::new);
        fee.compute(hour, hourlyRate, vehicleDto);
    }

    /**
     * Get minute difference of entryTime and exitTime to get the precise hourRate.
     *
     * @param vehicleDto vehicleDto
     * @return computation of the parkingSlot.
     */
    public BigDecimal getHourlyDifference(VehicleDto vehicleDto) {
        return TimeUtils.getDifferenceInHours(vehicleDto.getEntryTime(), vehicleDto.getExitTime());
    }
}

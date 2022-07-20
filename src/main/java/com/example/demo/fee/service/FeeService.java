package com.example.demo.fee.service;

import static com.example.demo.base.utils.TimeUtils.isParkingTimeExceeds24Hrs;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.parking.fee.FeeCalculator;
import com.example.demo.base.utils.TimeUtils;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.fee.dto.DayFee;
import com.example.demo.fee.dto.HourlyFee;
import com.example.demo.vehicle.dto.VehicleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeeService {

    @Value("${parking.fee.exceeds}")
    private BigDecimal dayRate;

    @Value("${parking.fee.flatRate}")
    private BigDecimal flatRate;

    private final List<FeeCalculator> feeCalculatorList;

    /**
     * Compute fee based on vehicle size,checking beans implementing FeeCalculator and gets its specific rate.
     * @param parkingSlotDto  parkingSlotDto
     * @param vehicleDto vehicleDto
     *
     **/
    public void compute(VehicleDto vehicleDto, ParkingSlotDto parkingSlotDto) {
        FeeCalculator feeCalculator = feeCalculatorList
            .stream()
            .filter(calculator -> calculator.isSize(parkingSlotDto)).findFirst().orElseThrow(NullPointerException::new);
        BigDecimal rate = feeCalculator.getHourlyRate(vehicleDto);
        vehicleDto.setFee(getHourlyDifference(rate, vehicleDto));
    }

    /**
     * Get minute difference of entryTime and exitTime to get the precise hourRate.
     * @param hourRate hourRate
     * @param vehicleDto vehicleDto
     * @return computation of the parkingSlot.
     */
    public BigDecimal getHourlyDifference(BigDecimal hourRate, VehicleDto vehicleDto) {
        BigDecimal diff = TimeUtils.getDifferenceInHours(vehicleDto.getEntryTime(),vehicleDto.getExitTime());

        return getRate(diff, hourRate);
    }

    /**
     * Getting rate depends for DayRate and HourlyRate.
     * @param hours minutes
     * @param hourRate hourRate
     * @return Computed date of the specific rate class.
     */
    private BigDecimal getRate(BigDecimal hours, BigDecimal hourRate) {
        if (isParkingTimeExceeds24Hrs(hours)) {
            return new DayFee(hours, dayRate, hourRate).getRate();
        } else {
            return new HourlyFee(hours, hourRate, flatRate).getRate();
        }
    }
}

package com.example.demo.parkingslot.controller;

import javax.validation.Valid;

import com.example.demo.base.constants.ResponseMessageCode;
import com.example.demo.base.constants.SuccessMessageResponse;
import com.example.demo.base.service.response.ResponseService;
import com.example.demo.parkingslot.dto.ParkingSlotDto;
import com.example.demo.parkingslot.service.ParkingSlotService;
import com.example.demo.parkingslot.service.validation.ParkingSlotValidationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ParkingSlotController {

    private final ParkingSlotService parkingSlotService;
    private final ParkingSlotValidationService parkingSlotValidationService;
    private final ResponseService responseService;

    @PostMapping("/api/parkingSlot")
    public ResponseEntity<ParkingSlotDto> createParkingSlot(@RequestBody @Valid ParkingSlotDto parkingSlotDto) {
        return parkingSlotService.createParkingSlot(parkingSlotDto);
    }

    @PostMapping("/api/parkingSlot/validate")
    public ResponseEntity<SuccessMessageResponse> validateParkingSlot(@RequestBody @Valid ParkingSlotDto parkingSlotDto, Errors errors) {
        parkingSlotValidationService.validateParkingSlot(parkingSlotDto, errors);
        return ResponseEntity.ok(responseService.createSuccessfulMessageResponse(ResponseMessageCode.SUCCESS_VALIDATION));
    }

}

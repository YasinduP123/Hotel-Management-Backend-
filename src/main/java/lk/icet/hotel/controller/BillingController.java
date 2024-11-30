package lk.icet.hotel.controller;

import lk.icet.hotel.dto.Billing;
import lk.icet.hotel.service.BillingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/billing")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class BillingController {

	final BillingService billingService;


	@PostMapping("/save")
	public void addBill(@RequestBody Billing billing) {
		log.info("1 " + billing);
		try {
			billingService.saveBill(billing);
		} catch (IOException e) {
			log.error("Error saving billing", e);
		}

	}

	@GetMapping("/all")
	public List<Billing> get(@RequestParam(required = false) Long billingId, Long bookingId){
		if(bookingId!=null){
			return billingService.findByBookingId(bookingId);
		}else if(billingId != null){
			return billingService.findByBillingId(billingId);
		}
		return billingService.getAll();
	}
}

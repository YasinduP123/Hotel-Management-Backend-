package lk.icet.hotel.controller;

import lk.icet.hotel.dto.Billing;
import lk.icet.hotel.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
@CrossOrigin
public class BillingController {

	final BillingService billingService;

	@PostMapping("/add-bill")
	public void addBill(@RequestBody Billing billing){
		System.out.println("1 "+billing);
		billingService.saveBill(billing);
	}
}

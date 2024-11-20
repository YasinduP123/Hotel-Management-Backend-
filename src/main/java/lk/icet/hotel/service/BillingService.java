package lk.icet.hotel.service;

import lk.icet.hotel.dto.Billing;

import java.util.List;

public interface BillingService {
	void saveBill(Billing billing);

	List<Billing> getAll();

	List<Billing> findByBookingId(Long bookingId);

	List<Billing> findByBillingId(Long billingId);

}

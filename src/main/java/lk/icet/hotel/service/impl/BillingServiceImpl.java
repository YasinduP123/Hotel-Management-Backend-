package lk.icet.hotel.service.impl;

import jakarta.transaction.Transactional;
import lk.icet.hotel.dto.Billing;
import lk.icet.hotel.entity.BillingEntity;
import lk.icet.hotel.repository.BillingRepository;
import lk.icet.hotel.repository.BookingRepository;
import lk.icet.hotel.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BillingServiceImpl implements BillingService {

	final ModelMapper mapper;
	final BillingRepository billingRepository;
	final BookingRepository bookingRepository;

	@Override
	@Transactional
	public void saveBill(Billing billing) {
		billingRepository.save(mapper.map(billing, BillingEntity.class));
		bookingRepository.deleteById(billing.getBooking().getBookingId());

	}
}

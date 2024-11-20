package lk.icet.hotel.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lk.icet.hotel.dto.Billing;
import lk.icet.hotel.entity.BillingEntity;
import lk.icet.hotel.repository.BillingRepository;
import lk.icet.hotel.repository.BookingRepository;
import lk.icet.hotel.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BillingServiceImpl implements BillingService {

	final ModelMapper mapper;
	final BillingRepository billingRepository;
	final BookingRepository bookingRepository;
	final EntityManager entityManager;
	@Override
	@Transactional
	public void saveBill(Billing billing) {
		BillingEntity save = billingRepository.save(mapper.map(billing, BillingEntity.class));
		if(save.getBillingId() != null){
			bookingRepository.deleteById(billing.getBookingId());
		}
	}

	@Override
	public List<Billing> getAll() {
		List<Billing> billings = new ArrayList<>();
		billingRepository.findAll().forEach(billingEntity -> billings.add(mapper.map(billingEntity, Billing.class)));
		return billings;
	}

	@Override
	public List<Billing> findByBookingId(Long bookingId) {
		List<Billing> billingList = new ArrayList<>();
		billingRepository.findByBookingId(bookingId).forEach(billingEntity -> billingList.add(mapper.map(billingEntity, Billing.class)));
		return billingList;
	}

	@Override
	public List<Billing> findByBillingId(Long billingId) {
		List<Billing> billingList = new ArrayList<>();
		billingList.add(mapper.map(billingRepository.findById(billingId), Billing.class));
		return billingList;
	}


}

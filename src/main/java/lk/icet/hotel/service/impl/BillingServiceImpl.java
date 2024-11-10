package lk.icet.hotel.service.impl;

import lk.icet.hotel.dto.Billing;
import lk.icet.hotel.entity.BillingEntity;
import lk.icet.hotel.repository.BillingRepository;
import lk.icet.hotel.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BillingServiceImpl implements BillingService {

	final ModelMapper mapper;
	final BillingRepository billingRepository;

	@Override
	public void saveBill(Billing billing) {

		System.out.println("q"+billing);
		BillingEntity map = mapper.map(billing, BillingEntity.class);
		System.out.println(map);
		billingRepository.save(map);
	}
}

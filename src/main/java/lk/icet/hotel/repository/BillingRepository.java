package lk.icet.hotel.repository;

import lk.icet.hotel.entity.BillingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<BillingEntity,Long> {
	List<BillingEntity> findByBookingId(Long bookingId);

}

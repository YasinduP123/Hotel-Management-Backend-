package lk.icet.hotel.repository;

import lk.icet.hotel.entity.BillingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<BillingEntity,Long> {
}

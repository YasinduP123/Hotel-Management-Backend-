package lk.icet.hotel.repository;

import lk.icet.hotel.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
	List<CustomerEntity> findByName(String name);

	List<CustomerEntity> findByNic(String nic);
}

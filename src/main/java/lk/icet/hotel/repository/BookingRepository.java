package lk.icet.hotel.repository;

import lk.icet.hotel.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Long> {

	List<BookingEntity> findByRoomType(String roomType);

	@Query(value = "SELECT * FROM booking WHERE YEAR(check_in_date) = :year AND MONTH(check_in_date) = :month", nativeQuery = true)
	List<BookingEntity> findByCheckInDate(@Param("year") Integer year, @Param("month") Integer month);


}


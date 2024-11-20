package lk.icet.hotel.repository;
import lk.icet.hotel.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity,Long> {
	RoomEntity findByRoomNumber(Integer roomNumber);
}

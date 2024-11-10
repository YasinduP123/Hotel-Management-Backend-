package lk.icet.hotel.repository;

import lk.icet.hotel.dto.Room;
import lk.icet.hotel.entity.BillingEntity;
import lk.icet.hotel.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity,Long> {
}

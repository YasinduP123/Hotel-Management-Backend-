package lk.icet.hotel.repository;

import lk.icet.hotel.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

	@Query(value = "SELECT * FROM user WHERE user_name =:userName AND password =:password", nativeQuery = true)
	List<UserEntity> findUser(@Param("userName") String userName, @Param("password") String password);

}

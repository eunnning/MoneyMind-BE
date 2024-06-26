package WindGirls.MoneyMinder.repository;


import WindGirls.MoneyMinder.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccount(String account);
}


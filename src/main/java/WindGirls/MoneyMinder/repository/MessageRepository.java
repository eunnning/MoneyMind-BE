package WindGirls.MoneyMinder.repository;

import WindGirls.MoneyMinder.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
}

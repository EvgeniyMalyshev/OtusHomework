package otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import otus.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {
}

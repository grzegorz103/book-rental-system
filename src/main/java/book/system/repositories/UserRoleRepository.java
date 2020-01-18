package book.system.repositories;

import book.system.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByUserType(UserRole.UserType usertype);
}

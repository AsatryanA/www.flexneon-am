package am.flexneon.www.repository;


import am.flexneon.www.model.Role;
import am.flexneon.www.model.enumModels.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName name);
}

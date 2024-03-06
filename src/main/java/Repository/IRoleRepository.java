package Repository;

import Domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.roleName IN ('MEMBER', 'GUEST')")
    List<Role> findMemberAndGuestRoles();
}

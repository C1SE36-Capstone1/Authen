package Repository;

import Domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface IAccountRepository extends JpaRepository<Account, Long> {

//    Login, xac thuc va phan quyen
    @Query(nativeQuery = true, value =
            "select account_id, username, email, encrypt_password, is_enable " +
                    "from account " +
                    "where username = :username and is_enable = true")
    Optional<Account> findAccountByUsername(@Param("username") String username);

    @Query(value = "INSERT INTO account_roles (account_id, role_id) " +
            "VALUES (:accountId, :roleId)", nativeQuery = true)
    @Modifying
    void setRoleForAccount(@Param("accountId") Long accountId, @Param("roleId") Long roleId);

    boolean existsByUsername(String username);
}

package Service;

import Domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAccountService extends IService<Account>{
    public Account addAccount(Account account);

    public void setRoleForAccount(Long accountId, Long roleId);

    void changePassword(String username, String newPass);
}

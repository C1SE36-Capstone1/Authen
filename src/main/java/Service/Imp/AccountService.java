package Service.Imp;

import Domain.Account;
import Domain.Role;
import Repository.IAccountRepository;
import Repository.IRoleRepository;
import Service.IAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    private final IAccountRepository accountRepository;
    private final IRoleRepository roleRepository;

    public AccountService(IAccountRepository accountRepository, IRoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public Account addAccount(Account account) {
        if (accountRepository.existsByUsername(account.getUsername())) {
            throw new IllegalArgumentException("Tên tài khoản đã tồn tại. Vui lòng chọn tên tài khoản khác.");
        }

        /** Trong truong hop co dung email de dang nhap vao tai khoan
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại. Vui lòng chọn email khác.");
        }
        */

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
        account.setEnable(true);

        return account;
    }

    @Override
    public void setRoleForAccount(Long accountId, Long roleId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            Role role = roleRepository.findById(roleId).orElse(null);
            if (role != null) {
                accountRepository.setRoleForAccount(accountId, roleId);
            }
        }
    }

    /** Neu can thi chi? sau nha*/
    @Override
    public void changePassword(String username, String newPass) {

    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public Account update(Account account) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}

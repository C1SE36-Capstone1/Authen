package Controller;

import Domain.Account;
import Domain.Role;
import Service.Imp.AccountService;
import Service.Imp.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AccountController {
    private final AccountService accountService;
    // private final EmployeeService employeeService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AccountController(AccountService accountService, RoleService roleService, AuthenticationManager authenticationManager) {
        this.accountService = accountService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
    }

    /** Thêm Tài khoản và setRole cho tài khoản*/
    @PostMapping("/addAccount")
    public ResponseEntity<?> addAccount(@Valid @RequestBody Account account, BindingResult bindingResult, @RequestParam Long roleId) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(
                    error -> {
                        String fieldName = error.getField();
                        String errorMessage = error.getDefaultMessage();
                        errors.put(fieldName, errorMessage);
                    });
            return ResponseEntity.badRequest().body(errors);
        }
        // Kiểm tra xem roleId có tồn tại trong danh sách các roles hợp lệ không
        Role role = roleService.findById(roleId);
        if (role == null) {
            return ResponseEntity.badRequest().body("Invalid roleId");
        }
        Set<Role> tempRoles = account.getRoles();
        tempRoles.add(role);
        account.setRoles(tempRoles);
        return ResponseEntity.ok(account);
    }
}

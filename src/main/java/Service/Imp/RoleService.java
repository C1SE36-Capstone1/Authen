package Service.Imp;

import Domain.Role;
import Repository.IRoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import Service.IRoleService;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findMemberAndGuestRoles();
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}

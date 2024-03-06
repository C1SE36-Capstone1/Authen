package Service;

import Domain.Role;

import java.util.List;

public interface IRoleService extends IService<Role>{
    List<Role> getAllRoles();
}

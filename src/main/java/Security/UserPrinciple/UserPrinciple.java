package Security.UserPrinciple;

import Domain.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private boolean enabled;
    List<GrantedAuthority> authorities = null;

    public UserPrinciple(Long id, String username, String password, boolean enabled, List<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    /**
     * This func help you get account information to UserDetailService
     */
    public static UserPrinciple build(Account account) {
        List<GrantedAuthority> authorities = account.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());

        return new UserPrinciple(
                account.getId(),
                account.getUsername(),
                account.getPassword(),
                account.isEnable(),
                authorities
        );
    }

//    null -> authorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

//    null -> password
    @Override
    public String getPassword() {
        return password;
    }

//    null -> username
    @Override
    public String getUsername() {
        return username;
    }

//    false -> true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    false -> enabled
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}

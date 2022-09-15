package com.company.samplesales.security;

import io.jmix.core.security.UserRepository;
import io.jmix.security.authentication.RoleGrantedAuthority;
import io.jmix.security.model.ResourceRole;
import io.jmix.security.role.ResourceRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component("sales_SimpleUserRepository")
public class SimpleUserRepository implements UserRepository {

    @Autowired
    private ResourceRoleRepository resourceRoleRepository;

    @Override
    public UserDetails getSystemUser() {
        ResourceRole role = resourceRoleRepository.getRoleByCode(FullAccessRole.CODE);
        RoleGrantedAuthority grantedAuthority = RoleGrantedAuthority.ofResourceRole(role);
        return new User("system", "", Collections.singleton(grantedAuthority));
    }

    @Override
    public UserDetails getAnonymousUser() {
        return new User("anonymous", "", Collections.emptySet());
    }

    @Override
    public List<? extends UserDetails> getByUsernameLike(String substring) {
        return Collections.emptyList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
}
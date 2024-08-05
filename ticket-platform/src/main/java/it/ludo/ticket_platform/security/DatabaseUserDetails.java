package it.ludo.ticket_platform.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.ludo.ticket_platform.model.Role;
import it.ludo.ticket_platform.model.User;

public class DatabaseUserDetails implements UserDetails {

    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    public DatabaseUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        
        authorities = new HashSet<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
            System.out.println(role.getRole());
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    
        return this.authorities;
    }

    @Override
    public String getPassword() {
      
        return this.password;
    }

    @Override
    public String getUsername() {
        
        return this.username;
    }

}

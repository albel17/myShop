package myApp.services;

import myApp.entity.PersonsEntity;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private PersonManager personManager;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        PersonsEntity person = personManager.getPersonByEmail(s);
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        if (person.getPersonType() == 1)
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (person.getPersonType() == 2)
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(person.getEmail(), person.getPassword(), authList);
    }
}

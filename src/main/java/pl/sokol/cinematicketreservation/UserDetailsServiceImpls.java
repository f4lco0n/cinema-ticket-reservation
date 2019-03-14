package pl.sokol.cinematicketreservation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sokol.cinematicketreservation.DAO.User;
import pl.sokol.cinematicketreservation.DAO.UserRepo;
import pl.sokol.cinematicketreservation.DAO.UserRoles;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpls implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        else {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getUserPassword(),
                    getGrantedAuthorities(user.getUsersRolesSet())
            );
        }
    }

    private Set<GrantedAuthority> getGrantedAuthorities(Set<UserRoles> usersRolesSet) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserRoles userRoles : usersRolesSet){
            grantedAuthorities.add(new SimpleGrantedAuthority(userRoles.getRole()));
        }
        return grantedAuthorities;
    }
}

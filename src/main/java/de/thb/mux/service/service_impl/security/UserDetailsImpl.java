package de.thb.mux.service.service_impl.security;

import de.thb.mux.data_access.security.UserAccessRepository;
import de.thb.mux.domain.security.UserAccess;
import de.thb.mux.domain.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserAccessRepository userAccessRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccess userAccess = userAccessRepository.findByUsername(username);
        if(userAccess == null){
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(userAccess);
    }

    public Collection<UserDetails> findAll(){
        Collection<UserAccess> accessCollection = userAccessRepository.findAll();
        Collection<UserDetails> userDetailsCollection = new ArrayList<>();
        accessCollection.forEach(userAccess ->{
            (userDetailsCollection).add(new UserPrincipal(userAccess));
        });
        return userDetailsCollection;
    }

    public UserDetails createUserDetails(UserAccess userAccess){
        userAccess.setPassword(passwordEncoder.encode(userAccess.getPassword()));
        return new UserPrincipal(userAccessRepository.save(userAccess));
    }
}

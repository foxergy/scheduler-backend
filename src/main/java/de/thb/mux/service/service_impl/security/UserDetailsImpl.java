package de.thb.mux.service.service_impl.security;

import de.thb.mux.data_access.security.UserAccessRepository;
import de.thb.mux.domain.security.UserAccess;
import de.thb.mux.domain.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsDetailsImpl implements UserDetailsService {

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
}

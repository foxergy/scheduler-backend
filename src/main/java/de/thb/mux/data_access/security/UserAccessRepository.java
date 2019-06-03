package de.thb.mux.data_access.security;

import de.thb.mux.domain.security.UserAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccessRepository extends JpaRepository<UserAccess, Long> {

    UserAccess findByUsername(String username);
}

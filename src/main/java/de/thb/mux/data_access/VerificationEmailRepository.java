package de.thb.mux.data_access;

import de.thb.mux.domain.VerificationEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationEmailRepository extends JpaRepository<VerificationEmail, String> {
}

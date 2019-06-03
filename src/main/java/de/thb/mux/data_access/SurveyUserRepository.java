package de.thb.mux.dataaccess;

import de.thb.mux.domain.SurveyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyUserRepository extends JpaRepository<SurveyUser, Long> {
}

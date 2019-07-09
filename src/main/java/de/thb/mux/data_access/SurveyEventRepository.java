package de.thb.mux.data_access;

import de.thb.mux.domain.SurveyEvent;
import de.thb.mux.domain.security.UserAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SurveyEventRepository extends JpaRepository<SurveyEvent, String> {

    Collection<SurveyEvent> findByUsername(UserAccess username);
}

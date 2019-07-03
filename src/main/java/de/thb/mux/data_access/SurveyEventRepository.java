package de.thb.mux.data_access;

import de.thb.mux.domain.SurveyEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyEventRepository extends JpaRepository<SurveyEvent, String> {
}

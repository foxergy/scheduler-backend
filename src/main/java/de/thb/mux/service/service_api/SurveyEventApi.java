package de.thb.mux.service.service_api;

import de.thb.mux.domain.SurveyEvent;
import de.thb.mux.domain.security.UserAccess;

import java.util.Collection;

public interface SurveyEventApi extends CrudServiceApi<SurveyEvent, String> {

    public Collection<SurveyEvent> findSurveyEventByUsername(UserAccess username);
}

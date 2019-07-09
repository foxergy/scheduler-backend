package de.thb.mux.service.service_impl;

import de.thb.mux.data_access.SurveyEventRepository;
import de.thb.mux.domain.SurveyEvent;
import de.thb.mux.domain.security.UserAccess;
import de.thb.mux.service.service_api.SurveyEventApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class SurveyEventImpl implements SurveyEventApi {

    @Autowired
    private SurveyEventRepository surveyEventRepository;

    @Override
    public SurveyEvent create(SurveyEvent surveyEvent) {

        return surveyEventRepository.save(surveyEvent);
    }

    @Override
    public Collection<SurveyEvent> findAll() {
        return surveyEventRepository.findAll();
    }

    @Override
    public SurveyEvent findByID(String id) {
        return surveyEventRepository.getOne(id);
    }

    @Override
    public SurveyEvent update(SurveyEvent surveyEvent) {
        if(!exists(surveyEvent.getId())){
            throw new EntityNotFoundException("Surveyevent with id: "+ surveyEvent.getId()+ " not found");
        }else{
            return surveyEventRepository.save(surveyEvent);
        }
    }

    @Override
    public Boolean deleteById(String id) {
        if(!exists(id)) {
            throw new EntityNotFoundException("Surveyevent "+id+" does not exist");
        }else {
            surveyEventRepository.deleteById(id);
            return findByID(id)==null;
        }
    }

    @Override
    public Collection<SurveyEvent> findSurveyEventByUsername(UserAccess username) {
        return surveyEventRepository.findByUsername(username);
    }
}
package de.thb.mux.serviceImpl;


import de.thb.mux.dataaccess.SurveyUserRepository;
import de.thb.mux.domain.SurveyUser;
import de.thb.mux.serviceApi.SurveyUserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class SurveyUserImpl implements SurveyUserApi {

    @Autowired
    SurveyUserRepository surveyUserRepository;

    @Override
    public Collection<SurveyUser> findAll() {
        return surveyUserRepository.findAll();
    }

    @Override
    public SurveyUser findByID(Long aLong) {
        return null;
    }

    @Override
    public SurveyUser create(SurveyUser surveyUser) {
        return surveyUserRepository.save(surveyUser);
    }

    @Override
    public SurveyUser update(SurveyUser surveyUser) throws EntityNotFoundException {
        return null;
    }

    @Override
    public boolean deleteById(Long aLong) throws EntityNotFoundException {
        return false;
    }

    @Override
    public boolean delete(SurveyUser surveyUser) throws EntityNotFoundException {
        return false;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }
}

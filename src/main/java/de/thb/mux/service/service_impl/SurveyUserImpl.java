package de.thb.mux.service.service_impl;


import de.thb.mux.data_access.SurveyUserRepository;
import de.thb.mux.domain.SurveyUser;
import de.thb.mux.service.service_api.SurveyUserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class SurveyUserImpl implements SurveyUserApi {

    @Autowired
    private SurveyUserRepository surveyUserRepository;

    @Override
    public Collection<SurveyUser> findAll() {
        return surveyUserRepository.findAll();
    }

    @Override
    public SurveyUser findByID(Long id) {
        return surveyUserRepository.getOne(id);
    }

    @Override
    public SurveyUser create(SurveyUser surveyUser) {
        return surveyUserRepository.save(surveyUser);
    }

    @Override
    public SurveyUser update(SurveyUser surveyUser) throws EntityNotFoundException {
        if (!exists(surveyUser.getId())) {
            throw new EntityNotFoundException("SurveyUser with id: " + surveyUser.getId() + " not found");
        } else {
            return surveyUserRepository.save(surveyUser);
        }
    }

    @Override
    public Boolean deleteById(Long id) throws EntityNotFoundException {
        if (!exists(id)) {
            throw new EntityNotFoundException("SurveyUser " + id + " does not exist");
        } else {
            surveyUserRepository.deleteById(id);
            return findByID(id) == null;
        }
    }
}
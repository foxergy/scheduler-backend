package de.thb.mux.service.service_impl;

import de.thb.mux.data_access.ScheduleRepository;
import de.thb.mux.domain.Schedule;
import de.thb.mux.service.service_api.ScheduleApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class ScheduleImpl implements ScheduleApi {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Collection<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule findByID(Long id) {
        return scheduleRepository.getOne(id);
    }

    @Override
    public Schedule create(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule update(Schedule schedule) throws EntityNotFoundException {
        if(!exists(schedule.getId())){
            throw new EntityNotFoundException("Schedule "+schedule.getId()+" does not exist");
        }else {
            return scheduleRepository.save(schedule);
        }
    }

    @Override
    public Boolean deleteById(Long id) throws EntityNotFoundException {
        if(!exists(id)) {
            throw new EntityNotFoundException("Schedule "+id+" does not exist");
        }else {
            scheduleRepository.deleteById(id);
            return findByID(id)==null;
        }
    }

    @Override
    public Boolean exists(Long id) {
        return findByID(id)!=null;
    }

}

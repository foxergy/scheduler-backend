package de.thb.mux.serviceImpl;

import de.thb.mux.dataaccess.ScheduleRepository;
import de.thb.mux.domain.Schedule;
import de.thb.mux.serviceApi.ScheduleApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class ScheduleImpl implements ScheduleApi {

    @Autowired
    ScheduleRepository scheduleRepository;

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
    public boolean deleteById(Long id) throws EntityNotFoundException {
        if(!exists(id)) {
            throw new EntityNotFoundException("Schedule "+id+" does not exist");
        }else {
            scheduleRepository.deleteById(id);
            return findByID(id)==null;
        }
    }

    @Override
    public boolean delete(Schedule schedule) throws EntityNotFoundException {
        if(!exists(schedule.getId())) {
            throw new EntityNotFoundException("Schedule "+schedule.getId()+" does not exist");
        }else {
            scheduleRepository.deleteById(schedule.getId());
            return findByID(schedule.getId())==null;
        }
    }

    @Override
    public boolean exists(Long id) {
        return findByID(id)!=null;
    }

}

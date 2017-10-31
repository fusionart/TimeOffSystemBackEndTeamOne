package com.tos.timeoffserver.domain.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.tos.timeoffserver.domain.entites.TimeOffDate;

public interface TimeOffDatesRepository extends CrudRepository<TimeOffDate, Long> {
	List<TimeOffDate> findAll();
}

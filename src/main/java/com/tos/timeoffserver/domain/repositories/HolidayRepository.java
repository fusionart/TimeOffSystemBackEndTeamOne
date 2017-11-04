package com.tos.timeoffserver.domain.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tos.timeoffserver.domain.entites.Holiday;

public interface HolidayRepository extends CrudRepository<Holiday, Long> {
	List<Holiday> findByDate(Date data);
}
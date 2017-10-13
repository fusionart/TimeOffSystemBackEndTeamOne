package com.tos.timeoffserver.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tos.timeoffserver.domain.entites.Holidays;

public interface HolidayRepository extends CrudRepository<Holidays, Long>{

}
package com.tos.timeoffserver.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tos.timeoffserver.domain.entites.TimeOffRequest;

public interface TimeOffRequestRepository extends CrudRepository<TimeOffRequest, Long>{

}

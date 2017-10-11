package com.tos.timeoffserver.domain.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tos.timeoffserver.domain.entites.TimeOffRequest;

public interface TimeOffRequestRepository extends CrudRepository<TimeOffRequest, Long>{
	List<TimeOffRequest> findAll();
}

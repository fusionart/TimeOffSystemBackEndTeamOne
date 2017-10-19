package com.tos.timeoffserver.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import com.tos.timeoffserver.domain.entites.ApplicationUser;

public interface UserRepository extends CrudRepository<ApplicationUser, Long>{
	ApplicationUser findByUsername(String username);
}

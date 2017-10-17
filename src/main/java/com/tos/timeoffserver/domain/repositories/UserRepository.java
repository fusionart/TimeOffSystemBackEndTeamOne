package com.tos.timeoffserver.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import com.tos.timeoffserver.domain.entites.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
}

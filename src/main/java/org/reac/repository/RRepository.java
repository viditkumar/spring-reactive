package org.reac.repository;

import org.reac.entity.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RRepository extends ReactiveCrudRepository<Users, String> {

}

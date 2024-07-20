package org.reac.service;

import org.reac.entity.Users;
import org.reac.mapper.RMapper;
import org.reac.model.RDTO;
import org.reac.repository.RRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RService {

    private final RRepository rRepository;
    private final RMapper rMapper;

    public RService(RRepository rRepository, RMapper rMapper) {
        this.rRepository = rRepository;
        this.rMapper = rMapper;
    }

    public Flux<Users> getAllUsers() {
        return rRepository.findAll();
    }

    public Mono<RDTO> getUserById(String id) {
        return rRepository.findById(id).map(user -> new RDTO(user.getId(), user.getName()));
    }

    public Mono<Users> getUserById1(String id) {
        return rRepository.findById(id);
    }
}

package com.dojotestback.domain.user.gateway;

import com.dojotestback.domain.user.User;
import reactor.core.publisher.Mono;

public interface UserGateway {
    Mono<User> findById(String id);
}

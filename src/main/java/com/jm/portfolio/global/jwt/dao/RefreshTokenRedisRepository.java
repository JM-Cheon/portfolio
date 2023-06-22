package com.jm.portfolio.global.jwt.dao;

import com.jm.portfolio.global.jwt.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String> {
}

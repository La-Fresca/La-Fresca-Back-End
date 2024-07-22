package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<Token, Integer> {
    List<Token> findAllAccessTokensByUser(String userId);

    Optional<Token> findByAccessToken(String token);

    Optional<Token> findByRefreshToken(String token);


}

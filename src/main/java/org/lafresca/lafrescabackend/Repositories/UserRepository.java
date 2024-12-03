package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.DTO.UserDTO;
import org.lafresca.lafrescabackend.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    @Query("{ 'Email' : ?0 }")
    Optional<User> findUserByEmail(String email);

    @Query("{ 'CafeId' : ?0 }")
    List<User> findUsersByCafeId(Long cafeId);

    @Query("{ 'Role' : ?0 }")
    List<User> findUsersByRole(String role);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query("{ 'CafeId' : ?0, 'Role' : ?1 }")
    List<User> findUserByCafeIdAndRole(String cafeId, String role);

    @Query("{ 'UserId' : ?0 }")
    User findByUserId(String userId);

    @Query("{ 'email': ?0 }")
    Optional<User> findTopByEmailOrderByIdDesc(String email);

}

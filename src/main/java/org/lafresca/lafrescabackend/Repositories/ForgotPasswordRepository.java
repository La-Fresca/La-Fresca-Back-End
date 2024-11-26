package org.lafresca.lafrescabackend.Repositories;

import org.lafresca.lafrescabackend.Models.ForgotPassword;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ForgotPasswordRepository extends MongoRepository<ForgotPassword,String> {
}

package Sumerge.JavaLearning.Quarter1.Repositories;

import Sumerge.JavaLearning.Quarter1.Entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
//    get user by email
    public Optional<User> findByEmail(String Email);








}

package net.QuinX.quinx.repository;

import net.QuinX.quinx.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String name);
}

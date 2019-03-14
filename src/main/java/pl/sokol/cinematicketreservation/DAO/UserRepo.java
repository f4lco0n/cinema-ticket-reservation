package pl.sokol.cinematicketreservation.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {
    User findUserByUsername(String username);
}

package pl.sokol.cinematicketreservation.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepo extends CrudRepository<UserRoles,Long> {
}

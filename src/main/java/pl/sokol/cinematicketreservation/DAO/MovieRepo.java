package pl.sokol.cinematicketreservation.DAO;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepo extends CrudRepository<Movie,Long> {
    //opcional
    Movie findByMovieName(String name);
}

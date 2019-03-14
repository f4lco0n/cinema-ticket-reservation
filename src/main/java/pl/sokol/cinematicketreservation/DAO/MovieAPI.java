package pl.sokol.cinematicketreservation.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sokol.cinematicketreservation.DAO.Movie;
import pl.sokol.cinematicketreservation.DAO.MovieRepo;

import java.util.List;

@RestController
public class MovieAPI {

    private List<Movie> movieList;

    @Autowired
    MovieRepo movieRepo;

    @GetMapping("/movies")
    public List<Movie> getMovies(){

        return (List<Movie>) movieRepo.findAll();
    }


}

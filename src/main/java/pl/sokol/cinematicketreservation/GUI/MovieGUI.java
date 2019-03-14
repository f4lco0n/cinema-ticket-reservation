package pl.sokol.cinematicketreservation.GUI;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sokol.cinematicketreservation.DAO.Movie;
import pl.sokol.cinematicketreservation.DAO.MovieRepo;
import pl.sokol.cinematicketreservation.DAO.Ticket;
import pl.sokol.cinematicketreservation.DAO.TicketRepo;

import java.util.Collection;
import java.util.Date;

@Route("show-movies")
public class MovieGUI extends VerticalLayout {
    private Label label = new Label("DATA: ");

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    public MovieGUI(MovieRepo repo) {
        Grid<Movie> movieGrid = new Grid<>();
        movieGrid.setItems((Collection<Movie>) repo.findAll());
        movieGrid.addColumn(Movie::getMovieName).setHeader("Movie name");
        movieGrid.addColumn(Movie::getDescription).setHeader("Description");
        movieGrid.addComponentColumn(movie -> new NativeButton("Reserve ticket", nativeButtonClickEvent -> saveTicket(movie))).setHeader("");
        add(movieGrid,label);
    }

    private void saveTicket(Movie movie) {
        label.setText("Zarezerwowano film: " + movie.getMovieName());
        Ticket ticket = new Ticket();
        Date date = new Date();
        ticket.setMovie(movie);
        ticket.setDate(date.toString());
        ticketRepo.save(ticket);
    }

}

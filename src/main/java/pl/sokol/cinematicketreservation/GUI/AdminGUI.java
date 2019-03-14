package pl.sokol.cinematicketreservation.GUI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sokol.cinematicketreservation.DAO.Movie;
import pl.sokol.cinematicketreservation.DAO.MovieRepo;


@Route("admin-panel")
public class AdminGUI extends VerticalLayout {

    private TextField movieName = new TextField("movie name: ");
    private TextArea movieDescription = new TextArea("description");
    private Button addMovie = new Button("add movie");

    @Autowired
    MovieRepo movieRepo;

    public AdminGUI(){
        add(movieName,movieDescription,addMovie);
        addMovie.addClickListener(e -> {
            Movie movie = new Movie();
            movie.setMovieName(movieName.getValue());
            if(movieDescription.getValue().length() > 255){
                Notification.show("Description is too long");
                System.out.println("za dluga");
            } else {
                movie.setDescription(movieDescription.getValue());
                movieRepo.save(movie);
                Notification.show("Movie has been added");
            }

        });
    }
}

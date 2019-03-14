package pl.sokol.cinematicketreservation.DAO;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String movieName;
    @Column
    private String description;

    @OneToMany(mappedBy = "movie")
    private Set<Ticket> ticketSet;



    public Movie(){}

    public Movie(String movieName, String description) {
        this.movieName = movieName;
        this.description = description;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Ticket> getTicketSet() {
        return ticketSet;
    }

    public void setTicketSet(Set<Ticket> ticketSet) {
        this.ticketSet = ticketSet;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

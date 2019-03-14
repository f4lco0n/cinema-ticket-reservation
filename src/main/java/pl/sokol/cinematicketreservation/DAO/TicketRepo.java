package pl.sokol.cinematicketreservation.DAO;

import org.springframework.data.repository.CrudRepository;

public interface TicketRepo extends CrudRepository<Ticket,Long> {
}

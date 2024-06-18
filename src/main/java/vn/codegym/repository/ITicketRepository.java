package vn.codegym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.entity.MovieSchedule;
import vn.codegym.entity.Ticket;
import vn.codegym.entity.User;

import java.util.List;
import java.util.Optional;

public interface ITicketRepository extends JpaRepository<Ticket, Long> {
    Page<Ticket> findAll(Pageable pageable);

    Page<Ticket> findByMovieSchedule_Movie_NameMovieContaining(Pageable pageable, String name);
    Optional<List<Ticket>> findTicketsByUser(User user);
}

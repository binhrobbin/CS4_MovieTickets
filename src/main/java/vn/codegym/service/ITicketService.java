package vn.codegym.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.entity.Movie;
import vn.codegym.entity.Ticket;
import vn.codegym.entity.User;

import java.util.List;
import java.util.Optional;
public interface ITicketService {
    List<Ticket> findAll();
    void save(Ticket ticket);

    void update(long id,Ticket ticket);
    void update(Ticket ticket);

    void remove(long id);
    Optional<Ticket> findById(long id);
    Page<Ticket> findAll(Pageable pageable);
    Page<Ticket> findByMovieSchedule_Movie_NameMovieContaining(Pageable pageable, String name);
    Optional<List<Ticket>> findTicketsByUser(User user);

}

package vn.codegym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.codegym.entity.Ticket;
import vn.codegym.entity.User;
import vn.codegym.repository.ITicketRepository;
import vn.codegym.service.ITicketService;

import java.util.List;
import java.util.Optional;
@Service
public class TicketService implements ITicketService {
    @Autowired
    private ITicketRepository ticketRepository;
    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void update(long id, Ticket ticket) {

    }

    @Override
    public void update(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void remove(long id) {
        ticketRepository.deleteById(id);
    }


    @Override
    public Optional<Ticket> findById(long id) {
        return ticketRepository.findById(id);
    }
    @Override
    public Page<Ticket> findByMovieSchedule_Movie_NameMovieContaining(Pageable pageable, String name) {
        return ticketRepository.findByMovieSchedule_Movie_NameMovieContaining(pageable,name);
    }

    @Override
    public Optional<List<Ticket>> findTicketsByUser(User user) {
        return ticketRepository.findTicketsByUser(user);
    }

    @Override
    public Page<Ticket> findAll(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

}

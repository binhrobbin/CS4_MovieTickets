package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.entity.Ticket;
import vn.codegym.service.impl.TicketService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tickets")
public class R_TicketController {
    @Autowired
    TicketService ticketService;
    @GetMapping
    public ResponseEntity<Page<Ticket>> listTickets(@PageableDefault(value = 3) Pageable pageable){
        Page<Ticket> list = ticketService.findAll(pageable);
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Ticket>> search(@PageableDefault(value = 3) Pageable pageable, @RequestParam(value = "s",required = false) Optional<String> key){
        Page<Ticket> list;
        if (key.isPresent()){
            list = ticketService.findByMovieSchedule_Movie_NameMovieContaining(pageable,key.get());
        } else {
        list = ticketService.findAll(pageable);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}

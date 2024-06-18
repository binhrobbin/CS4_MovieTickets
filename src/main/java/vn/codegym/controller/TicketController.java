package vn.codegym.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.codegym.dto.TicketDto;
import vn.codegym.dto.UserDto;
import vn.codegym.entity.MovieSchedule;
import vn.codegym.entity.Ticket;
import vn.codegym.entity.User;
import vn.codegym.repository.IUserRepository;
import vn.codegym.service.IMovieScheduleService;
import vn.codegym.service.ITicketService;
import vn.codegym.service.IUserService;

import javax.swing.text.Document;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IMovieScheduleService movieScheduleService;
    @Autowired
    private ITicketService ticketService;
    @GetMapping("/list")
    public ModelAndView showTicket(@SessionAttribute(value = "ticket", required = false) TicketDto ticket) {
//    public ModelAndView showCart(@ModelAttribute(value = "cart") CartDto cart) {
        return new ModelAndView("ticket/list-ticket", "ticketList", ticketService.findAll());
    }
    @GetMapping("/myList")
    public ModelAndView addTicket2(Authentication authentication, Model model) {
//    public ModelAndView showCart(@ModelAttribute(value = "cart") CartDto cart) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.findUserByUsername(username);
        Optional<List<Ticket>> ticketList = ticketService.findTicketsByUser(user);
        long sum = 0;
        for (Ticket ticket1: ticketList.get()) {
            sum+= (ticket1.getQuantityTicket() * ticket1.getMovieSchedule().getRoom().getPriceRoom());
        }
        model.addAttribute("totalPrice", sum);
        return new ModelAndView("/ticket/list-ticket", "ticketList", ticketList.get());
    }

    @GetMapping("/add/{id}")
    public ModelAndView addTicket(@PathVariable Long id,
                                  @RequestParam("counter") String counter,
                                  Authentication authentication, Model model) {
//    public ModelAndView showCart(@ModelAttribute(value = "cart") CartDto cart) {
        Ticket ticket = new Ticket();
        ticket.setBooking_day(LocalDate.now() + "");
        ticket.setQuantityTicket(Integer.parseInt(counter));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.findUserByUsername(username);
        ticket.setUser(user);
        Optional<MovieSchedule> optionalMovieSchedule = movieScheduleService.findById(id);
        ticket.setMovieSchedule(optionalMovieSchedule.get());
        ticketService.save(ticket);
        Optional<List<Ticket>> ticketList = ticketService.findTicketsByUser(user);
        long sum = 0;
        for (Ticket ticket1: ticketList.get()) {
            sum+= (ticket1.getQuantityTicket() * ticket1.getMovieSchedule().getRoom().getPriceRoom());
        }
        model.addAttribute("totalPrice", sum);
        return new ModelAndView("/ticket/list-ticket", "ticketList", ticketList.get());
    }
}

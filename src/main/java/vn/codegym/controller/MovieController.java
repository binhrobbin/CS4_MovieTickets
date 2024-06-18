package vn.codegym.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.codegym.dto.MovieDto;
import vn.codegym.dto.MovieScheduleDto;
import vn.codegym.dto.TicketDto;
import vn.codegym.entity.Movie;
import vn.codegym.entity.MovieSchedule;
import vn.codegym.service.IMovieScheduleService;
import vn.codegym.service.IMovieService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movie")
@SessionAttributes("ticket") //khai báo session có tên là 'cart'
public class MovieController {
    @ModelAttribute("ticket")
    public TicketDto initTicket(){
        return new TicketDto();
    }
    @Autowired
    private IMovieService movieService;
    @Autowired
    private IMovieScheduleService movieScheduleService;

    @GetMapping("/list")
    public ModelAndView showMovieList1() {
        return new ModelAndView("/movie/movie",
                "movieList", movieService.findAll());
    }
//    @GetMapping("/add/{id}")
//    public String addToCart(@PathVariable Long id,
//                            @SessionAttribute("ticket") TicketDto ticket){
//        Optional<MovieSchedule> movieScheduleOptional =  movieScheduleService.findById(id);
//        if(movieScheduleOptional.isPresent()) {
//            MovieScheduleDto movieScheduleDto = new MovieScheduleDto();
//            BeanUtils.copyProperties(movieScheduleOptional.get(), movieScheduleDto);
//            ticket.addMovie(movieScheduleDto);
//        }
//        return "redirect:/ticket";
//
//    }

    @GetMapping("/search")
    public ModelAndView searchMovieList1(@RequestParam("keywordSearch") String keyword) {
        Optional<List<Movie>> movieOptional = movieService.findMoviesByNameMovieContaining(keyword);
        List<MovieDto> movieDtoList = new ArrayList<>();
        if (movieOptional.isPresent()) {
            for (Movie movie: movieOptional.get()) {
                MovieDto movieDto = new MovieDto();
                BeanUtils.copyProperties(movie, movieDto);
                movieDtoList.add(movieDto);
            }
        }
        return new ModelAndView("/movie/movie",
                "movieList", movieDtoList);
    }
    @GetMapping("/search-type")
    public ModelAndView searchMovieListByType(@RequestParam("type") String type) {
        List<Movie> movieList = movieService.findMoviesByTypeMovieContaining(type);
        List<MovieDto> movieDtoList = new ArrayList<>();
        System.out.println("hello");
            for (Movie movie: movieList) {
                MovieDto movieDto = new MovieDto();
                BeanUtils.copyProperties(movie, movieDto);
                movieDtoList.add(movieDto);
            }
        return new ModelAndView("/movie/movie",
                "movieList", movieDtoList);
    }



    @GetMapping("/manager")
    public ModelAndView showManagerPage() {

        return new ModelAndView("/admin/cinema");
    }
}

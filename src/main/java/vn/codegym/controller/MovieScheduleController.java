package vn.codegym.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.codegym.dto.MovieScheduleDto;
import vn.codegym.dto.TicketDto;
import vn.codegym.entity.MovieSchedule;
import vn.codegym.service.IMovieScheduleService;
import vn.codegym.service.IMovieService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movie-schedule")
@SessionAttributes("ticket") //khai báo session có tên là 'cart'
public class MovieScheduleController {
    @Autowired
    private IMovieScheduleService movieScheduleService;
    @Autowired
    private IMovieService movieService;

    @GetMapping("/list")
    public ModelAndView showMovieScheduleList() {
        List<MovieSchedule> movieScheduleList = movieScheduleService.findAll();
        List<MovieScheduleDto> movieScheduleDtoList = new ArrayList<>();
        for (MovieSchedule movieSchedule: movieScheduleList) {
            MovieScheduleDto movieScheduleDto = new MovieScheduleDto();
            BeanUtils.copyProperties(movieSchedule, movieScheduleDto);
            movieScheduleDtoList.add(movieScheduleDto);
        }
        return new ModelAndView("/movie-schedule/movie-schedule-all",
                "movieScheduleDtoAllList", movieScheduleDtoList);
    }

    @GetMapping("/add/{id}")
    public ModelAndView addToTicket(@PathVariable Long id){
        List<MovieSchedule> movieScheduleList = movieScheduleService.findMovieSchedulesByMovie(movieService.findById(id));
        List<MovieScheduleDto> movieScheduleDtoList = new ArrayList<>();
        for (MovieSchedule movieSchedule: movieScheduleList) {
            MovieScheduleDto movieScheduleDto = new MovieScheduleDto();
            BeanUtils.copyProperties(movieSchedule, movieScheduleDto);
            movieScheduleDtoList.add(movieScheduleDto);
        }
        return new ModelAndView("/movie-schedule/movie-schedule-list",
                "movieScheduleDtoList", movieScheduleDtoList);

    }
}

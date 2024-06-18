package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import vn.codegym.entity.MovieSchedule;
import vn.codegym.entity.Room;
import vn.codegym.service.IMovieScheduleService;
import vn.codegym.service.IRoomService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/schedules")
public class R_MovieScheduleController {
    @Autowired
    IMovieScheduleService movieScheduleService;
    @Autowired
    IRoomService roomService;


    @GetMapping
    public ResponseEntity<List<MovieSchedule>> listMovieSchedules(){
        List<MovieSchedule> list = movieScheduleService.findAll();
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovieSchedule> createMovieSchedule(@RequestBody MovieSchedule movieSchedule){
//        Optional<MovieSchedule> optionalMovieSchedule = movieScheduleService.findByTimeSlotAndRoom(movieSchedule.getTimeSlot(),movieSchedule.getRoom());
//        if (optionalMovieSchedule.isPresent()){
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
        movieScheduleService.save(movieSchedule);
        return new ResponseEntity<>(movieSchedule, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieSchedule> findMovieSchedule(@PathVariable Long id){
        System.out.println(id);
        Optional<MovieSchedule> MovieScheduleOptional = movieScheduleService.findById(id);
        if (!MovieScheduleOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MovieScheduleOptional.get(),HttpStatus.OK);
    }
    @GetMapping("/{id}/{date}")
    public ResponseEntity<List<MovieSchedule>> findTime(@PathVariable("id") Long id, @PathVariable("date") Date date){
        Optional<Room> room = roomService.findById(id);
//        Date date = Date.valueOf(str);
        return new ResponseEntity<>(movieScheduleService.findByScreeningDayAndRoom(date,room.get()),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieSchedule> deleteMovieSchedule(@PathVariable Long id){
        Optional<MovieSchedule> MovieScheduleOptional = movieScheduleService.findById(id);
        if (!MovieScheduleOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        movieScheduleService.remove(id);
        return new ResponseEntity<>(MovieScheduleOptional.get(),HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MovieSchedule> updateMovieSchedule(@PathVariable Long id, @RequestBody MovieSchedule movieSchedule){
        Optional<MovieSchedule> MovieScheduleOptional = movieScheduleService.findById(id);
        if (!MovieScheduleOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        movieScheduleService.update(movieSchedule);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

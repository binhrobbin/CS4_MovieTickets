package vn.codegym.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String booking_day;
    private int quantityTicket;
    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
//    @JoinColumn(name = "movie_schedule_id", referencedColumnName = "id")
    private MovieSchedule movieSchedule;

    public Ticket() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getBooking_day() {
        return booking_day;
    }

    public void setBooking_day(String booking_day) {
        this.booking_day = booking_day;
    }

    public int getQuantityTicket() {
        return quantityTicket;
    }

    public void setQuantityTicket(int quantityTicket) {
        this.quantityTicket = quantityTicket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MovieSchedule getMovieSchedule() {
        return movieSchedule;
    }

    public void setMovieSchedule(MovieSchedule movieSchedule) {
        this.movieSchedule = movieSchedule;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", booking_day='" + booking_day + '\'' +
                ", quantityTicket=" + quantityTicket +
                ", user=" + user +
                ", movieSchedule=" + movieSchedule +
                '}';
    }
}

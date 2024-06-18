package vn.codegym.dto;

import vn.codegym.entity.MovieSchedule;
import vn.codegym.entity.User;

public class TicketDto {
    private Long id;
    private String booking_day;
    private int quantityTicket;
    private User user;
    private MovieSchedule movieSchedule;

    public TicketDto() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    //    public void addMovie(MovieScheduleDto movieScheduleDto) {
//        if (movieScheduleDtoMap.containsKey(movieScheduleDto)) {
//            //update value + 1
//            Integer currentValue = movieScheduleDtoMap.get(movieScheduleDto);
////            productMap.put(productDto, currentValue + 1);
//            movieScheduleDtoMap.replace(movieScheduleDto, currentValue + 1);
//        } else {
//            movieScheduleDtoMap.put(movieScheduleDto, 1); //Lan dau dc them vao
//        }
//    }
//    public void deleteMovie(MovieScheduleDto movieScheduleDto) {
//        if (movieScheduleDtoMap.containsKey(movieScheduleDto)) {
//            //update value + 1
//            Integer currentValue = movieScheduleDtoMap.get(movieScheduleDto);
////            productMap.put(productDto, currentValue + 1);
//            movieScheduleDtoMap.replace(movieScheduleDto, currentValue - 1);
//        } else {
//            System.out.println("No Data");
////            movieScheduleMap.put(movieDto, 1); //Lan dau dc them vao
//        }
//    }
//private boolean checkItemInTicket(MovieScheduleDto movieScheduleDto){
//    for (Map.Entry<MovieScheduleDto,Integer> entry : movieScheduleDtoMap.entrySet()){
//        if (entry.getKey().getId().equals(movieScheduleDto.getId())){
//            return true;
//        }
//    }
//    return false;
//}
//    private Map.Entry<MovieScheduleDto, Integer> selectItemInTicket(MovieScheduleDto movieScheduleDto){
//        for (Map.Entry<MovieScheduleDto, Integer> entry : movieScheduleDtoMap.entrySet()) {
//            if(entry.getKey().getId().equals(movieScheduleDto.getId())){
//                return entry;
//            }
//        }
//        return null;
//    }
//    public void addMovieSchedule(MovieScheduleDto movieScheduleDto){
//        if (!checkItemInTicket(movieScheduleDto)){
//            movieScheduleDtoMap.put(movieScheduleDto,1);
//        }else {
//            Map.Entry<MovieScheduleDto,Integer> itemEntry = selectItemInTicket(movieScheduleDto);
//            Integer newQuantity = itemEntry.getValue()+1;
//            movieScheduleDtoMap.replace(itemEntry.getKey(),newQuantity);
//        }
//    }
//
//    public void subtractMovie(MovieScheduleDto movieScheduleDto){
//        if (checkItemInTicket(movieScheduleDto)) {
//            Map.Entry<MovieScheduleDto, Integer> itemEntry = selectItemInTicket(movieScheduleDto);
//            if (itemEntry.getValue() > 1) {
//                Integer newQuantity = itemEntry.getValue() - 1;
//                movieScheduleDtoMap.replace(itemEntry.getKey(), newQuantity);
//            }else movieScheduleDtoMap.remove(itemEntry.getKey());
//        }
//    }
}

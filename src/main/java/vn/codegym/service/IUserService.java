package vn.codegym.service;

import vn.codegym.entity.User;

public interface IUserService {
    void save(User user);
    User findUserByUsername(String username);


}

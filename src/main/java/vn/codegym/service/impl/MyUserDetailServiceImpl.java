package vn.codegym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.codegym.entity.MyUserDetail;
import vn.codegym.entity.User;
import vn.codegym.repository.IUserRepository;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if(!username.equals(user.getUsername()) || user == null){
            throw new UsernameNotFoundException("User name: "+username + " not found");
        }

        return new MyUserDetail(user);
    }
}

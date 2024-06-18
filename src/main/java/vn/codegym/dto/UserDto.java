package vn.codegym.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {

    @Size(min = 3, max = 255)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z_0-9\\s]+$", message = "{error.name.regex}")
    private String name;
    @Size(min = 3, max = 255)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z_0-9\\s]+$", message = "{error.name.regex}")
    private String username;
    @Size(min = 6, max = 255)
    @NotBlank
    private String password;
    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

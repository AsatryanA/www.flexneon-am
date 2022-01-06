package am.flexneon.www.model.dto;

import am.flexneon.www.model.Role;
import am.flexneon.www.model.enumModels.UserStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private UserStatus status;
    private Collection<Role> roles;
}

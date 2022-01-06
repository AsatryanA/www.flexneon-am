package am.flexneon.www.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class UserResponse {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String username;
    private List<String> roles;

    public UserResponse(String token, Integer id, String username, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
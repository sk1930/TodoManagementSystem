package sk.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {
// 171. Configure JWT in Spring Security


    private String accessToken;
    private String tokenType = "Bearer";
}

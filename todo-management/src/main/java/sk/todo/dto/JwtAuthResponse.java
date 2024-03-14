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
    private String role; // 175. Change Login Rest Api to return role along with token


}

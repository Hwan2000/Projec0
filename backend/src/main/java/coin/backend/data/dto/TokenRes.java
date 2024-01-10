package coin.backend.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/*
* 로그인 시, 토큰 생성해서 전달에 사용
* */
@Data
@AllArgsConstructor
public class TokenRes {
    private String accessToken;
    private String refreshToken;
}

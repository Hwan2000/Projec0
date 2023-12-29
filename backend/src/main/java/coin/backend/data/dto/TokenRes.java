package coin.backend.data.dto;

import lombok.Data;

/*
* 로그인 시, 토큰 생성해서 전달에 사용
* */
@Data
public class TokenRes {
    private String accessToken;
    private String refreshToken;
}

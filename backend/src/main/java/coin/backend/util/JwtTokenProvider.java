package coin.backend.util;

import com.fasterxml.jackson.databind.DatabindException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${spring.jwt.secret}")
    private String secretKey;
    
    /*
    * secretKey로 인코딩
    * */
    @PostConstruct
    private void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /*
    * refresh token 생성
    * */
    public String createRefreshToken(String userName){
        Date now = new Date();

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes((5)).toMillis()))
                .compact();
    }
}

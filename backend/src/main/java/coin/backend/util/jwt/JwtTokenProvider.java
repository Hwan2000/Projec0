package coin.backend.util.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
public class JwtTokenProvider {

    /*
    * 사용할 key
    */
    private Key key;
    
    /*
    * secretKey 인코딩
    * */
    @PostConstruct
    private void init(){
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    /*
    * access token 생성
    * 리턴값: String
    * userUUID subject 설정해서 만든 뒤, 유효시간은 10분
    * */
    public String createAccessToken(String userUUID){
        Date now = new Date();

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes((10)).toMillis()))
                .setSubject(userUUID)
                .signWith(key)
                .compact();
    }
    
    /*
    * refresh 토큰 생성
    * 리턴값: String
    * 인증 정보 없이 유효하기만 하면 됨
    * 서버에 저장된 뒤 사용 예정
    * */
    public String createRefreshToken(){
        Date now = new Date();

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(5).toMillis()))
                .signWith(key)
                .compact();
    }

    /*
    * 토큰 유효성 검사 코드
    * 리턴값: bool
    * 토큰이 유효하면 true 리턴, 유효기간 만료 등의 이유로 토큰이 유효하지 않으면 false return
    * */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            return false;
        }
    }
    
    /*
    * jwt에서 subject(user uuid) 추출
    * */
    public String getUUID(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return claimsJws.getBody().getSubject();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            return null;
        }
    }
}

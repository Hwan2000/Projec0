package coin.backend.util.jwt;

import coin.backend.util.security.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    /*
    * 사용할 key
    */
    private Key key;
    private final UserDetailsServiceImpl userDetailsService;
    
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

    /**
     * @param token : 토큰을 받아서 유효한 토큰인지 아닌지 확인
     * @return : 토큰이 유효한지 아닌지 리턴
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * @param token : 토큰을 받아서 내부의 UUID를 리턴한다.
     * @return : 토큰 내부의 UUID
     */
    public String getUUID(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return claimsJws.getBody().getSubject();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * @param token : 토큰을 받아서 Authentication을 만든다.
     * @return : 생성된 usernamePasswordAuthentication 객체.
     */
    public Authentication getAuthentication(String token){

        UserDetails userDetails = userDetailsService.loadUserByUserUUID(token);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}

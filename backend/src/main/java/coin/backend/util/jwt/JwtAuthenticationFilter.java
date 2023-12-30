package coin.backend.util.jwt;

import coin.backend.data.domain.RefreshToken;
import coin.backend.data.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    
    /*
    * 토큰 유효성 검사 필터
    * 필터는 단 한번만 실행
    * */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException{
        
        // 토큰 추출
        String accessToken = extractAccessToken(request);
        String refreshToken = extractRefreshToken(request);

//        System.out.println(accessToken + ' ' + refreshToken + '\n');

        // 만약 access 토큰이 있으면
        if(accessToken != null){
            if(jwtTokenProvider.validateToken(accessToken)){
                
                // 인증 객체 생성 (나중에 업데이트 예정)
                Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
                // 토큰 새로고침한 후에 응답 헤더에 넣음
                String newAccessToken = jwtTokenProvider.createAccessToken(jwtTokenProvider.getUUID(accessToken));
                response.setHeader("accessToken", "Bearer " + newAccessToken);
                
                // 다음 필터 진행
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
            return;
        }
        
        // 만약 refresh 토큰이 있으면
        if(refreshToken != null){
            if(jwtTokenProvider.validateToken(refreshToken)){

                // 인증 객체 생성(나중에 업데이트 예정)
                Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // 리프레시 토큰을 가진 객체를 refreshToken 리포지토리에서 찾아 옴
                Optional<RefreshToken> byValue = refreshTokenRepository.findByValue(refreshToken);
                // 만약 해당 객체가 있으면
                if(byValue.isPresent()) {

                    // 리포지토리에서 가져온 refresh 토큰 객체
                    RefreshToken byRefreshToken = byValue.get();

                    // access 토큰과 refresh 토큰 생성
                    String newRefreshToken = jwtTokenProvider.createRefreshToken();
                    String newAccessToken = jwtTokenProvider.createAccessToken(byRefreshToken.getUuid().toString());

                    // refreshToken 리포지토리에 refresh 토큰 값 업데이트 
                    byRefreshToken.updateValue(newRefreshToken);
                    refreshTokenRepository.save(byRefreshToken);
                    
                    // 응답 헤더에 refresh 토큰과 액세스 토큰 넣어 줌
                    response.setHeader("accessToken", "Bearer " + newAccessToken);
                    response.setHeader("refreshToken", "Bearer " + newRefreshToken);

                    filterChain.doFilter(request, response);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
            return;
        }

        filterChain.doFilter(request,response);
    }

    private String extractAccessToken(HttpServletRequest httpServletRequest){
        return httpServletRequest.getHeader("accessToken");
    }

    private String extractRefreshToken(HttpServletRequest httpServletRequest){
        return httpServletRequest.getHeader("refreshToken");
    }
}

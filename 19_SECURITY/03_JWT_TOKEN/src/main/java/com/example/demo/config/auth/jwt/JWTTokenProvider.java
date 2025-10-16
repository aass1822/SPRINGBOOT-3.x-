package com.example.demo.config.auth.jwt;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.domain.dtos.UserDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JWTTokenProvider {

    @Autowired
    private UserRepository userRepository;

    //Key
    private Key key ;

    public Key getKey(){
        return key;
    }
    @PostConstruct
    public void init(){
        byte[] keyBytes = KeyGenerator.keyGen();
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenInfo generateToken(Authentication authentication){

        //계정정보 - 계정명 / auth(role)
        String authorities = authentication  .getAuthorities()// Collection<SimpleGrantedAuthority> authorities 반환
                .stream()   // Stream 함수 사용예정
                .map((role)->{return role.getAuthority();}) // 각각 GrantedAuthoriy("ROLE~")들을 문자열값으로 반환해서 map처리
                .collect(Collectors.joining(",")); //각각의 role(ROLE_ADMIN ROLE_USER...) 를 ','를 기준으로 묶음 ("ROLE_USER,ROLE_ADMIN")
        //AccessToken(서버의 서비스를 이용제한 )
        long now = (new Date()).getTime();  //현재시간
        String accessToken = Jwts.builder()
                            .setSubject(authentication.getName()) //본문 TITLE
                            .setExpiration(new Date(now + JWTProperties.ACCESS_TOKEN_EXPIRATION_TIME )) //만료날짜(밀리초단위)
                            .signWith(key, SignatureAlgorithm.HS256) // 서명값
                            .claim("username",authentication.getName()) // 본문 내용
                            .claim("auth",authorities) // 본문 내용
                            .compact();
        //RefreshToken(AccessToken 만료시 갱신처리)
        String refreshToken = Jwts.builder()
                            .setSubject("Refresh_Token_Title") //본문 TITLE
                            .setExpiration(new Date(now + JWTProperties.REFRESH_TOKEN_EXPIRATION_TIME )) //만료날짜(밀리초단위)
                            .signWith(key, SignatureAlgorithm.HS256) // 서명값
                            .compact();

        //TokenInfo
        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) throws ExpiredJwtException
    {
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();

        String username = claims.getSubject(); // username
        username = (String)claims.get("username"); //username
        String auth = (String)claims.get("auth"); //"ROLE_USER,ROLE_ADMIN"

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String roles [] = auth.split(","); //["ROLE_ADMIN","ROLE_USER"]
        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }


        PrincipalDetails principalDetails = null;
        UserDto dto = null;
        if(userRepository.existsById(username)){

            dto = new UserDto();
            dto.setUsername(username);
            dto.setRole(auth);
            dto.setPassword(null);

            principalDetails = new PrincipalDetails(dto);
        }

        if(principalDetails!=null) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, authorities);
            return authenticationToken;
        }

        return null;


    }

    public boolean validateToken(String token) throws Exception
    {
        boolean isValid = false;
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            isValid = true;
        }catch(ExpiredJwtException e) {
            // 토큰 만료시 예외
            log.info("[ExpiredJwtException].." + e.getMessage());
            throw new ExpiredJwtException(null,null,null); //header,claims,message
        }
        return isValid;
    }


}

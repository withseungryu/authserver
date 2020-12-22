package com.ssg.auth.jwt;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {


//    @Value("${jwt.secret}")
    private String secretKey = "sksmswlrmadurltmakdlfrpdlxmdptjroqkfgksmswnd"; //나는지금여기스마일게이트에서개발하는중

    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //토큰 유효시간
        Long validationTime = 1000 * 60L * 60L *2L;
//
//        //만료 시간
        Date expiredTime = new Date();
        expiredTime.setTime(expiredTime.getTime()+ validationTime);
        System.out.println(authentication.getName());
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiredTime)
                .compact();
    }

//    public String generateRefreshToken(Authentication authentication) {
//        final String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//
//        //토큰 유효시간
//        Long validationTime = 1000 * 100L * 60L *2L;
////
////        //만료 시간
//        Date expiredTime = new Date();
//        expiredTime.setTime(expiredTime.getTime()+ validationTime);
//        System.out.println(authentication.getName());
//        return Jwts.builder()
//                .setSubject(authentication.getName())
//                .claim("auth", authorities)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(expiredTime)
//                .compact();
//    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = getEmailFromToken(token);
        return (
                email.equals(userDetails.getUsername())
                        && !isTokenExpired(token));
    }

    public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {

        final JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();

        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        final Claims claims = claimsJws.getBody();

        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }



}

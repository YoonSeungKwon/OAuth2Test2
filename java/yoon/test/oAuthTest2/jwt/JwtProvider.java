package yoon.test.oAuthTest2.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import yoon.test.oAuthTest2.domain.Accounts;
import yoon.test.oAuthTest2.service.AccountDetailService;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final AccountDetailService accountDetailService;
    private final String SECRET_KEY = "yyoonnssgg11223344";

    private final long exp_acc = 30 * 60 * 1000l;

    private final long exp_ref = 2 * 7 * 24 * 60 * 60 * 1000l;

    public String createAccessToken(Accounts accounts){
        Claims claims = Jwts.claims()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp_acc));

        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JSON")
                .setClaims(claims)
                .claim("email", accounts.getEmail())
                .claim("name", accounts.getName())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    public String createRefreshToken(){
        Claims claims = Jwts.claims()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp_ref));

        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JSON")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = accountDetailService.loadUserByUsername(this.getId(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    public String getId(String token){                                     //get Id From Token
        return (String)Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("email");
    }

    public boolean validateToken(String token){                             //Validate Check
        try{
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return !claims.getExpiration().before(new Date());
        }catch(Exception e){
            return false;
        }
    }

    public String resolveAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("X-Refresh-Token");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}


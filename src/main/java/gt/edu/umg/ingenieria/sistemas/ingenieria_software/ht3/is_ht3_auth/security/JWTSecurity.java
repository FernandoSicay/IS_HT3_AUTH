package gt.edu.umg.ingenieria.sistemas.ingenieria_software.ht3.is_ht3_auth.security;

import gt.edu.umg.ingenieria.sistemas.ingenieria_software.ht3.is_ht3_auth.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JWTSecurity {
    @Value("${spring.security.oauth2.resourceserver.jwt.key-value}")
    private String key;

    @Value("${spring.security.oauth2.resourceserver.jwt.expire-time-seconds}")
    private int timeExpire;

    public String createJWT(String user, String pass) throws RuntimeException, CustomException {

        long nowMillis = System.currentTimeMillis();
        Date date = new Date(nowMillis);

        long timeExpire = this.timeExpire * 1000;
        Date dateExpire = new Date(timeExpire + nowMillis);

        Claims claims = Jwts.claims().setSubject(user).setSubject(pass);

        if(validateKey()){
            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(date)
                    .setExpiration(dateExpire)
                    .signWith(SignatureAlgorithm.HS256,key.getBytes()).compact();
        }else {
            throw new CustomException("secret-key, no cumple con los requisitos mínimos de seguridad.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean validateKey() {

        Pattern pat = Pattern.compile("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
        Matcher matcher = pat.matcher(this.key);

        return matcher.matches();
    }
}

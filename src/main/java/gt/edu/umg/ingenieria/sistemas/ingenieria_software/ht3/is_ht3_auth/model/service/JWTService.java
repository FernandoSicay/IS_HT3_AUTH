package gt.edu.umg.ingenieria.sistemas.ingenieria_software.ht3.is_ht3_auth.model.service;

import gt.edu.umg.ingenieria.sistemas.ingenieria_software.ht3.is_ht3_auth.exception.CustomException;
import gt.edu.umg.ingenieria.sistemas.ingenieria_software.ht3.is_ht3_auth.model.dto.ResponseDto;
import gt.edu.umg.ingenieria.sistemas.ingenieria_software.ht3.is_ht3_auth.security.JWTSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class JWTService {

    @Autowired
    private JWTSecurity security;

    public ResponseEntity<ResponseDto> signIn(HttpServletRequest request) throws CustomException {

        try {
            String user = request.getParameter("username");
            System.out.println(request.getParameter("username"));
            String saludo = String.format("Bienvenido %s!!", user);
            String key = this.security.createJWT(user, request.getParameter("password"));
            System.out.println(saludo);

            return new ResponseEntity<>(new ResponseDto(200,"success",key), HttpStatus.OK);
        }catch (CustomException ex){
            return new ResponseEntity<>(new ResponseDto(400,"secret-key, no cumple con los requisitos mínimos de seguridad"), HttpStatus.BAD_REQUEST);
        }
    }
}

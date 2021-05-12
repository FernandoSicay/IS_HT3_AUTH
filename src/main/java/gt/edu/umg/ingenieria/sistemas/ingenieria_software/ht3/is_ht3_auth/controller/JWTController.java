package gt.edu.umg.ingenieria.sistemas.ingenieria_software.ht3.is_ht3_auth.controller;


import gt.edu.umg.ingenieria.sistemas.ingenieria_software.ht3.is_ht3_auth.exception.CustomException;
import gt.edu.umg.ingenieria.sistemas.ingenieria_software.ht3.is_ht3_auth.model.dto.ResponseDto;
import gt.edu.umg.ingenieria.sistemas.ingenieria_software.ht3.is_ht3_auth.model.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class JWTController {
    @Autowired
    private JWTService service;

    @RequestMapping( value = "/auth", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResponseDto> auth(HttpServletRequest request) throws CustomException {
        return this.service.signIn(request);
    }
}

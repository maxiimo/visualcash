package com.backend.visualcash.security.controller;

import com.backend.visualcash.dto.Mensaje;
import com.backend.visualcash.email.dto.EmailValuesDTO;
import com.backend.visualcash.email.service.EmailService;
import com.backend.visualcash.security.dto.AccountUser;
import com.backend.visualcash.security.dto.ChangePasswordDTO;
import com.backend.visualcash.security.dto.JwtDto;
import com.backend.visualcash.security.entity.Rol;
import com.backend.visualcash.security.entity.Usuario;
import com.backend.visualcash.security.enums.RolNombre;
import com.backend.visualcash.security.jwt.JwtProvider;
import com.backend.visualcash.security.service.RolService;
import com.backend.visualcash.security.service.UsuarioService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolService rolService;

    @Autowired
    EmailService emailService;
    
    @Value("${url}")
    private String url; 
    
    @PostMapping("/signin")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody AccountUser loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos invalidos."), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getEmail(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity(jwt, HttpStatus.OK);
    }
     @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity(jwt, HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> nuevo(@Valid @RequestBody AccountUser accUser, BindingResult bindingResult,
                                   HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String emailUser = accUser.getEmail();
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos invalidos."), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(emailUser))
            return new ResponseEntity(new Mensaje("Este correo electrónico ya se encuentra registrado."), HttpStatus.BAD_REQUEST);
        String passwordUser = accUser.getPassword();
        
        Usuario usuario =
                new Usuario(emailUser,passwordEncoder.encode(passwordUser),
                        RandomString.make(64), false, (int) new Date().getTime());
        Set<Rol> roles = new HashSet<>();
        if(!rolService.existsByRolNombre(RolNombre.ROLE_USER))
            rolService.save(new Rol(RolNombre.ROLE_USER));
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        /*if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());*/
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        String Url = getSiteURL(request)+"/auth/verify?code="+usuario.getVerificationCode();
        EmailValuesDTO dto = new EmailValuesDTO(emailUser,"ACTIVACION DE CUENTA","Haz click <a href='"+Url+"'>aquí</a> para activar tu cuenta.<br><br>Si no se realiza la confirmación del correo durante las próximas 24 horas el registro será eliminado.");
        emailService.sendEmail(dto
                ,Url);
        return new ResponseEntity(new Mensaje("Se ha enviado un correo de confirmación al correo electrónico <b>"+emailUser+"</b>."), HttpStatus.CREATED);
    }
    
    @PostMapping("/recover-password")
    public ResponseEntity<?> RecoverPassword(@RequestParam("email") String email, HttpServletRequest request) throws MessagingException {
        if(!usuarioService.existsByEmail(email))
            return new ResponseEntity(new Mensaje("Este correo electrónico no se encuentra registrado."), HttpStatus.BAD_REQUEST);
        Usuario usuario = usuarioService.getByEmail(email).get();
        if(!usuario.isActive())
            return new ResponseEntity(new Mensaje("Este correo electrónico no ha sido confirmado"), HttpStatus.NOT_FOUND);
        usuario.setVerificationCode(UUID.randomUUID().toString());
        usuarioService.save(usuario);
        url = url+"/recoverPassword/changePassword?code="+usuario.getVerificationCode();
        emailService.sendEmail(new EmailValuesDTO(email,"CAMBIAR CONTRASEÑA","Haz click <a href='"+url+"'>aquí</a> para cambiar tu contraseña. Este link tendrá una validez de 2 horas.")
             ,url);
        return new ResponseEntity(new Mensaje("Se ha enviado un correo de cambio de contraseña al correo electrónico <b>"+email+"</b>."), HttpStatus.OK);
    }


    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos invalidos"), HttpStatus.BAD_REQUEST);
        Usuario usuario = usuarioService.findByVerificationCodeAndEmailAndActive(dto.getToken(),dto.getEmail());
        if(usuario == null)
            return new ResponseEntity(new Mensaje("No existe ningún usuario con esas credenciales."), HttpStatus.NOT_FOUND);
        String newPassword = passwordEncoder.encode(dto.getPassword());
        usuario.setPassword(newPassword);
        usuario.setVerificationCode(null);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Contraseña actualizada."), HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ModelAndView verifyUser(@Param("code") String code) {
        ModelAndView modelAndView = new ModelAndView();
        ModelMap modelMapView = modelAndView.getModelMap();
        modelMapView.addAttribute("estado", usuarioService.verify(code));
        modelAndView.setViewName("verify_account");
        return modelAndView;
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

}

package coin.backend.controller;

import coin.backend.data.dto.NamePasswordReq;
import coin.backend.data.dto.SignUpReq;
import coin.backend.data.dto.TokenRes;
import coin.backend.service.UserService;
import coin.backend.util.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class UserController {

    private final UserService userService;

    /*
    * 회원가입 컨트롤러
    * */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpReq signUpReq){
        try{
            userService.insertUser(signUpReq.getUserName(), signUpReq.getUserPassword());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            e.printStackTrace();
            // 오류 리턴
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody NamePasswordReq namePasswordReq){
        try {
            TokenRes tokenRes = userService.loginUser(namePasswordReq.getUserName(), namePasswordReq.getUserPassword());
            return ResponseEntity.status(HttpStatus.OK).body(tokenRes);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/testing")
    public ResponseEntity<?> testing(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String userName = userDetails.getUsername();

            return ResponseEntity.ok("Hello " +userName + "!");

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
 }

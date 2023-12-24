package coin.backend.controller;

import coin.backend.data.dto.SignUpReq;
import coin.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

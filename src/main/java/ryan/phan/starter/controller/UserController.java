package ryan.phan.starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ryan.phan.starter.constant.common.ResponseCode;
import ryan.phan.starter.dto.common.ResponseDto;
import ryan.phan.starter.dto.user.CreateUserDto;
import ryan.phan.starter.dto.user.UserDto;
import ryan.phan.starter.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseDto<List<UserDto>> getList() {
        List<UserDto> lst = service.list();
        return new ResponseDto<>(ResponseCode.OK, lst);
    }

    @GetMapping("/{username}")
    public ResponseDto<UserDto> detail(@PathVariable String username) {
        return new ResponseDto<>(ResponseCode.OK, service.getDetail(username));
    }

    @PostMapping
    public ResponseDto<UserDto> createNew(@Valid @RequestBody CreateUserDto payload) {
        UserDto newUser = service.createUser(payload);
        return new ResponseDto<>(ResponseCode.OK, newUser);
    }

}

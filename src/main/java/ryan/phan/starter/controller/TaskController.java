package ryan.phan.starter.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ryan.phan.starter.constant.common.ResponseCode;
import ryan.phan.starter.dto.common.ResponseDto;
import ryan.phan.starter.dto.task.CreateTaskDto;
import ryan.phan.starter.dto.task.TaskDto;
import ryan.phan.starter.entity.Task;
import ryan.phan.starter.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseDto<Task> detail(@PathVariable Long id) {
        return new ResponseDto<>(ResponseCode.OK, service.getDetail(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDto<List<TaskDto>> list() {
        List<TaskDto> list = service.getList();
        return new ResponseDto<>(ResponseCode.OK, list);
    }

    @PostMapping
    public ResponseDto<TaskDto> create(@Valid @RequestBody CreateTaskDto body) {
        TaskDto responseDto = service.createTask(body);
        return new ResponseDto<>(ResponseCode.CREATED, responseDto);
    }
}

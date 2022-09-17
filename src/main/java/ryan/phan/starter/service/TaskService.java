package ryan.phan.starter.service;

import org.springframework.stereotype.Service;
import ryan.phan.starter.constant.common.ResponseCode;
import ryan.phan.starter.dto.task.CreateTaskDto;
import ryan.phan.starter.dto.task.TaskDto;
import ryan.phan.starter.entity.Task;
import ryan.phan.starter.entity.User;
import ryan.phan.starter.exception.GlobalAppException;
import ryan.phan.starter.repository.TaskRepository;
import ryan.phan.starter.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Task getDetail(Long Id) {
        return repository.findTaskById(Id)
                .orElseThrow(() -> new GlobalAppException(ResponseCode.BAD_REQUEST));
    }

    public List<TaskDto> getList() {
        return repository.findAll().stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
    }

    public TaskDto createTask(CreateTaskDto body) {
        Task entity = body.toEntity();
        User owner = userRepository.findById(body.getOwnerId())
                .orElseThrow(() -> new GlobalAppException(ResponseCode.NOT_FOUND_USER));
        entity.setOwner(owner);
        repository.save(entity);
        return new TaskDto(entity);
    }
}

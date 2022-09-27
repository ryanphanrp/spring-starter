package ryan.phan.starter.service;

import ryan.phan.starter.dto.task.CreateTaskDto;
import ryan.phan.starter.dto.task.TaskDto;
import ryan.phan.starter.entity.Task;

import java.util.List;

public interface TaskService {
    Task getDetail(Long id);
    List<TaskDto> getList();
    TaskDto createTask(CreateTaskDto body);
}

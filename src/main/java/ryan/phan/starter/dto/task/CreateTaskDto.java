package ryan.phan.starter.dto.task;


import lombok.Data;
import ryan.phan.starter.constant.Priority;
import ryan.phan.starter.entity.Task;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateTaskDto {

    @NotEmpty(message = "Title must not be empty")
    @NotNull(message = "Title is required")
    private String title;

    @NotEmpty(message = "Description must not be empty")
    private String description;
    private Priority priority;

    private Long ownerId;

    public Task toEntity() {
        Task entity = new Task();
        entity.setTitle(this.title);
        entity.setDescription(this.description);
        entity.setPriority(this.priority);
        return entity;
    }
}

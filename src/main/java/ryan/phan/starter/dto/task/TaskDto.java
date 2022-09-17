package ryan.phan.starter.dto.task;

import lombok.Data;
import lombok.NoArgsConstructor;
import ryan.phan.starter.constant.Priority;
import ryan.phan.starter.dto.user.UserDto;
import ryan.phan.starter.entity.Task;

@Data
@NoArgsConstructor
public class TaskDto {
    private Long Id;
    private String title;
    private String description;
    private Priority priority;
    private UserDto owner;
    private long createDt;
    private long updateDt;

    public TaskDto(Task entity) {
        this.Id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.priority = entity.getPriority();
        this.owner = new UserDto(entity.getOwner());
        this.createDt = entity.getCreateDt().getTime();
        this.updateDt = entity.getUpdateDt().getTime();
    }
}

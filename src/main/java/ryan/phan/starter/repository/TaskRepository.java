package ryan.phan.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ryan.phan.starter.entity.Task;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findTaskById(Long id);
}

package dev.ens.join_backend.repository;

import dev.ens.join_backend.model.enums.Priority;
import dev.ens.join_backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByPriority(Priority priority);

}

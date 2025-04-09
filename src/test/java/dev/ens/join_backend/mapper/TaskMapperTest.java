package dev.ens.join_backend.mapper;

import dev.ens.join_backend.dtos.SubtaskDTO;
import dev.ens.join_backend.dtos.TaskRequestDTO;
import dev.ens.join_backend.model.Category;
import dev.ens.join_backend.model.Subtask;
import dev.ens.join_backend.model.Task;
import dev.ens.join_backend.model.enums.Priority;
import dev.ens.join_backend.model.enums.Status;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest {

    @Test
    void toDto() {
        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setName("Test Task");
        dto.setDescription("Test Description");
        dto.setStatus(Status.PENDING);
        dto.setPriority(Priority.URGENT);
        dto.setDueDate(LocalDate.of(2025, 4, 1));

        SubtaskDTO sub1 = new SubtaskDTO("Subtask 1", false);
        SubtaskDTO sub2 = new SubtaskDTO("Subtask 2", false);
        dto.setSubtasks(Arrays.asList(sub1, sub2));

        Task task = TaskMapper.toEntity(dto);

        assertEquals(dto.getName(), task.getName());
        assertEquals(dto.getDescription(), task.getDescription());
        assertEquals(dto.getStatus(), task.getStatus());
        assertEquals(dto.getDueDate(), task.getDueDate());
        assertEquals(dto.getPriority(), task.getPriority());
        assertNotNull(task.getSubtasks());
        assertEquals(2, task.getSubtasks().size());
        assertEquals("Subtask 1", task.getSubtasks().getFirst().getName());
        assertFalse(task.getSubtasks().getFirst().isCompleted());
        assertEquals(task, task.getSubtasks().getFirst().getTask());
    }

    @Test
    void toEntity() {
        Task task = getTask();

        TaskRequestDTO dto = TaskMapper.toDto(task);

        assertEquals(task.getName(), dto.getName());
        assertEquals(task.getDescription(), dto.getDescription());
        assertEquals(task.getStatus(), dto.getStatus());
        assertEquals(task.getDueDate(), dto.getDueDate());
        assertEquals("Work", dto.getCategoryName());
        assertEquals(Priority.URGENT, dto.getPriority());
        assertNotNull(dto.getSubtasks());
        assertEquals(1, dto.getSubtasks().size());
        assertEquals("Subtask A", dto.getSubtasks().getFirst().getName());
        assertTrue(dto.getSubtasks().getFirst().isCompleted());
    }

    private static Task getTask() {
        Task task = new Task();
        task.setName("Task name");
        task.setDescription("Some description");
        task.setStatus(Status.PENDING);
        task.setPriority(Priority.URGENT);
        task.setDueDate(LocalDate.of(2025, 5, 1));

        Category category = new Category();
        category.setName("Work");
        task.setCategory(category);

        Subtask sub1 = new Subtask();
        sub1.setName("Subtask A");
        sub1.setCompleted(true);
        sub1.setTask(task);

        task.setSubtasks(Collections.singletonList(sub1));
        return task;
    }

}
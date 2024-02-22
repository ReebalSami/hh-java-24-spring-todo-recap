package neuefisched.de.hhjava24springtodorecap.service;

import neuefisched.de.hhjava24springtodorecap.model.Task;
import neuefisched.de.hhjava24springtodorecap.model.TaskStatus;
import neuefisched.de.hhjava24springtodorecap.repo.TaskRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SuperKanbanServiceTest {
    private final TaskRepository repo = mock(TaskRepository.class);
    private final IdService idMock = mock(IdService.class);
    SuperKanbanService superKanbanService = new SuperKanbanService(repo, idMock);
    @Test
    void getAllTodos_shouldReturnEmptyList_whenCalledInitially() {
        //GIVEN
        List<Task> expected = List.of();
        when(repo.findAll()).thenReturn(new ArrayList<>());
        //WHEN
        List<Task> actual = superKanbanService.getAllTodos();
        //THEN
        assertEquals(expected, actual);
        verify(repo).findAll();
    }

    @Test
    void addNewTask_shouldReturnCleaningTaskWithIdandStatusOpen_whenCalledWithCleaning() {
        //GIVEN
        Task taskToSave = new Task();
        Task taskExpected = new Task("1", "Cleaning", TaskStatus.OPEN);
        when(idMock.randomId()).thenReturn("1");
        when(repo.save(taskExpected)).thenReturn(taskExpected);
        when(repo.findById("1")).thenReturn(java.util.Optional.of(taskExpected));

        //WHEN
        Task actual = superKanbanService.addNewTask(taskToSave);
        //THEN
        assertEquals(taskExpected, actual);
        verify(idMock).randomId();
        verify(repo).findById("1");
    }

    @Test
    void getTodoById() {
        //GIVEN
        Task expected = new Task("1", "Cleaning", TaskStatus.OPEN);
        when(repo.findById("1")).thenReturn(java.util.Optional.of(expected));
        //WHEN
        Task actual = superKanbanService.getTodoById("1");
        //THEN
        assertEquals(expected, actual);
        verify(repo).findById("1");
    }

    @Test
    void updateTask() {
        //GIVEN
        Task task = new Task("1", "Cleaning", TaskStatus.OPEN);
        Task expected = new Task("1", "Cleaning", TaskStatus.IN_PROGRESS);
        when(repo.save(expected)).thenReturn(expected);
        when(repo.findById("1")).thenReturn(java.util.Optional.of(expected));
        //WHEN
        Task actual = superKanbanService.updateTask("1", task);
        //THEN
        assertEquals(expected, actual);
        verify(repo).findById("1");
    }

    @Test
    void deleteTask() {

        //GIVEN
        Task expected = new Task("1", "Cleaning", TaskStatus.OPEN);
        when(repo.findById("1")).thenReturn(java.util.Optional.of(expected));
        //WHEN
        Task actual = superKanbanService.deleteTask("1");
        //THEN
        assertEquals(expected, actual);
        verify(repo).findById("1");
        verify(repo).deleteById("1");
    }
}
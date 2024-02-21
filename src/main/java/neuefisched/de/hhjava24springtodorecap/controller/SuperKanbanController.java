package neuefisched.de.hhjava24springtodorecap.controller;

import lombok.RequiredArgsConstructor;
import neuefisched.de.hhjava24springtodorecap.model.Task;
import neuefisched.de.hhjava24springtodorecap.model.dtos.TaskDto;
import neuefisched.de.hhjava24springtodorecap.service.SuperKanbanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SuperKanbanController {
    private final SuperKanbanService service;


    /*
    import axios from 'axios'

export const getAllTodos = () =>
  axios.get('/api/todo').then(response => response.data)

export const getTodoById = id =>
  axios.get(`/api/todo/${id}`).then(response => response.data)

export const postTodo = description =>
  axios.post('/api/todo', { description: description, status: 'OPEN' })

export const putTodo = todo => axios.put(`/api/todo/${todo.id}`, todo)

export const deleteTodo = id => axios.delete(`/api/todo/${id}`)

     */
    @GetMapping("/todo")
    public List<Task> getAllTodos(){
        return service.getAllTodos();
    }

@GetMapping("/todo/{id}")
    public Task getTodoById(@PathVariable String id) {
        return service.getTodoById(id);
    }


    @GetMapping("/todos")
    public List<TaskDto> getTodos() {
        return service.getTodos();
    }

    @PostMapping("/todo")
    public Task addNewTask(@RequestBody Task task) {
        return service.addNewTask(task);
    }

//putTodo = todo => axios.put(`/api/todo/${todo.id}`, todo)
    @PutMapping("/todo/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task task) {
        return service.updateTask(id, task);
    }

    @DeleteMapping("/todo/{id}")
    public String deleteTask(@PathVariable String id) {
        service.deleteTask(id);
        return "This Task has been deleted: " + id;
    }

}

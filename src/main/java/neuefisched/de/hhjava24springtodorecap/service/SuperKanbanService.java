package neuefisched.de.hhjava24springtodorecap.service;

import lombok.RequiredArgsConstructor;
import neuefisched.de.hhjava24springtodorecap.model.Task;
import neuefisched.de.hhjava24springtodorecap.model.TaskStatus;
import neuefisched.de.hhjava24springtodorecap.model.dtos.TaskDto;
import neuefisched.de.hhjava24springtodorecap.repo.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SuperKanbanService {

    //needed classes
    private final TaskRepository repository;
    private final IdService idService;


    public List<Task> getAllTodos(){
        return repository.findAll();
    }

    //get all tasks from the todo_list
    public List<TaskDto> getTodos() {
        List<Task> temp = repository.findAll();
        List<TaskDto> dtoList = new ArrayList<>();
        for (Task task : temp) {
            TaskDto taskdto = new TaskDto(task.getId(), task.getDescription());
            dtoList.add(taskdto);
        }
        return dtoList;

    }

    //add a new task to the todo_list
    public Task addNewTask(Task task) {
        Task temp = task.withId(idService.randomId())
                    .withStatus(TaskStatus.valueOf("OPEN"));
        repository.save(temp);
        return repository.findById(temp.getId()).orElseThrow();
    }

    public Task getTodoById(String id) {
        return repository.findById(id).orElseThrow();
    }

    public Task updateTask(String id, Task task) {
        Task temp = task.withId(id);
        repository.save(temp);
        return repository.findById(temp.getId()).orElseThrow();
    }

    public Task deleteTask(String id) {
        Task temp = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return temp;}
}

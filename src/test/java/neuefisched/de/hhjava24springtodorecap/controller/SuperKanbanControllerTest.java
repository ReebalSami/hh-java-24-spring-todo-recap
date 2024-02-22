package neuefisched.de.hhjava24springtodorecap.controller;

import neuefisched.de.hhjava24springtodorecap.model.Task;
import neuefisched.de.hhjava24springtodorecap.model.TaskStatus;
import neuefisched.de.hhjava24springtodorecap.repo.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SuperKanbanControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private TaskRepository repo;

    @Test
    void getAllTodos_shouldReturnEmptyList_WhenCalledInitially() throws Exception {
        //GIVEN
        List<Task> expected = List.of();
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expected.toString()));
    }

    @Test
    void getTodoById_shouldReturnCleaning_WhenCalledWithValidId()throws Exception {
        //GIVEN
        Task task = new Task("1","Cleaning", TaskStatus.OPEN);
        Task saved = repo.save(task);
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/todo/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                    {
                                        "id":"1",
                                        "description":"Cleaning",
                                        "status":"OPEN"
                                    }

                                    """
                ));

    }

/*    @Test
    void getTodos() {
    }*/

    @Test
    void addNewTask_souldReturnNewTask_whenCalledWithValidJSON() throws Exception {
        //GIVEN
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "description":"Cleaning",
                            "status":"OPEN"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                    "description":"Cleaning",
                                    "status":"OPEN"
                                }
                                """
                ))
                .andExpect(jsonPath("$.id").exists());

    }

    @Test
    void updateTask_shouldReturnFeierabend_whenCalledWithValidJSON() throws Exception {
        //GIVEN
        Task task = new Task("1","Cleaning", TaskStatus.OPEN);
        Task saved = repo.save(task);
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.put("/api/todo/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "description":"Feierabend",
                            "status":"OPEN"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                    "id":"1",
                                    "description":"Feierabend",
                                    "status":"OPEN"
                                }
                                """
                ));
    }

    @Test
    void deleteTask_shouldReturnString_whenCalledWithValidId() throws Exception {
        //GIVEN
        Task task = new Task("1","Cleaning", TaskStatus.OPEN);
        Task saved = repo.save(task);
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.delete("/api/todo/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("This Task has been deleted: 1"));
    }
}
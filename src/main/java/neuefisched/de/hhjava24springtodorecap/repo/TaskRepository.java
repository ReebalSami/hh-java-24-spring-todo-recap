package neuefisched.de.hhjava24springtodorecap.repo;

import neuefisched.de.hhjava24springtodorecap.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface TaskRepository extends MongoRepository<Task, String>{

}

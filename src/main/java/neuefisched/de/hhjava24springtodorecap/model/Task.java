package neuefisched.de.hhjava24springtodorecap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Document("tasks")

public class Task {
    private String id;
    private String description;
    private TaskStatus status;


}

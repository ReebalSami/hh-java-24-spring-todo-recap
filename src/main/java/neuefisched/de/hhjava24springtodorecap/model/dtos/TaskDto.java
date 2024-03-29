package neuefisched.de.hhjava24springtodorecap.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import neuefisched.de.hhjava24springtodorecap.model.TaskStatus;


@Data
@With
@NoArgsConstructor
@AllArgsConstructor

public class TaskDto {
    private String description;
    private TaskStatus status;
}

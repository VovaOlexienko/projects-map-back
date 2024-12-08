package oleksiienko.volodymyr.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    private ObjectId id;

    @Field("name")
    private String name;

    @Field("projects")
    private List<Project> projects;
}

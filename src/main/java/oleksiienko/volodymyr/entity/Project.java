package oleksiienko.volodymyr.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    private ObjectId id;

    @Field("name")
    private String name;

    @Field("webAddress")
    private String webAddress;
}

package oleksiienko.volodymyr.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ShortenedGroupDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
}

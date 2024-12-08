package oleksiienko.volodymyr.dto.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailsDto {

    @JsonProperty("javaVersion")
    private String javaVersion;

    @JsonProperty("springBootVersion")
    private String springBootVersion;
}

package oleksiienko.volodymyr.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import oleksiienko.volodymyr.dto.project.ProjectDto;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto extends ShortenedGroupDto {

    @JsonProperty("projects")
    private List<ProjectDto> projects;
}

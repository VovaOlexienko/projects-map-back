package oleksiienko.volodymyr.controller;

import lombok.SneakyThrows;
import oleksiienko.volodymyr.dto.project.CrudProjectDto;
import oleksiienko.volodymyr.dto.project.ProjectDetailsDto;
import oleksiienko.volodymyr.dto.project.ProjectDto;
import oleksiienko.volodymyr.service.ProjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @GetMapping("/group/{groupId}/project/{projectId}/details")
    public ProjectDetailsDto getProjectDetail(@PathVariable("groupId") @NotBlank String groupId, @PathVariable("projectId") @NotBlank String projectId) {
        return service.getProjectDetail(groupId, projectId);
    }

    @PostMapping("/group/{groupId}/project")
    public ProjectDto createProject(@PathVariable("groupId") @NotBlank String groupId, @RequestBody @Valid CrudProjectDto dto) {
        return service.createProject(groupId, dto);
    }

    @PutMapping("/group/{groupId}/project/{projectId}")
    public void updateProject(@PathVariable("groupId") @NotBlank String groupId, @PathVariable("projectId") @NotBlank String projectId,
                              @RequestBody @Valid CrudProjectDto dto) {
        service.updateProject(groupId, projectId, dto);
    }

    @DeleteMapping("/group/{groupId}/project/{projectId}")
    public void deleteProject(@PathVariable("groupId") @NotBlank String groupId, @PathVariable("projectId") @NotBlank String projectId) {
        service.deleteProject(groupId, projectId);
    }
}

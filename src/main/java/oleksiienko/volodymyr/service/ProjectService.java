package oleksiienko.volodymyr.service;

import oleksiienko.volodymyr.dto.project.CrudProjectDto;
import oleksiienko.volodymyr.dto.project.ProjectDetailsDto;
import oleksiienko.volodymyr.dto.project.ProjectDto;

import java.util.List;

public interface ProjectService {

    ProjectDetailsDto getProjectDetail(String groupId, String projectId);

    ProjectDto createProject(String groupId, CrudProjectDto dto);

    void updateProject(String groupId, String projectId, CrudProjectDto dto);

    void deleteProject(String groupId, String projectId);
}

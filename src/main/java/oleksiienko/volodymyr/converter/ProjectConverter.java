package oleksiienko.volodymyr.converter;

import oleksiienko.volodymyr.dto.project.CrudProjectDto;
import oleksiienko.volodymyr.dto.project.ProjectDetailsDto;
import oleksiienko.volodymyr.dto.project.ProjectDto;
import oleksiienko.volodymyr.entity.Project;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectConverter {

    public List<ProjectDto> convertToProjectDtos(List<Project> projects) {
        return projects.stream()
                .map(this::convertToProjectDto)
                .toList();
    }

    public ProjectDto convertToProjectDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId().toString())
                .name(project.getName())
                .webAddress(project.getWebAddress())
                .build();
    }

    public ProjectDetailsDto convertToProjectDetailsDto(String javaVersion, String springBootVersion) {
        return ProjectDetailsDto.builder()
                .javaVersion(javaVersion)
                .springBootVersion(springBootVersion)
                .build();
    }

    public Project convertToProject(CrudProjectDto dto) {
        return Project.builder()
                .id(new ObjectId())
                .name(dto.getName())
                .webAddress(dto.getWebAddress())
                .build();
    }
}

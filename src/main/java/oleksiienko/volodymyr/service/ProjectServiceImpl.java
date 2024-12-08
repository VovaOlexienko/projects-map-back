package oleksiienko.volodymyr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import oleksiienko.volodymyr.converter.ProjectConverter;
import oleksiienko.volodymyr.dto.project.CrudProjectDto;
import oleksiienko.volodymyr.dto.project.ProjectDetailsDto;
import oleksiienko.volodymyr.dto.project.ProjectDto;
import oleksiienko.volodymyr.entity.Group;
import oleksiienko.volodymyr.entity.Project;
import oleksiienko.volodymyr.exception.EntityNotFoundException;
import oleksiienko.volodymyr.exception.HttpRequestException;
import oleksiienko.volodymyr.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final GroupRepository repository;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final ProjectConverter projectConverter;
    private final GroupService service;

    @Override
    public ProjectDto createProject(String groupId, CrudProjectDto dto) {
        Group group = service.getGroup(new ObjectId(groupId));
        Project project = projectConverter.convertToProject(dto);
        group.getProjects().add(project);
        repository.save(group);
        return projectConverter.convertToProjectDto(project);
    }

    @Override
    public void updateProject(String groupId, String projectId, CrudProjectDto dto) {
        Group group = service.getGroup(new ObjectId(groupId));
        Project project = getProject(projectId, group);
        project.setName(dto.getName());
        project.setWebAddress(dto.getWebAddress());
        repository.save(group);
    }

    @Override
    public void deleteProject(String groupId, String projectId) {
        Group group = service.getGroup(new ObjectId(groupId));
        group.getProjects().removeIf(project -> project.getId().toString().equals(projectId));
        repository.save(group);
    }

    @Override
    public ProjectDetailsDto getProjectDetail(String groupId, String projectId) {
        try {
            String webAddress = getProject(projectId, service.getGroup(new ObjectId(groupId))).getWebAddress();
            String jsonResponse = restTemplate.getForObject(webAddress, String.class);
            JsonNode root = objectMapper.readTree(jsonResponse);
            String javaVersion = root.path("java").path("version").asText();
            String springBootVersion = root.path("spring-boot").path("version").asText();
            return projectConverter.convertToProjectDetailsDto(javaVersion, springBootVersion);
        } catch (RestClientException | JsonProcessingException ex) {
            throw new HttpRequestException("Error during http request", ex);
        }
    }

    private Project getProject(String projectId, Group group) {
        return group.getProjects().stream()
                .filter(project -> project.getId().toString().equals(projectId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("Project with id = [%s] not found in group with id = [%s]", projectId, group.getId())));
    }
}

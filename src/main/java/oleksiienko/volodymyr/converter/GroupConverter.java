package oleksiienko.volodymyr.converter;

import lombok.RequiredArgsConstructor;
import oleksiienko.volodymyr.dto.group.CrudGroupDto;
import oleksiienko.volodymyr.dto.group.GroupDto;
import oleksiienko.volodymyr.dto.group.ShortenedGroupDto;
import oleksiienko.volodymyr.entity.Group;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class GroupConverter {

    private final ProjectConverter projectConverter;

    public ShortenedGroupDto convertToShortenedGroupDto(Group group) {
        return ShortenedGroupDto.builder()
                .id(group.getId().toString())
                .name(group.getName())
                .build();
    }

    public GroupDto convertToGroupDto(Group group) {
        return GroupDto.builder()
                .id(group.getId().toString())
                .name(group.getName())
                .projects(projectConverter.convertToProjectDtos(group.getProjects()))
                .build();
    }

    public Group convertToGroup(CrudGroupDto dto) {
        return Group.builder()
                .name(dto.getName())
                .projects(new ArrayList<>())
                .build();
    }
}

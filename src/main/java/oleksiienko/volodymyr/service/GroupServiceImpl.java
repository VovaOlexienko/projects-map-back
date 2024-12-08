package oleksiienko.volodymyr.service;

import oleksiienko.volodymyr.converter.GroupConverter;
import oleksiienko.volodymyr.dto.group.CrudGroupDto;
import oleksiienko.volodymyr.dto.group.GroupDto;
import oleksiienko.volodymyr.dto.group.ShortenedGroupDto;
import oleksiienko.volodymyr.entity.Group;
import oleksiienko.volodymyr.exception.EntityNotFoundException;
import oleksiienko.volodymyr.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository repository;
    private final GroupConverter converter;

    @Override
    public List<ShortenedGroupDto> getGroups() {
        return repository.findAll().stream()
                .map(converter::convertToShortenedGroupDto)
                .toList();
    }

    @Override
    public GroupDto getGroup(String id) {
        return converter.convertToGroupDto(getGroup(new ObjectId(id)));
    }

    @Override
    public Group getGroup(ObjectId id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Group with id = [%s] not found", id)));
    }

    @Override
    public ShortenedGroupDto createGroup(CrudGroupDto dto) {
        return converter.convertToShortenedGroupDto(repository.save(converter.convertToGroup(dto)));
    }

    @Override
    public void updateGroup(String id, CrudGroupDto dto) {
        Group group = getGroup(new ObjectId(id));
        group.setName(dto.getName());
        repository.save(group);
    }

    @Override
    public void deleteGroup(String id) {
        repository.deleteById(new ObjectId(id));
    }
}

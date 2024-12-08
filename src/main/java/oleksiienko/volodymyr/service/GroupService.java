package oleksiienko.volodymyr.service;

import oleksiienko.volodymyr.dto.group.CrudGroupDto;
import oleksiienko.volodymyr.dto.group.GroupDto;
import oleksiienko.volodymyr.dto.group.ShortenedGroupDto;
import oleksiienko.volodymyr.entity.Group;
import org.bson.types.ObjectId;

import java.util.List;

public interface GroupService {

    List<ShortenedGroupDto> getGroups();

    GroupDto getGroup(String id);

    Group getGroup(ObjectId id);

    ShortenedGroupDto createGroup(CrudGroupDto dto);

    void updateGroup(String id, CrudGroupDto dto);

    void deleteGroup(String id);
}

package oleksiienko.volodymyr.controller;

import lombok.SneakyThrows;
import oleksiienko.volodymyr.dto.group.CrudGroupDto;
import oleksiienko.volodymyr.dto.group.GroupDto;
import oleksiienko.volodymyr.dto.group.ShortenedGroupDto;
import oleksiienko.volodymyr.service.GroupService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService service;

    @GetMapping("/group")
    public List<ShortenedGroupDto> getGroups() {
        return service.getGroups();
    }

    @GetMapping("/group/{id}")
    public GroupDto getGroup(@PathVariable("id") @NotBlank String id) {
        return service.getGroup(id);
    }

    @PostMapping("/group")
    public ShortenedGroupDto createGroup(@RequestBody @Valid CrudGroupDto dto) {
        return service.createGroup(dto);
    }

    @PutMapping("/group/{id}")
    public void updateGroup(@PathVariable("id") @NotBlank String id, @RequestBody @Valid CrudGroupDto dto) {
        service.updateGroup(id, dto);
    }

    @DeleteMapping("/group/{id}")
    public void deleteGroup(@PathVariable("id") @NotBlank String id) {
        service.deleteGroup(id);
    }
}

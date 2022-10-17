package com.wovenreviews.java.controller;

import com.wovenreviews.java.dto.ProjectRequest;
import com.wovenreviews.java.dto.ProjectResponse;
import com.wovenreviews.java.model.Project;
import com.wovenreviews.java.repo.ProjectRepository;
import com.wovenreviews.java.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ProjectResponse> create(@RequestBody ProjectRequest createProject,
                                                  @AuthenticationPrincipal String userEmail) {
        return userRepository.findByEmail(userEmail)
                .map(user -> {
                    Project project = projectRepository.save(new Project(user,
                            createProject.getTitle(), createProject.getDescription()));
                    return ResponseEntity.ok(new ProjectResponse(project.getId(), project.getTitle(),
                            project.getDescription(), project.getUser().getEmail()));
                })
                .orElse(ResponseEntity.of(Optional.empty()));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAll() {
        return ResponseEntity.ok(projectRepository.getProjects()
                .stream().map(project -> new ProjectResponse(project.getId(), project.getTitle(),
                        project.getDescription(), project.getUser().getEmail()))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> getById(@PathVariable Integer projectId) {
        return projectRepository.getProjectsById(projectId)
                .map(project -> ResponseEntity.ok(new ProjectResponse(project.getId(), project.getTitle(),
                        project.getDescription(), project.getUser().getEmail())))
                .orElse(ResponseEntity.of(Optional.empty()));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<?> update(@PathVariable Integer projectId, @RequestBody ProjectRequest updateRequest) {
        return projectRepository
                .updateProject(updateRequest.getTitle(), updateRequest.getDescription(), projectId)
                == 0 ? ResponseEntity.of(Optional.empty()) : ResponseEntity.ok().build();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> delete(@PathVariable Integer projectId) {
        projectRepository.deleteById(projectId);
        return ResponseEntity.ok().build();
    }
}

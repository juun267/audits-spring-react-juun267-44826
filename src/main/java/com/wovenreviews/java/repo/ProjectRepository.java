package com.wovenreviews.java.repo;

import com.wovenreviews.java.model.Project;
import com.wovenreviews.java.projection.ProjectView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {


    @Query("""
              select project from Project project
              join fetch project.user
            """)
    List<ProjectView> getProjects();

    Optional<ProjectView> getProjectsById(Integer id);

    @Modifying
    @Transactional
    @Query("""
                update Project p set p.title = :title, p.description = :description where p.id = :id
            """)
    int updateProject(@Param("title") String title, @Param("description") String description,
                      @Param("id") Integer id);
}

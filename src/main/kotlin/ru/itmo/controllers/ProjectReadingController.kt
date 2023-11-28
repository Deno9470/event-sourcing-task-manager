package ru.itmo.controllers

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itmo.entities.ProjectEntity
import ru.itmo.entities.StatusEntity
import ru.itmo.services.reading.ProjectReadingService
import ru.itmo.services.reading.StatusReadingService
import java.util.*

@RestController
@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE],
    value = ["/\${api.prefix}/\${api.currentVersion}" +
            "/projects"]
)
class ProjectReadingController(
    private val projectReadingService: ProjectReadingService,
    private val statusReadingService: StatusReadingService
) {

    @GetMapping("")
    fun getAll() : ResponseEntity<Iterable<ProjectEntity>> {
        val projects = this.projectReadingService.getAll()
        return ResponseEntity.ok(projects)
    }

    @GetMapping("/{projectId}")
    fun getById(@PathVariable projectId: UUID) : ResponseEntity<ProjectEntity> {
        val foundProject = this.projectReadingService.getById(projectId)
        return ResponseEntity.ok(foundProject)
    }

    @GetMapping("/{projectId}/statuses")
    fun getAllProjectStatusesById(@PathVariable projectId: UUID) : ResponseEntity<Iterable<StatusEntity>> {
        val projectStatuses = this.statusReadingService.getAllByProjectId(projectId)
        return ResponseEntity.ok(projectStatuses)
    }

    @GetMapping("/{projectId}/statuses/{statusId}")
    fun getAllProjectStatusesById(@PathVariable projectId: UUID, @PathVariable statusId: UUID) : ResponseEntity<StatusEntity> {
        val projectStatus = this.statusReadingService.getByProjectIdAndId(projectId, statusId)
        return ResponseEntity.ok(projectStatus)
    }
}

























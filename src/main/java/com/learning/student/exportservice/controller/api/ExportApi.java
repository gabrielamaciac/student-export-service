package com.learning.student.exportservice.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/export")
@Tag(name = "Student Export Service", description = "Export students from DB in XML format.")
public interface ExportApi {
    @Operation(summary = "Export a student from DB.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student exported.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found with the given id.", content = @Content)
    })
    @PostMapping("/student/{studentId}")
    ResponseEntity<String> export(@Parameter(description = "The id of the student from DB.") @PathVariable String studentId);
}

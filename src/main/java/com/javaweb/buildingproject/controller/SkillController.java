package com.javaweb.buildingproject.controller;

import com.javaweb.buildingproject.annotation.ApiMessage;
import com.javaweb.buildingproject.domain.dto.PaginationDTO;
import com.javaweb.buildingproject.domain.dto.SkillDTO;
import com.javaweb.buildingproject.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class SkillController {
    private SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @ApiMessage("fetch all skills")
    @GetMapping("/skills")
    public ResponseEntity<PaginationDTO> fetchAllSkills(@RequestParam(defaultValue = "1") Integer pageNumber
                                                        , @RequestParam(defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber-1,pageSize);
        return ResponseEntity.ok().body(skillService.fetchAllSkills(pageable));
    }

    @ApiMessage("created skill")
    @PostMapping("/skills")
    public ResponseEntity<?> createSkill(@Valid @RequestBody SkillDTO skillDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skillService.createSkill(skillDTO));
    }

    @ApiMessage("updated skill")
    @PutMapping("/skills/{id}")
    public ResponseEntity<?> updateSkill(@PathVariable Long id, @RequestBody SkillDTO skillDTO) {
        return ResponseEntity.ok().body(skillService.updateSkill(id,skillDTO));
    }

    @ApiMessage("deleted skills")
    @DeleteMapping("/skills/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}

package com.skill.tracker.microservices.skill_service.Controller;

import com.skill.tracker.microservices.skill_service.Dto.SkillCreateRequestDto;
import com.skill.tracker.microservices.skill_service.Dto.SkillResponseDto;
import com.skill.tracker.microservices.skill_service.Service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkillController {


    private final SkillService service;

    public SkillController(SkillService service) {
        this.service = service;
    }

    @PostMapping("/create-new")
    public ResponseEntity<SkillResponseDto> createSkill(
            @RequestBody SkillCreateRequestDto skillCreateRequestDto
    )
    {
        SkillResponseDto created = service.createSkill(skillCreateRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @GetMapping("/all-skills")
    public ResponseEntity<List<SkillResponseDto>> getAllSkills()
    {
        return ResponseEntity
                .ok()
                .body(service.getAllSkills());
    }


}

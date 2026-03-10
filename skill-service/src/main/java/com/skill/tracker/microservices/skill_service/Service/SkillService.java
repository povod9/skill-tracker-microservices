package com.skill.tracker.microservices.skill_service.Service;

import com.skill.tracker.microservices.skill_service.Dto.SkillCreateRequestDto;
import com.skill.tracker.microservices.skill_service.Dto.SkillResponseDto;
import com.skill.tracker.microservices.skill_service.Entity.SkillEntity;
import com.skill.tracker.microservices.skill_service.Mapper.SkillMapper;
import com.skill.tracker.microservices.skill_service.Repository.SkillRepository;
import com.skill.tracker.microservices.skill_service.Repository.UserSkillRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;
    private final SkillMapper mapper;

    public SkillService(SkillRepository skillRepository, UserSkillRepository userSkillRepository, SkillMapper mapper) {
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public SkillResponseDto createSkill(
            SkillCreateRequestDto skillCreateRequestDto
    )
    {
        if(skillRepository.existsByName(skillCreateRequestDto.name())){
            throw new IllegalArgumentException("Skill already exists " + skillCreateRequestDto.name());
        }

        SkillEntity skillEntity = new SkillEntity(
                null,
                skillCreateRequestDto.name(),
                skillCreateRequestDto.description(),
                null
        );

        SkillEntity saved = skillRepository.save(skillEntity);

        return mapper.toDto(saved);
    }

    public List<SkillResponseDto> getAllSkills() {

        List<SkillEntity> allSkills = skillRepository.findAll();

        return allSkills.stream()
                .map(mapper::toDto)
                .toList();
    }
}

package com.skill.tracker.microservices.skill_service.Entity;

import com.skill.tracker.microservices.skill_service.Entity.Enums.SkillLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSkillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false, unique = true)
    private SkillEntity skill;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SkillLevel skillLevel;


}

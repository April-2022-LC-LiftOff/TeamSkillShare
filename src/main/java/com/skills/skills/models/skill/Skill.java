package com.skills.skills.models.skill;

import com.skills.skills.models.AbstractEntity;
import com.skills.skills.models.Tag;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

    @NotBlank(message = "What is your skill called?")
    @Size(max = 30, message = "Name must be 30 characters or less")
    public String name;

    @ManyToOne
    @NotNull(message = "Skill category is required")
    private SkillsCategory skillsCategory;

    @ManyToOne
    public Tag tagName;

    public Skill(String name, SkillsCategory skillsCategory, Tag tagName) {
        this.name = name;
        this.skillsCategory = skillsCategory;
        this.tagName = tagName;
    }

    public Skill() {}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getTagId(Tag tag) { return tag.getId(); }

    public SkillsCategory getSkillsCategory() { return skillsCategory; }

    public String getCatNameString(Skill skill) { return skill.skillsCategory.getCatName(); }

    public void setCatName(SkillsCategory skillsCategory) { this.skillsCategory = skillsCategory; }

    public Tag getTagName() { return tagName; }

    public void setTagName (Tag tagName) { this.tagName = tagName; }

}



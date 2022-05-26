package com.skills.skills.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity{

    @OneToMany(mappedBy = "tagName")
    private final List<Skill> skills = new ArrayList<>();


    private String tagName;

    public Tag(String tagName) { this.tagName = tagName; }
    public Tag() {}

    public String getTagName() {
        return tagName;
    }

}

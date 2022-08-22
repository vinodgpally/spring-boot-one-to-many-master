package com.example.spring.hibernate.onetomany.dto;

import java.util.Date;

public class TaskDTO {

    private String name;

    private String description;

    private Date createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public TaskDTO(String name, String description, Date createDate) {
        this.name = name;
        this.description = description;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}

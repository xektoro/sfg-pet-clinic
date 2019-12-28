package com.example.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity // is telling that this is going to be an entity persisted in the database
@Table(name = "specialties")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Specialty extends BaseEntity {

    @Column(name = "description")
    private String description;
}

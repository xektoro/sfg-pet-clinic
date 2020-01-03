package com.example.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "types")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetType extends BaseEntity {

    // we annotate the constructor with Builder annotation, because we have super class here, that also should be
    // initialized
    @Builder
    public PetType(Long id, String name) {
       super(id);
       this.name = name;
    }

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return this.name;
    }
}

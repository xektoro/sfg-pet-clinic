package com.example.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owners") // how to name the db table
@Setter
@Getter
@NoArgsConstructor
public class Owner extends Person {

    // here we couldn't set with Builder the fields id, firstName, lastName (which were coming from the Super Class)
    // so we had to write it on hand
    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city,
                 String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        // if we have not added this check, every time we use the Builder pattern
        // for that entity it will initialize it with null value (override the field initialization)
        if(pets != null) {
            this.pets = pets;
        }
    }

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    // now if we delete an owner, also his pets are going to be deleted
    // mappedBy = "owner" specifies the field property from the other side of the relationship
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>(); // to avoid NPE

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name) {
        return getPet(name, false);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for(Pet pet: pets) {
            if(!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if(compName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }
}

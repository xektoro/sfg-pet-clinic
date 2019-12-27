package com.example.sfgpetclinic.repositories;

import com.example.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

// because extending CrudRepository automatically we will have them in Spring Context
// Spring Data JPA is going to provide us instances of these at runtime automatically
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);
}

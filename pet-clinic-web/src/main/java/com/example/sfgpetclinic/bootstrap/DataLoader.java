package com.example.sfgpetclinic.bootstrap;

import com.example.sfgpetclinic.model.Owner;
import com.example.sfgpetclinic.model.Pet;
import com.example.sfgpetclinic.model.PetType;
import com.example.sfgpetclinic.model.Vet;
import com.example.sfgpetclinic.services.OwnerService;
import com.example.sfgpetclinic.services.PetTypeService;
import com.example.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// By making this Component, this becomes a Spring Bean and it gets
// registered into Spring context
@Component
// CommandLineRunner is a Spring Boot specific way to initialize data
// When the Spring Context is completely up and ready, it is going to rcall this run method and run
// everything inside of it
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    // Before Spring 4.2 I had to annotate the Constructor injection with '@Autowired'
    // but now it is not needed
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType sevedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType sevedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Mich");
        owner1.setLastName("Dukanen");
        owner1.setAddress("14 Struma str");
        owner1.setCity("Pernik");
        owner1.setTelephone("0444444");

        Pet michPet = new Pet();
        michPet.setName("Kalinka");
        michPet.setPetType(sevedDogPetType);
        michPet.setBirthDate(LocalDate.now());
        michPet.setOwner(owner1);
        owner1.getPets().add(michPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Pablo");
        owner2.setLastName("Escobar");
        owner1.setAddress("4 Kalimantsi");
        owner1.setCity("Pernik");
        owner1.setTelephone("0123456789");

        Pet pablosCat = new Pet();
        pablosCat.setName("Just cat");
        pablosCat.setOwner(owner2);
        pablosCat.setBirthDate(LocalDate.now());
        pablosCat.setPetType(sevedCatPetType);
        owner2.getPets().add(pablosCat);

        ownerService.save(owner2);

        System.out.println("Loading Owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Ivan");
        vet1.setLastName("Ivanov");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Asen");
        vet2.setLastName("Asenov");

        vetService.save(vet2);

        System.out.println("Loading Vets");

    }
}

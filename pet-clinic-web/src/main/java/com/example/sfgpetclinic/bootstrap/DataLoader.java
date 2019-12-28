package com.example.sfgpetclinic.bootstrap;

import com.example.sfgpetclinic.model.*;
import com.example.sfgpetclinic.services.*;
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
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    // Before Spring 4.2 I had to annotate the Constructor injection with '@Autowired'
    // but now it is not needed
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) {
        // not to load data on evety s
        int count = petTypeService.findAll().size();
        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType sevedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType sevedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology Description");
        Specialty savedRadiology = specialtyService.save(radiology);
        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery Description");
        Specialty savedSurgery = specialtyService.save(surgery);
        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry Description");
        Specialty savedDentistry = specialtyService.save(dentistry);


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

        // Builder Pattern
        Owner.builder().firstName("First Name Lombok").lastName("Last Name Lombok").build();

        Pet pablosCat = new Pet();
        pablosCat.setName("Just cat");
        pablosCat.setOwner(owner2);
        pablosCat.setBirthDate(LocalDate.now());
        pablosCat.setPetType(sevedCatPetType);
        owner2.getPets().add(pablosCat);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(pablosCat);
        catVisit.setDescription("Sneezy Kitty");
        catVisit.setDate(LocalDate.now());

        visitService.save(catVisit);

        System.out.println("Loading Owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Ivan");
        vet1.setLastName("Ivanov");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Asen");
        vet2.setLastName("Asenov");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loading Vets");
    }
}

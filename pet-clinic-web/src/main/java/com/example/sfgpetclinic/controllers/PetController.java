package com.example.sfgpetclinic.controllers;

import com.example.sfgpetclinic.model.Owner;
import com.example.sfgpetclinic.model.PetType;
import com.example.sfgpetclinic.services.OwnerService;
import com.example.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(OwnerService ownerService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    // @ModelAttribute (declared like that) - whenever we hit any controller method in that controller
    // this is going automatically added to the model of the view
    // so rather than having to repeat all these operation in each controller, now the Spring MVC is going to provide it automatically for us
    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    // @ModelAttribute (declared like that) - whenever we hit any controller method in that controller
    // this is going automatically added to the model of the view
    // so rather than having to repeat all these operation in each controller, now the Spring MVC is going to provide it automatically for us

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }
}

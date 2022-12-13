package com.gdmatstaffs.pets.pet;

import com.gdmatstaffs.pets.address.AddressRepository;
import com.gdmatstaffs.pets.entity.Address;
import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.entity.Pet;
import com.gdmatstaffs.pets.entity.Staff;
import com.gdmatstaffs.pets.owner.OwnerRepository;
import com.gdmatstaffs.pets.staff.StaffRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PetControllerIntegrationTests
{
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Test
    public void when_OwnerExistsAndCreatePet_expect_PetCreated() throws Exception
    {
        Address address = new Address(0, "5", "High Street", "Toon Town", "TT1 0AA");
        Owner owner = new Owner(
                0,
                address,
                "owner1@pets.com",
                "Owner 1",
                "owner_1",
                "+44 1111 222222",
                "123456",
                null);

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        addressRepository.save(address);
        owner = ownerRepository.save(owner);

        String json_petToCreate = "{\"name\": \"Snuffler\", " +
                                  "\"dateOfBirth\": \"2022-10-03\", " +
                                  "\"ownerId\": " + owner.getId() + ", " +
                                  "\"typeOfAnimal\": \"Dog\"}";
        mockMvc
                .perform(post("/pet/add/" + owner.getId())
                        .header("AUTHORIZATION", "123456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_petToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Snuffler"))
                .andExpect(jsonPath("$.dateOfBirth").value("2022-10-03"))
                .andExpect(jsonPath("$.typeOfAnimal").value("Dog"))
                .andExpect(jsonPath("$.ownerId").value(owner.getId()));
    }

    @Test
    public void when_OwnerDoesNotExistAndCreatePet_expect_PetNotCreated() throws Exception
    {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        String json_petToCreate = "{\"name\": \"Snuffler\", " +
                                  "\"dateOfBirth\": \"2022-10-03\", " +
                                  "\"owner\": " +
                                      "{\"id\": 1, " +
                                      "\"address\": \"\", " +
                                      "\"email\": \"\", " +
                                      "\"name\": \"\", " +
                                      "\"telephone\": \"\", " +
                                  "\"pets\": null}, " +
                                  "\"typeOfAnimal\": \"Dog\"}";
        mockMvc
                .perform(post("/pet/add/100")
                        .header("AUTHORIZATION", "123456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_petToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void when_PetExistsAndGetPet_expect_PetReturned() throws Exception
    {
        Address address = new Address(0, "5", "High Street", "Toon Town", "TT1 0AA");
        Owner owner = new Owner(
                0,
                address,
                "owner1@pets.com",
                "Owner 1",
                "owner_1",
                "+44 1111 222222",
                null,
                null);

        Staff staff = new Staff(
                0,
                "staff1@staff.com",
                "Staff 1",
                "staff_1",
                "54321");

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();
        staffRepository.deleteAll();;

        addressRepository.save(address);
        owner = ownerRepository.save(owner);

        Pet pet = new Pet(0, "Snuffler", LocalDate.parse("2022-10-03"), owner, "Dog");
        pet = petRepository.save(pet);

        staff = staffRepository.save(staff);

        mockMvc
                .perform(get("/pet/get/" + pet.getId()).header("AUTHORIZATION", "54321"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Snuffler"))
                .andExpect(jsonPath("$.dateOfBirth").value("2022-10-03"))
                .andExpect(jsonPath("$.typeOfAnimal").value("Dog"))
                .andExpect(jsonPath("$.ownerId").value(owner.getId()));
    }

    @Test
    public void when_PetDoesNotExistAndGetPet_expect_NoPetReturned() throws Exception
    {
        Staff staff = new Staff(
                0,
                "staff1@staff.com",
                "Staff 1",
                "staff_1",
                "54321");

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();
        staffRepository.deleteAll();

        staff = staffRepository.save(staff);

        mockMvc
                .perform(get("/pet/get/100").header("AUTHORIZATION", "54321"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void when_InvalidNewPet_expect_BadRequestAndCorrectErrorMessages() throws Exception
    {
        Address address = new Address(0, "5", "High Street", "Toon Town", "TT1 0AA");
        Owner owner = new Owner(
                0,
                address,
                "owner1@pets.com",
                "Owner 1",
                "owner_1",
                "+44 1111 222222",
                "123456",
                null);

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        addressRepository.save(address);
        owner = ownerRepository.save(owner);

        String json_petToCreate = "{\"name\": \"\", " +
                                  "\"dateOfBirth\": \"" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\", " +
                                  "\"typeOfAnimal\": \"\"}";
        mockMvc
                .perform(post("/pet/add/" + owner.getId())
                        .header("AUTHORIZATION", "123456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_petToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.dateOfBirth").value("Date of birth must be before today"))
                .andExpect(jsonPath("$.name").value("Name cannot be blank"))
                .andExpect(jsonPath("$.ownerId").value("Owner id must be greater than zero"))
                .andExpect(jsonPath("$.typeOfAnimal").value("Type of animal cannot be blank"));
    }

    @Test
    public void when_GetPetWithInvalidId_expect_BadRequestAndCorrectErrorMessage() throws Exception
    {
        Staff staff = new Staff(
                0,
                "staff1@staff.com",
                "Staff 1",
                "staff_1",
                "54321");

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();
        staffRepository.deleteAll();

        staff = staffRepository.save(staff);

        mockMvc
                .perform(get("/pet/get/0").header("AUTHORIZATION", "54321"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Validation error: Id must be greater than zero"));
    }
}
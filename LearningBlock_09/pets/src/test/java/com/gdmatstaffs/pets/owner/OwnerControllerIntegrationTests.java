package com.gdmatstaffs.pets.owner;

import com.gdmatstaffs.pets.address.AddressRepository;
import com.gdmatstaffs.pets.entity.Address;
import com.gdmatstaffs.pets.entity.Owner;
import com.gdmatstaffs.pets.entity.Pet;
import com.gdmatstaffs.pets.pet.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class OwnerControllerIntegrationTests
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

    @Test
    public void when_OwnerExistsAndCreateOwner_expect_NoOwnerReturned() throws Exception
    {
        Address address = new Address(0, "43", "High Road", "Meadow-under-Hill", "F12 6GX");
        Owner owner = new Owner(
                0,
                address,
                "po4@tt.com",
                "Pet owner 4",
                "pet_owner_4",
                "+44 2222 444444",
                null);

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        addressRepository.save(address);
        owner = ownerRepository.save(owner);

        String json_ownerToCreate =
                "{\"address\": \"43, High Road, Meadow-under-Hill, F12 6GX\"," +
                "\"email\": \"po4@tt.com\"," +
                "\"name\": \"Pet owner 4\"," +
                "\"telephone\": \"+44 2222 444444\"," +
                "\"password\": \"pet_owner_4\"}";

        mockMvc
                .perform(post("/owner/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void when_AddressExistsOwnerDoesNotExistAndCreateOwner_expect_OwnerReturned() throws Exception
    {
        Address address = new Address(0, "43", "High Road", "Meadow-under-Hill", "F12 6GX");

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        addressRepository.save(address);

        String json_ownerToCreate =
                "{\"address\": \"43, High Road, Meadow-under-Hill, F12 6GX\"," +
                "\"email\": \"po4@tt.com\"," +
                "\"name\": \"Pet owner 4\"," +
                "\"telephone\": \"+44 2222 444444\"," +
                "\"password\": \"pet_owner_4\"}";

        mockMvc
                .perform(post("/owner/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Pet owner 4"))
                .andExpect(jsonPath("$.address").value("43, High Road, Meadow-under-Hill, F12 6GX"))
                .andExpect(jsonPath("$.email").value("po4@tt.com"))
                .andExpect(jsonPath("$.telephone").value("+44 2222 444444"))
                .andExpect(jsonPath("$.pets").isEmpty());
    }

    @Test
    public void when_AddressDoesNotExistAndOwnerDoesNotExistAndCreateOwner_expect_OwnerReturned() throws Exception
    {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        String json_ownerToCreate =
                "{\"address\": \"43, High Road, Meadow-under-Hill, F12 6GX\"," +
                "\"email\": \"po4@tt.com\"," +
                "\"name\": \"Pet owner 4\"," +
                "\"telephone\": \"+44 2222 444444\"," +
                "\"password\": \"pet_owner_4\"}";

        mockMvc
                .perform(post("/owner/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Pet owner 4"))
                .andExpect(jsonPath("$.address").value("43, High Road, Meadow-under-Hill, F12 6GX"))
                .andExpect(jsonPath("$.email").value("po4@tt.com"))
                .andExpect(jsonPath("$.telephone").value("+44 2222 444444"))
                .andExpect(jsonPath("$.pets").isEmpty());
    }

    @Test
    public void when_OwnerExistsAndCheckCredentialsWithCorrectValues_expect_OwnerReturned() throws Exception
    {
        Address address = new Address(0, "43", "High Road", "Meadow-under-Hill", "F12 6GX");
        Owner owner = new Owner(
                0,
                address,
                "po4@tt.com",
                "Pet owner 4",
                "pet_owner_4",
                "+44 2222 444444",
                null);

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        addressRepository.save(address);
        owner = ownerRepository.save(owner);

        String json_credentialsToCheck =
                "{\"email\": \"po4@tt.com\"," +
                "\"password\": \"pet_owner_4\"}";

        mockMvc
                .perform(post("/owner/checkCredentials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_credentialsToCheck))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Pet owner 4"))
                .andExpect(jsonPath("$.address").value("43, High Road, Meadow-under-Hill, F12 6GX"))
                .andExpect(jsonPath("$.email").value("po4@tt.com"))
                .andExpect(jsonPath("$.telephone").value("+44 2222 444444"))
                .andExpect(jsonPath("$.pets").isEmpty());
    }

    @Test
    public void when_OwnerExistsAndCheckCredentialsWithIncorrectEmail_expect_NoOwnerReturned() throws Exception
    {
        Address address = new Address(0, "43", "High Road", "Meadow-under-Hill", "F12 6GX");
        Owner owner = new Owner(
                0,
                address,
                "po4@tt.com",
                "Pet owner 4",
                "pet_owner_4",
                "+44 2222 444444",
                null);

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        addressRepository.save(address);
        owner = ownerRepository.save(owner);

        String json_credentialsToCheck =
                "{\"email\": \"wrong.email4@tt.com\"," +
                "\"password\": \"pet_owner_4\"}";

        mockMvc
                .perform(post("/owner/checkCredentials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_credentialsToCheck))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void when_OwnerExistsAndCheckCredentialsWithIncorrectPassword_expect_NoOwnerReturned() throws Exception
    {
        Address address = new Address(0, "43", "High Road", "Meadow-under-Hill", "F12 6GX");
        Owner owner = new Owner(
                0,
                address,
                "po4@tt.com",
                "Pet owner 4",
                "pet_owner_4",
                "+44 2222 444444",
                null);

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        addressRepository.save(address);
        owner = ownerRepository.save(owner);

        String json_credentialsToCheck =
                "{\"email\": \"po4@tt.com\"," +
                "\"password\": \"wrong_password\"}";

        mockMvc
                .perform(post("/owner/checkCredentials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_credentialsToCheck))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void when_OwnerExistsAndUpdateOwnerAddressToAnExistingAddress_expect_OwnerReturnedWithNewAddress() throws Exception
    {
        Address address = new Address(0, "43", "High Road", "Meadow-under-Hill", "F12 6GX");
        Address address2 = new Address(0, "3a", "Horizon Road", "Vile Village", "TT12 6PD");
        Owner owner = new Owner(
                0,
                address,
                "po4@tt.com",
                "Pet owner 4",
                "pet_owner_4",
                "+44 2222 444444",
                null);

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        addressRepository.save(address);
        addressRepository.save(address2);
        owner = ownerRepository.save(owner);

        String json_new_address = "3a, Horizon Road, Vile Village, TT12 6PD";

        mockMvc
                .perform(post("/owner/" + owner.getId() + "/update/address")
                        .content(json_new_address))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Pet owner 4"))
                .andExpect(jsonPath("$.address").value("3a, Horizon Road, Vile Village, TT12 6PD"))
                .andExpect(jsonPath("$.email").value("po4@tt.com"))
                .andExpect(jsonPath("$.telephone").value("+44 2222 444444"))
                .andExpect(jsonPath("$.pets").isEmpty());
    }

    @Test
    public void when_OwnerExistsAndUpdateOwnerAddressToNonExistingAddress_expect_OwnerReturnedWithNewAddress() throws Exception
    {
        Address address = new Address(0, "43", "High Road", "Meadow-under-Hill", "F12 6GX");
        Owner owner = new Owner(
                0,
                address,
                "po4@tt.com",
                "Pet owner 4",
                "pet_owner_4",
                "+44 2222 444444",
                null);

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        addressRepository.save(address);
        owner = ownerRepository.save(owner);

        String json_new_address = "3a, Horizon Road, Vile Village, TT12 6PD";

        mockMvc
                .perform(post("/owner/" + owner.getId() + "/update/address")
                        .content(json_new_address))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Pet owner 4"))
                .andExpect(jsonPath("$.address").value("3a, Horizon Road, Vile Village, TT12 6PD"))
                .andExpect(jsonPath("$.email").value("po4@tt.com"))
                .andExpect(jsonPath("$.telephone").value("+44 2222 444444"))
                .andExpect(jsonPath("$.pets").isEmpty());
    }

    @Test
    public void when_OwnerDoesNotExistAndUpdateOwnerAddress_expect_NoOwnerReturned() throws Exception
    {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        String json_new_address = "3a, Horizon Road, Vile Village, TT12 6PD";

        mockMvc
                .perform(post("/owner/1/update/address")
                        .content(json_new_address))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void when_OneOwnerExistsAndGetOwnerList_expect_ListWithOneOwnerReturned() throws Exception
    {
        Address address = new Address(0, "5", "High Street", "Toon Town", "TT1 0AA");
        Owner owner = new Owner(
                0,
                address,
                "owner1@pets.com",
                "Owner 1",
                "owner_1",
                "+44 1111 222222",
                null);

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        addressRepository.save(address);
        owner = ownerRepository.save(owner);

        mockMvc
                .perform(get("/owner/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Owner 1"))
                .andExpect(jsonPath("$[0].address").value("5, High Street, Toon Town, TT1 0AA"))
                .andExpect(jsonPath("$[0].email").value("owner1@pets.com"))
                .andExpect(jsonPath("$[0].telephone").value("+44 1111 222222"))
                .andExpect(jsonPath("$[0].pets").isEmpty());
    }

    @Test
    public void when_NoOwnersExistAndGetOwnerList_expect_EmptyListReturned() throws Exception
    {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        mockMvc
                .perform(get("/owner/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[]"));
    }

    @Test
    public void when_TwoOwnersExistAndGetOwnerList_expect_ListWithTwoOwnersReturned() throws Exception
    {
        Address address = new Address(0, "5", "High Street", "Toon Town", "TT1 0AA");
        Owner owner1 = new Owner(
                0,
                address,
                "owner1@pets.com",
                "Owner 1",
                "owner_1",
                "+44 1111 222222",
                null);
        Owner owner2 = new Owner(
                0,
                address,
                "owner2@pets.com",
                "Owner 2",
                "owner_2",
                "+44 1111 444444",
                null);

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        addressRepository.save(address);
        owner1 = ownerRepository.save(owner1);
        owner2 = ownerRepository.save(owner2);

        mockMvc
                .perform(get("/owner/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Owner 1"))
                .andExpect(jsonPath("$[1].name").value("Owner 2"));
    }

    @Test
    public void when_OwnerExistsAndGetOwner_expect_OwnerReturned() throws Exception
    {
        Address address = new Address(0, "5", "High Street", "Toon Town", "TT1 0AA");
        Owner owner1 = new Owner(
                0,
                address,
                "owner1@pets.com",
                "Owner 1",
                "owner_1",
                "+44 1111 222222",
                null);
        Owner owner2 = new Owner(
                0,
                address,
                "owner2@pets.com",
                "Owner 2",
                "owner_2",
                "+44 1111 444444",
                null);

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        addressRepository.save(address);
        owner1 = ownerRepository.save(owner1);
        owner2 = ownerRepository.save(owner2);

        mockMvc
                .perform(get("/owner/" + owner2.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Owner 2"))
                .andExpect(jsonPath("$.address").value("5, High Street, Toon Town, TT1 0AA"))
                .andExpect(jsonPath("$.email").value("owner2@pets.com"))
                .andExpect(jsonPath("$.telephone").value("+44 1111 444444"))
                .andExpect(jsonPath("$.pets").isEmpty());
    }

    @Test
    public void when_OwnerDoesNotExistAndGetOwner_expect_NoOwnerReturned() throws Exception
    {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        mockMvc
                .perform(get("/owner/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void when_OwnerWithNoPetsExistsAndDeleteOwner_expect_TrueReturnedAndOwnerDeleted() throws Exception
    {
        Address address = new Address(0, "5", "High Street", "Toon Town", "TT1 0AA");
        Owner owner1 = new Owner(
                0,
                address,
                "owner1@pets.com",
                "Owner 1",
                "owner_1",
                "+44 1111 222222",
                null);

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        addressRepository.save(address);
        owner1 = ownerRepository.save(owner1);

        mockMvc
                .perform(delete("/owner/" + owner1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        mockMvc
                .perform(get("/owner/" + owner1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void when_OwnerDoesNotExistAndDeleteOwner_expect_FalseReturned() throws Exception
    {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        mockMvc
                .perform(delete("/owner/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void when_OwnerWithPetExistsAndDeleteOwner_expect_FalseReturnedAndPetNotDeletedAndOwnerNotDeleted() throws Exception
    {
        Address address = new Address(0, "5", "High Street", "Toon Town", "TT1 0AA");
        Owner owner = new Owner(
                0,
                address,
                "owner1@pets.com",
                "Owner 1",
                "owner_1",
                "+44 1111 222222",
                null);

        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        addressRepository.save(address);
        owner = ownerRepository.save(owner);

        Pet pet = new Pet(0, "Snuffler", LocalDate.parse("2022-10-03"), owner, "Dog");
        pet = petRepository.save(pet);

        mockMvc
                .perform(delete("/owner/" + owner.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        mockMvc
                .perform(get("/pet/" + pet.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(pet.getId()));

        mockMvc
                .perform(get("/owner/" + owner.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Owner 1"));
    }

    @Test
    public void when_CheckCredentialsWithInvalidCredentials_expect_BadRequestAndCorrectErrorMessages() throws Exception
    {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        String json_credentialsToCheck =
                "{\"email\": \"po4tt.com\"," +
                "\"password\": \"\"}";

        mockMvc
                .perform(post("/owner/checkCredentials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_credentialsToCheck))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email").value("Email is not in the correct format"))
                .andExpect(jsonPath("$.password").value("Password cannot be blank"));
    }

    @Test
    public void when_CreateOwnerWithInvalidValues_expect_BadRequestAndCorrectErrorMessages() throws Exception
    {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        String json_ownerToCreate =
                "{\"address\": \"\"," +
                "\"email\": \"??\"," +
                "\"name\": \"\"," +
                "\"telephone\": \"\"," +
                "\"password\": \"\"}";

        mockMvc
                .perform(post("/owner/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.address").value("Address cannot be blank"))
                .andExpect(jsonPath("$.email").value("Email is not in the correct format"))
                .andExpect(jsonPath("$.name").value("Name cannot be blank"))
                .andExpect(jsonPath("$.telephone").value("Telephone cannot be blank"))
                .andExpect(jsonPath("$.password").value("Password cannot be blank"));
    }

    @Test
    public void when_CreateOwnerWithNullEmail_expect_BadRequestAndCorrectErrorMessages() throws Exception
    {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        String json_ownerToCreate =
                "{\"address\": \"\"," +
                "\"name\": \"\"," +
                "\"telephone\": \"\"," +
                "\"password\": \"\"}";

        mockMvc
                .perform(post("/owner/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_ownerToCreate)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.address").value("Address cannot be blank"))
                .andExpect(jsonPath("$.email").value("Email must have a value"))
                .andExpect(jsonPath("$.name").value("Name cannot be blank"))
                .andExpect(jsonPath("$.telephone").value("Telephone cannot be blank"))
                .andExpect(jsonPath("$.password").value("Password cannot be blank"));
    }

    @Test
    public void when_DeleteOwnerWithInvalidId_expect_BadRequestAndCorrectErrorMessage() throws Exception
    {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        mockMvc
                .perform(delete("/owner/0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Validation error: Id must be greater than zero"));
    }

    @Test
    public void when_GetOwnerWithInvalidId_expect_BadRequestAndCorrectErrorMessage() throws Exception
    {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        mockMvc
                .perform(get("/owner/0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Validation error: Id must be greater than zero"));
    }

    @Test
    public void when_UpdateOwnerWithInvalidId_expect_BadRequestAndCorrectErrorMessage() throws Exception
    {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        String json_new_address = "Somewhere Street";

        mockMvc
                .perform(post("/owner/0/update/address")
                        .content(json_new_address))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Validation error: Id must be greater than zero"));
    }

    @Test
    public void when_UpdateOwnerWithBlankAddress_expect_BadRequestAndCorrectErrorMessage() throws Exception
    {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        String json_new_address = " ";

        mockMvc
                .perform(post("/owner/1/update/address")
                        .content(json_new_address))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Validation error: Address cannot be blank"));
    }

    @Test
    public void when_UpdateOwnerWithNoAddress_expect_BadRequestAndCorrectErrorMessage() throws Exception
    {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
        addressRepository.deleteAll();

        mockMvc
                .perform(post("/owner/1/update/address"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));
    }
}
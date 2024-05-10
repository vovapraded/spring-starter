package by.javaguru.http.controller;

import by.javaguru.annotation.IT;
import by.javaguru.spring.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IT
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class UserControllerIT {
    private  final MockMvc mockMvc;
    @Test
    void findAll() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("user/users"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
                .andExpect(MockMvcResultMatchers.model().attribute("users", IsCollectionWithSize.hasSize(5)));
    }
    @Test
    void create() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .param(UserCreateEditDto.Fields.username,"Test")
                .param(UserCreateEditDto.Fields.firstname,"Alexey")
                .param(UserCreateEditDto.Fields.lastname,"TestTest")
                        .param(UserCreateEditDto.Fields.role,"ADMIN")
                        .param(UserCreateEditDto.Fields.companyId,"1")
                        .param(UserCreateEditDto.Fields.birthDate,"01-01-2000")
                )

                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }
}

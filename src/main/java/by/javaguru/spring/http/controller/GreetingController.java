package by.javaguru.spring.http.controller;

import by.javaguru.spring.database.entity.Role;
import by.javaguru.spring.database.repository.CompanyRepository;
import by.javaguru.spring.dto.CompanyReadDto;
import by.javaguru.spring.dto.UserReadDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes("user")

public class GreetingController {
    @ModelAttribute("roles")
    public List<Role> getRoles(){
      return   Arrays.asList(Role.values());
    }
    @GetMapping("/hello")
    public String hello(Model model, UserReadDto userReadDto){
        model.addAttribute("user",userReadDto);
        return "greeting/hello";

    }
    @GetMapping("/hello/{id}")
    public String hello(ModelAndView mv, CompanyRepository companyRepository,
                              HttpServletRequest request,
                              @RequestParam Integer age,
                              Model model,
                              @PathVariable("id") Integer id){
//        model.addAttribute("user",new UserReadDto(1L,"Andrei"));
        return "greeting/hello";

    }
    @GetMapping("/bye")

    public String bye(ModelAndView mv, @SessionAttribute("user") UserReadDto user){
        return "greeting/bye";
    }
}

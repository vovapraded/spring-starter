package by.javaguru.spring.http.controller;

import by.javaguru.spring.database.entity.Company;
import by.javaguru.spring.database.entity.Role;
import by.javaguru.spring.dto.PageResponse;
import by.javaguru.spring.dto.UserCreateEditDto;
import by.javaguru.spring.dto.UserFilter;
import by.javaguru.spring.dto.UserReadDto;
import by.javaguru.spring.service.CompanyService;
import by.javaguru.spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final CompanyService companyService;

    @GetMapping
    public String findAll(Model model, UserFilter filter, Pageable pageable){
//        model.addAttribute("users",userService.findAll());
        Page<UserReadDto> page = userService.findAll(filter,pageable);
        model.addAttribute("users", PageResponse.of(page));
        model.addAttribute("filter", filter);

        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
//        model.addAttribute("user",userService.findById(id));
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user",user);
                    model.addAttribute("roles", Role.values());
                    model.addAttribute("companies", companyService.findAll());


                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public String create(@ModelAttribute("id") @Validated UserCreateEditDto user, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            var userDto = userService.create(user);
            redirectAttributes.addFlashAttribute("user",user);
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            return "redirect:/users/registration";

        }
        UserReadDto dto = userService.create(user);
        return "redirect:/users/" + dto.getId();

    }
    @PostMapping("{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute @Validated UserCreateEditDto user){
        userService.update(id,user);
        return userService.update(id,user)
                .map(it->"redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PostMapping("{id}/delete")
    public String delete(@ModelAttribute("id") Long id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }
        @GetMapping("/registration") public  String registration(Model model,@ModelAttribute("user") UserCreateEditDto user){
        model.addAttribute("user",user);
            model.addAttribute("roles",Role.values());
            model.addAttribute("companies", companyService.findAll());
return "user/registration";

        }


}

package proiect.Place.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proiect.DTO.UserDto;
import proiect.Place.Service.PlaceCategoryService;
import proiect.User.Service.UserService;


@Controller
@RequiredArgsConstructor
@RequestMapping("categories")
public class PlaceCategoryController {
    private final PlaceCategoryService placeCategoryService;
    private final UserService userService;

    @GetMapping
    public String displayAllCategories(Model model, Authentication authentication){
        model.addAttribute("sitetitle", "Place categories");
        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", placeCategoryService.findAll());

        return "categories/index";
    }


}

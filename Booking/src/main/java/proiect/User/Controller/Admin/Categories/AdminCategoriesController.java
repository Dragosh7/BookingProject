package proiect.User.Controller.Admin.Categories;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import proiect.DTO.UserDto;
import proiect.Place.Service.PlaceCategoryService;
import proiect.User.Service.UserService;
import proiect.Model.Places.PlaceCategory;
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/categories")

public class AdminCategoriesController {
    private final PlaceCategoryService placeCategoryService;
    private final UserService userService;
    @GetMapping()
    public String displayAllCategories(Model model, Authentication authentication){
        model.addAttribute("sitetitle", "LP - Categories");
        model.addAttribute("headertext", "View all categories!");

        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", placeCategoryService.findAll());
        return "admin/categories/index";
    }

    @GetMapping("/create")
    public String renderCreateCategoryForm(Model model, Authentication authentication){
        model.addAttribute("sitetitle", "LP - Create Category");
        model.addAttribute("headertext", "Create Category!");

        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        model.addAttribute("title", "Create Place");
        model.addAttribute("category", new PlaceCategory());
        return "admin/categories/create";
    }
    @PostMapping("/create")
    public String createEvent(@ModelAttribute("category") @Valid PlaceCategory category, Errors errors, Model model, Authentication authentication){
        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Place");
            model.addAttribute("categories", placeCategoryService.findAll());
            model.addAttribute("userLogged", false);
            return "admin/categories/create";
        }
        placeCategoryService.save(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/delete")
    public String displayDeleteEventForm(Model model, Authentication authentication){
        model.addAttribute("sitetitle", "LP - Delete Category");
        model.addAttribute("headertext", "Delete Category!");
        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        model.addAttribute("title", "Delete Place");
        model.addAttribute("categories", placeCategoryService.findAll());
        return "admin/categories/delete";
    }
    @PostMapping("/delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] categoryIDs, Model model, Authentication authentication){
        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        if(categoryIDs != null){
            for(int id : categoryIDs){
                placeCategoryService.deleteById(id);
            }
        }
        return "redirect:/admin/categories";
    }
}

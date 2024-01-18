package proiect.Place.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proiect.Place.Repository.*;
import proiect.Place.Service.*;
import proiect.DTO.UserDto;
import proiect.Model.Places.*;
import proiect.User.Service.UserService;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("places")
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceCategoryService placeCategoryService;
    private final UserService userService;
    private final TagService tagService;

    @GetMapping
    public String displayAllPlaces(@RequestParam(required = false) Integer categoryID, @RequestParam(required = false) Integer placeTagID, Model model, Authentication authentication){
        model.addAttribute("sitetitle", "Places");
        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        if (categoryID == null){
            model.addAttribute("title","All Places");
            model.addAttribute("places", placeService.findAll());
            model.addAttribute("userLogged", false);
        }else{
            Optional<PlaceCategory> result = placeCategoryService.findById(categoryID);
            if(result.isEmpty()){
                model.addAttribute("title","Invalid Category ID: " + categoryID);
                model.addAttribute("userLogged", false);
            }else{
                PlaceCategory category = result.get();
                model.addAttribute("title", "Places in category: " + category.getName());
                model.addAttribute("places", category.getPlaces());
                model.addAttribute("userLogged", false);
            }
        }
        if (placeTagID == null){
            model.addAttribute("title","All Places");
            model.addAttribute("places", placeService.findAll());
        }else{
            Optional<PlaceTag> result = tagService.findById(placeTagID);
            if(result.isEmpty()){
                model.addAttribute("title","Invalid Category ID: " + categoryID);
            }else{
                PlaceTag tag = result.get();
                model.addAttribute("title", "Places in Tag: " + tag.getName());
                model.addAttribute("places", tag.getPlaces());
            }
        }
        return "places/index";
    }


    @GetMapping("detail")
    public String displayPlaceDetails(@RequestParam Integer placeID, Model model) {
        model.addAttribute("sitetitle", "Place Details");
        Optional<Place> result = placeService.findById(placeID);
        if(result.isEmpty()){
            model.addAttribute("title", "Invalid Place ID:" + placeID);
            model.addAttribute("userLogged", false);
        }else{
            Place place = result.get();
            model.addAttribute("title", place.getTitle() + " Details");
            model.addAttribute("place", place);
            model.addAttribute("tags", place.getTags());
            model.addAttribute("userLogged", false);
        }
        return "places/detail";
    }
}

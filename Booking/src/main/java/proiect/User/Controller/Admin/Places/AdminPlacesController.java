package proiect.User.Controller.Admin.Places;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import proiect.Place.Service.TagService;
import proiect.DTO.UserDto;
import proiect.DTO.PlaceTagDTO;
import proiect.Place.Service.PlaceCategoryService;
import proiect.Place.Service.PlaceService;
import proiect.User.Service.UserService;
import proiect.Model.Places.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/places")
public class AdminPlacesController {
    private final PlaceService placeService;
    private final PlaceCategoryService placeCategoryService;
    private final TagService tagService;
    private final UserService userService;
    @GetMapping()
    public String displayAllPlaces(@RequestParam(required = false) Integer categoryID, @RequestParam(required = false) Integer placeTagID, Model model, Authentication authentication){
        model.addAttribute("sitetitle", "Places");
        model.addAttribute("headertext", "View all places!");
        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        if (categoryID == null){
            model.addAttribute("title","All Places");
            model.addAttribute("places", placeService.findAll());
        }else{
            Optional<PlaceCategory> result = placeCategoryService.findById(categoryID);
            if(result.isEmpty()){
                model.addAttribute("title","Invalid Category ID: " + categoryID);
            }else{
                PlaceCategory category = result.get();
                model.addAttribute("title", "Places in category: " + category.getName());
                model.addAttribute("places", category.getPlaces());
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
        return "admin/places/index";
    }


    @GetMapping("/create")
    public String renderCreateEventForm(Model model, Authentication authentication){
        model.addAttribute("sitetitle", "Create Place");
        model.addAttribute("headertext", "Create place!");
        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        model.addAttribute("title", "Create Place");
        model.addAttribute("place", new Place());
        model.addAttribute("placeCategories", placeCategoryService.findAll());
        return "admin/places/create";
    }
    @PostMapping("/create")
    public String createPlace(@ModelAttribute @Valid Place place, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile, Errors errors, Model model, Authentication authentication){
        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Place");
            model.addAttribute("placeCategories", placeCategoryService.findAll());
            return "admin/places/create";
        }
        if(imageFile != null){
            try {
                place.getPlaceDetails().setImageData(imageFile.getBytes());
            } catch (IOException ignored) {

            }
        }
        placeService.save(place);
        return "redirect:/admin/places";
    }
    @GetMapping("/delete")
    public String displayDeletePlaceForm(Model model, Authentication authentication){
        model.addAttribute("sitetitle", "Delete Place");
        model.addAttribute("headertext", "Delete places!");
        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        model.addAttribute("title", "Delete Places");
        model.addAttribute("places", placeService.findAll());
        return "admin/places/delete";
    }
    @PostMapping("/delete")
    public String processDeletePlacesForm(@RequestParam(required = false) int[] placeIDs, Model model) {
        if(placeIDs != null){
            for(int id : placeIDs){
                placeService.deleteById(id);
            }
        }
        return "redirect:/admin/places";
    }

    @GetMapping("/add-tag")
    public String displayAddTagForm(@RequestParam Integer placeID, Model model, Authentication authentication){
        model.addAttribute("sitetitle", "Add Tag Place");
        model.addAttribute("headertext", "Add Tag Place!");
        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        Optional<Place> result = placeService.findById(placeID);
        if(result.isEmpty()){
            model.addAttribute("title", "Invalid Place ID:" + placeID);
        }else {
            Place place = result.get();
            model.addAttribute("title", "Add PlaceTag to: " + place.getTitle());
            List<PlaceTag> tags = (List<PlaceTag>) tagService.findAll();
            tags.removeAll(place.getTags());
            model.addAttribute("tags", tags);
            PlaceTagDTO placeTagDTO = new PlaceTagDTO();
            placeTagDTO.setPlace(place);
            model.addAttribute("placeTagDTO", placeTagDTO);
        }
        return "admin/places/add-tag";
    }
    @PostMapping("/add-tag")
    public String processAddTagForm(@ModelAttribute @Valid PlaceTagDTO placeTagDTO, Errors errors, Model model, Authentication authentication){
        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        if(!errors.hasErrors()){
            Place place = placeTagDTO.getPlace();
            PlaceTag tag = placeTagDTO.getTag();
            if(!place.getTags().contains(tag)){
                place.addTag(tag);
                placeService.save(place);
            }
            return "redirect:/places/detail?placeID=" + place.getID();
        }
        return "redirect:/admin/places/add-tag";
    }

    @RequestMapping("/update/{placeID}")
    public String displayPlaceUpdate(@PathVariable("placeID") int placeID, Model model) {
        model.addAttribute("headertext", "Update Place!");
        model.addAttribute("placeCategories", placeCategoryService.findAll());
        model.addAttribute("sitetitle", "Update Place");
        Optional<Place> result = placeService.findById(placeID);
        if(result.isEmpty()){
            return "redirect: /admin/places";
        }
        Place place = result.get();
        model.addAttribute("place", place);
        return "/admin/places/update";
    }
    @RequestMapping("/update")
    public String createPlace(@ModelAttribute Place place, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        if(imageFile != null){
            try {
                place.getPlaceDetails().setImageData(imageFile.getBytes());
            } catch (IOException ignored) {

            }
        }
        placeService.save(place);
        return "redirect:/admin/places";
    }
}

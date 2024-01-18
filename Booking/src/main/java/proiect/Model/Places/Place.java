package proiect.Model.Places;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import proiect.Model.AbstractEntity;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@DynamicUpdate
public class Place extends AbstractEntity{
    @NotBlank(message = "Title is required.")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters.")
    private String title;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private PlaceDetails placeDetails;
    @ManyToOne
    @NotNull(message = "Category is required.")
    private PlaceCategory placeCategory;
    @ManyToMany
    private final List<PlaceTag> tags = new ArrayList<>();

    public Place(String title, PlaceCategory placeCategory, PlaceDetails placeDetails) {
        this.title = title;
        this.placeCategory = placeCategory;
        this.placeDetails = placeDetails;
    }

    public Place() {
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public void setPlaceDetails(PlaceDetails placeDetails) {
        this.placeDetails = placeDetails;
    }

    public void setPlaceCategory(PlaceCategory placeCategory) {
        this.placeCategory = placeCategory;
    }

    public void addTag(PlaceTag tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return title;
    }

}

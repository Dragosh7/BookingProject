package proiect.Model.Places;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
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
public class PlaceTag extends AbstractEntity {
    @Size(min = 1, max = 25)
    @NotBlank
    private String name;
    @ManyToMany(mappedBy = "tags")
    private List<Place> places = new ArrayList<>();

    public PlaceTag(String name, List<Place> places) {
        this.name = name;
        this.places = places;
    }

    public PlaceTag() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public String getDisplayName() {
        return "#" + name + " ";
    }
}

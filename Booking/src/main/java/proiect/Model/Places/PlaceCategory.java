package proiect.Model.Places;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class PlaceCategory extends AbstractEntity {
    @Size(min = 3, message = "Name must be at least 3 characters long.")
    private String name;
    @OneToMany(mappedBy = "placeCategory")
    private final List<Place> places = new ArrayList<>();
    public PlaceCategory(@Size(min = 3, message = "Name must be at least 3 characters long.") String name) {
        this.name = name;
    }
    public PlaceCategory() {
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

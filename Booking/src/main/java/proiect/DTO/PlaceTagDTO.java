package proiect.DTO;

import jakarta.validation.constraints.NotNull;
import proiect.Model.Places.*;


public class PlaceTagDTO {
    @NotNull
    private Place place;
    private PlaceTag tag;
    public PlaceTagDTO(){}

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public PlaceTag getTag() {
        return tag;
    }

    public void setTag(PlaceTag tag) {
        this.tag = tag;
    }
}

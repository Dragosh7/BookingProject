package proiect.Place.Service;

import proiect.Model.Places.*;

import java.util.Optional;

public interface PlaceCategoryService {
    void save(PlaceCategory placeCategory);
    void deleteById(Integer ID);
    Optional<PlaceCategory> findById(Integer ID);
    Iterable<PlaceCategory> findAll();
    void flush();
}

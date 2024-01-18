package proiect.Place.Service;


import proiect.Model.Places.*;

import java.util.Optional;

public interface PlaceService {
    void save(Place place);
    void deleteById(Integer ID);
    Optional<Place> findById(Integer ID);
    Iterable<Place> findAll();
    void flush();
}

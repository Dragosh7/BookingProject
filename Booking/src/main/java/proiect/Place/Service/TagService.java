package proiect.Place.Service;

import proiect.Model.Places.PlaceTag;

import java.util.Optional;

public interface TagService {
    void save(PlaceTag tag);
    void deleteById(Integer ID);
    Optional<PlaceTag> findById(Integer ID);
    Iterable<PlaceTag> findAll();
    void flush();
}

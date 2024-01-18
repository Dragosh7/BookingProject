package proiect.Place.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import proiect.Place.Repository.*;
import proiect.Model.Places.*;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class PlaceCategoryServiceImpl implements PlaceCategoryService{
    private final PlaceCategoryRepository placeCategoryRepository;

    @Override
    public void save(PlaceCategory placeCategory) {
        placeCategoryRepository.save(placeCategory);
    }

    @Override
    public void deleteById(Integer ID) {
        placeCategoryRepository.deleteById(ID);
    }

    @Override
    public Optional<PlaceCategory> findById(Integer ID) {
        return placeCategoryRepository.findById(ID);
    }

    @Override
    public Iterable<PlaceCategory> findAll() {
        return placeCategoryRepository.findAll();
    }

    @Override
    public void flush() {
        placeCategoryRepository.flush();
    }
}

package proiect.Place.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import proiect.Place.Repository.*;
import proiect.Model.Places.Place;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService{
    private final PlaceRepository placeRepository;
    @Override
    public void save(Place place) {
        placeRepository.save(place);
    }

    @Override
    public void deleteById(Integer ID) {
        placeRepository.deleteById(ID);
    }

    @Override
    public Optional<Place> findById(Integer ID) {
        return placeRepository.findById(ID);
    }

    @Override
    public Iterable<Place> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public void flush() {
        placeRepository.flush();
    }
}

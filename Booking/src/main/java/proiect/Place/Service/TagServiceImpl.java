package proiect.Place.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import proiect.Place.Repository.TagRepository;
import proiect.Model.Places.PlaceTag;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{
    private final TagRepository tagRepository;
    @Override
    public void save(PlaceTag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void deleteById(Integer ID) {
        tagRepository.deleteById(ID);
    }

    @Override
    public Optional<PlaceTag> findById(Integer ID) {
        return tagRepository.findById(ID);
    }

    @Override
    public Iterable<PlaceTag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public void flush() {

    }
}

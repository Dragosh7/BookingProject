package proiect.Place.Repository;

import org.springframework.data.repository.CrudRepository;
import proiect.Model.Places.*;

public interface TagRepository extends CrudRepository<PlaceTag, Integer> {
}

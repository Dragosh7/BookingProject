package proiect.Place.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.Model.Places.*;

@Repository
public interface PlaceCategoryRepository extends JpaRepository<PlaceCategory, Integer> {
}
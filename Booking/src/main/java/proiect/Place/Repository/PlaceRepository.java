package proiect.Place.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proiect.Model.Places.*;
import proiect.Model.User.UserRole;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
}

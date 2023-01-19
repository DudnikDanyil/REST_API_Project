package springcourse.Project3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springcourse.Project3.models.Measurements;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {

}

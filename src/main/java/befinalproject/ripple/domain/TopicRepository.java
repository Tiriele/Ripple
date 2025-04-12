package befinalproject.ripple.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository <Topic, Long> {
    List<Topic> findByName(String name);
}

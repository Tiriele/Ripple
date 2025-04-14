package befinalproject.ripple.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PostRepositoryTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TopicRepository topicrepository;

    @Test
    public void testCreateNewPost() {
        Post post = new Post("What I cooked for today", "SuperFoodie", LocalDate.parse("2025-06-06", formatter), LocalDate.parse("2026-07-07", formatter), 6, 7, 2, topicrepository.findByName("Cooking").get(0), "Oats, eggs, and some veggies.");
        postRepository.save(post);

        assertThat(post.getPostId()).isNotNull();
    }

    @Test
    public void testFindById() {
        Optional<Post> post = postRepository.findById(3L);
        
        assertThat(post).isPresent();
        assertThat(post.get().getCreator()).isEqualTo("GymGuru");
    }

    @Test
    public void testDeletePost() {
        Post post = new Post("What I cooked for today", "SuperFoodie", LocalDate.parse("2025-06-06", formatter), LocalDate.parse("2026-07-07", formatter), 6, 7, 2, topicrepository.findByName("Cooking").get(0), "Oats, eggs, and some veggies.");
        postRepository.save(post);

        postRepository.delete(post);
        assertThat(postRepository.findById(post.getPostId())).isEmpty();
    }
}

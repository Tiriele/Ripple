package befinalproject.ripple;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import befinalproject.ripple.web.PostController;
import befinalproject.ripple.web.PostRestController;
import befinalproject.ripple.web.TopicController;

@SpringBootTest
class RippleApplicationTests {

	@Autowired
	private PostController postController;

	@Autowired
	private PostRestController postRestController;

	@Autowired
	private TopicController topicController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(postController).isNotNull();
		assertThat(postRestController).isNotNull();
		assertThat(topicController).isNotNull();
	}

}

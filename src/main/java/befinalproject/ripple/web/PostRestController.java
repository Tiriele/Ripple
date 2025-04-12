package befinalproject.ripple.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import befinalproject.ripple.domain.Post;
import befinalproject.ripple.domain.PostRepository;

@CrossOrigin
@Controller
public class PostRestController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public @ResponseBody List<Post> getAllPostsRest() {
        return (List<Post>) postRepository.findAll();
    }

    @GetMapping("/posts/{id}")
    public @ResponseBody Optional<Post> getPostById (@PathVariable(name = "id") Long postId) {
        return postRepository.findById(postId);
}

    @PostMapping("/posts")
    public @ResponseBody Post addNewPostRest(@RequestBody Post newPost) {
        return postRepository.save(newPost);
    }
}

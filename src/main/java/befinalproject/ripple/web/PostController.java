package befinalproject.ripple.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import befinalproject.ripple.domain.Post;
import befinalproject.ripple.domain.PostRepository;
import befinalproject.ripple.domain.TopicRepository;

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/login")
    public String login() {
       return "login";
    }

    @GetMapping("/homepage")
    public String getHomepage(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "homepage";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deletePost(@PathVariable("id") Long postId, Model model) {
        postRepository.deleteById(postId);
        return "redirect:/homepage";
    }

    @GetMapping("/create")
    public String addPost(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("topics", topicRepository.findAll());
        return "createpost";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute Post post) {
        postRepository.save(post);
        return "redirect:/homepage";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable("id") Long postId, Model model) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid book Id: " + postId));
        model.addAttribute("post", post);
        model.addAttribute("topics", topicRepository.findAll());
        return "editpost";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable("id") Long postId, @ModelAttribute Post post) {
        post.setPostId(postId);
        postRepository.save(post);
        return "redirect:/homepage";
    }

    @GetMapping("/post/{id}")
    public String showPost(@PathVariable("id") Long postId, Model model) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid post Id: " + postId));
    model.addAttribute("post", post);
    return "showpost";
}


}

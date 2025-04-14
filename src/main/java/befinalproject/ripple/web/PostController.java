package befinalproject.ripple.web;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

import befinalproject.ripple.domain.Comment;
import befinalproject.ripple.domain.CommentRepository;
import befinalproject.ripple.domain.Post;
import befinalproject.ripple.domain.PostRepository;
import befinalproject.ripple.domain.TopicRepository;

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CommentRepository commentRepository;

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
    public String savePost(@Valid @ModelAttribute Post post, BindingResult bindingResult) {
    
        if (bindingResult.hasErrors()) {
            return "createpost";
        }
    
    post.setCreationDate(LocalDate.now());
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    post.setCreator(auth.getName());
    
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
    public String showPost(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid post Id: " + id));

        List<Comment> comments = commentRepository.findByPost(post);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("username", username);
        return "showpost";
    }

    @PostMapping("/like/{id}")
    public String likePost(@PathVariable("id") Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
        return "redirect:/post/" + postId;
    }

    @PostMapping("/dislike/{id}")
    public String dislikePost(@PathVariable("id") Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        post.setDislikes(post.getDislikes() + 1);
        postRepository.save(post);
        return "redirect:/post/" + postId;
    }

    @PostMapping("/comment/{postId}")
    public String addComment(@PathVariable Long postId, @RequestParam("content") String content, Principal principal) {
    Post post = postRepository.findById(postId)
                  .orElseThrow(() -> new RuntimeException("Post not found"));

    Comment comment = new Comment(post, content, principal.getName());
    commentRepository.save(comment);

    post.setComments(post.getComments() + 1);
    postRepository.save(post);

    return "redirect:/post/" + postId;
}

}

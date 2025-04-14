package befinalproject.ripple.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentid;

    @ManyToOne
    private Post post;
    private String content;
    private String author;
    private LocalDate createdAt;
    

    public Comment() {
        this.createdAt = LocalDate.now();
    }

    public Comment(Post post, String content, String author) {
        this.post = post;
        this.content = content;
        this.author = author;
        this.createdAt = LocalDate.now();
    }

    public Long getCommentid() {
        return commentid;
    }

    public void setCommentid(Long id) {
        this.commentid = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    
}

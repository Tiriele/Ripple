package befinalproject.ripple.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    private String creator;
    private LocalDate creationDate;
    private LocalDate lastCommentedDate;
    private int comments;
    private int likes;
    private int dislikes;

    @NotNull(message = "Content cannot be null")
    @NotEmpty(message = "Content cannot be empty")
    private String content;

    @ManyToOne
    @JsonIgnoreProperties("posts")
    @JoinColumn(name = "topicid")
    private Topic topic;

    public Post(String title, String creator, LocalDate creationDate, LocalDate lastCommentedDate, int comments, int likes, int dislikes, Topic topic, String content) {
        this.title = title;
        this.creator = creator;
        this.creationDate = creationDate;
        this.lastCommentedDate = lastCommentedDate;
        this.comments = comments;
        this.likes = likes;
        this.dislikes = dislikes;
        this.topic = topic;
        this.content = content;
    }

    public Post() {
        this.title = null;
        this.creator = null;
        this.creationDate = LocalDate.now();
        this.lastCommentedDate = null;
        this.comments = 0;
        this.likes = 0;
        this.dislikes = 0;
        this.topic = null;
        this.content = null;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastCommentedDate() {
        return lastCommentedDate;
    }

    public void setLastCommentedDate(LocalDate lastCommentedDate) {
        this.lastCommentedDate = lastCommentedDate;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post [postId=" + postId + ", title=" + title + ", creator=" + creator + ", creationDate=" + creationDate
                + ", lastCommentedDate=" + lastCommentedDate + ", comments=" + comments + ", likes=" + likes + ", dislikes=" + dislikes + ", content=" + content + "]";
    }

}

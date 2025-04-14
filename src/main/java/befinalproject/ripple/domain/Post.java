package befinalproject.ripple.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;
    private String title;
    private String creator;
    private LocalDate creationDate;
    private LocalDate lastCommentedDate;
    private int comments;
    private int likes;
    private int dislikes;
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
        this.creationDate = null;
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

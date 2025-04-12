package befinalproject.ripple.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long topicid;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    @JsonIgnoreProperties("topic")
    private List<Post> posts;
    
    public Topic(String name) {
        this.name = name;
    }

    public Topic() {
        this.name = null;
    }

    public Long getTopicid() {
        return topicid;
    }

    public void setTopicid(Long id) {
        this.topicid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Topic [id=" + topicid + ", name=" + name + "]";
    }

}

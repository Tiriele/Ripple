package befinalproject.ripple.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import befinalproject.ripple.domain.Topic;
import befinalproject.ripple.domain.TopicRepository;

@Controller
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/topiclist")
    public String getTopiclist(Model model) {
        model.addAttribute("topics", topicRepository.findAll());
        return "topiclist";
    }

    @GetMapping("/deleteTopic/{id}")
    public String deleteTopic(@PathVariable("id") Long TopicId, Model model) {
        topicRepository.deleteById(TopicId);
        return "redirect:/topiclist";
    }

    @GetMapping("/createTopic")
    public String addTopic(Model model) {
        model.addAttribute("topic", new Topic());
        return "createTopic";
    }

    @PostMapping("/saveTopic")
    public String saveTopic(@ModelAttribute Topic topic) {
        topicRepository.save(topic);
        return "redirect:/topiclist";
    }

    @GetMapping("/edittopic/{id}")
    public String editTopic(@PathVariable("id") Long topicId, Model model) {
        Topic topic = topicRepository.findById(topicId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Topic Id: " + topicId));
        model.addAttribute("topic", topic);
        return "edittopic";
    }

    @PostMapping("/updatetopic/{id}")
    public String updateTopic(@PathVariable("id") Long topicId, @ModelAttribute Topic topic) {
        topic.setTopicid(topicId);
        topicRepository.save(topic);
        return "redirect:/topiclist";
    }
}

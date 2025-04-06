package roadmap.project;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    private final GitParser parser;

    WebController(GitParser parser) {
        this.parser = parser;
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/results")
    public String viewResults(@RequestParam("githubUrl") String githubUrl, Model model) {

        String username = githubUrl.replace("https://github.com/", "").split("/")[0];

        String formattedResult = parser.getFormattedActivity(username);

        model.addAttribute("username", username);
        model.addAttribute("result", formattedResult);

        return "results";
    }
}

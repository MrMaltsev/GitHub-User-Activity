package roadmap.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final GitParser parser;

    ApiController(GitParser parser) {
        this.parser = parser;
    }

    @GetMapping("/api/activity")
    public GitHubEvent[] getActivity(@RequestParam String user) {
        return parser.getUserActivity(user);
    }
}

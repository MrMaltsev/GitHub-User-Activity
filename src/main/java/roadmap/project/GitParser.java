package roadmap.project;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitParser {

    private final RestTemplate restTemplate = new RestTemplate();

    public GitHubEvent[] getUserActivity(String username) {
        String url = "https://api.github.com/users/" + username + "/events";

        ResponseEntity<GitHubEvent[]> response = restTemplate.getForEntity(url, GitHubEvent[].class);

        return response.getBody();
    }
}

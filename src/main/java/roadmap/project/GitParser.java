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

    public String getFormattedActivity(String username) {
        GitHubEvent[] events = getUserActivity(username);
        return formatEvents(events);
    }

    public String formatEvents(GitHubEvent[] events) {
        StringBuilder result = new StringBuilder();

        for(GitHubEvent event : events) {
            switch (event.type) {
                case "PushEvent" :
                    int commits = event.payload.commits.size();
                    result.append(" - Pushed ").append(commits)
                            .append(" commits to ").append(event.repo.name).append("\n");
                    break;
                case "WatchEvent" :
                    result.append(" - Starred ").append(event.repo.name).append("\n");
                    break;
                case "ForkEvent" :
                    result.append(" - Forked ").append(event.repo.name).append("\n");
            }
        }
        return result.toString();
    }


}

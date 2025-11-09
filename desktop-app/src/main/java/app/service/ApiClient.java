package app.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ApiClient {
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    private final String teamsBase = System.getenv().getOrDefault("TEAMS_BASE_URL", "http://localhost:8181");
    private final String playersBase = System.getenv().getOrDefault("PLAYERS_BASE_URL", "http://localhost:8182");
    private final String matchesBase = System.getenv().getOrDefault("MATCHES_BASE_URL", "http://localhost:8183");

    public String getTeams() throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder(URI.create(teamsBase + "/teams"))
                .GET().build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String createTeam(Map<String, Object> payload) throws IOException, InterruptedException {
        String body = mapper.writeValueAsString(payload);
        HttpRequest req = HttpRequest.newBuilder(URI.create(teamsBase + "/teams"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String getPresidents() throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder(URI.create(teamsBase + "/presidents"))
                .GET().build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String createPresident(Map<String, Object> payload) throws IOException, InterruptedException {
        String body = mapper.writeValueAsString(payload);
        HttpRequest req = HttpRequest.newBuilder(URI.create(teamsBase + "/presidents"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String getPresidentEmails(String dpi) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder(URI.create(teamsBase + "/presidents/" + dpi + "/emails"))
                .GET().build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String createPresidentEmail(String dpi, Map<String, Object> payload) throws IOException, InterruptedException {
        String body = mapper.writeValueAsString(payload);
        HttpRequest req = HttpRequest.newBuilder(URI.create(teamsBase + "/presidents/" + dpi + "/emails"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String getPlayers() throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder(URI.create(playersBase + "/players"))
                .GET().build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String createPlayer(Map<String, Object> payload) throws IOException, InterruptedException {
        String body = mapper.writeValueAsString(payload);
        HttpRequest req = HttpRequest.newBuilder(URI.create(playersBase + "/players"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String getPlayerEmails(Long playerId) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder(URI.create(playersBase + "/players/" + playerId + "/emails"))
                .GET().build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String createPlayerEmail(Long playerId, Map<String, Object> payload) throws IOException, InterruptedException {
        String body = mapper.writeValueAsString(payload);
        HttpRequest req = HttpRequest.newBuilder(URI.create(playersBase + "/players/" + playerId + "/emails"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String getMatches() throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder(URI.create(matchesBase + "/matches"))
                .GET().build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String createMatch(Map<String, Object> payload) throws IOException, InterruptedException {
        String body = mapper.writeValueAsString(payload);
        HttpRequest req = HttpRequest.newBuilder(URI.create(matchesBase + "/matches"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String getGoals() throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder(URI.create(matchesBase + "/matches/goals"))
                .GET().build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String createGoal(Long matchId, Map<String, Object> payload) throws IOException, InterruptedException {
        String body = mapper.writeValueAsString(payload);
        HttpRequest req = HttpRequest.newBuilder(URI.create(matchesBase + "/matches/" + matchId + "/goals"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();
        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }
}

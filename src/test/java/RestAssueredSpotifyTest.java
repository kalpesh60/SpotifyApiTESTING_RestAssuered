import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssueredSpotifyTest {
    public String token = "";
    public static String userId;

    @BeforeTest
    public void setUp() {
        token = "Bearer BQB2ObSBJASwkwQUc3ouLb7gybDcgQu0CjLBa_ioOzG8buDZuRfR8zo73JQ0ihw4t7lGJ9zCjybBqG2zYWq5a6O9jj2Kofdvz5F4KaVuyWNHW-lFmOYnEDEd2pfNxnsY8913k1_5fwWsWQk9ks8YNBr1l6dvq-orTaQXkOrMftUTJNttk1vs4JfLVsI4HnwKHo6HnYgx6a0sgMWWq1Fm7CmSl5YrHnqZCa8xu0h4wNnJd3J_W_fgIZT_Kkeg_DgGUghoVhijg3b2gzduncwAGPDfCVnGzgUXYRQnLVQD";
    }

    @Test
    public void getUserProfile() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/me");
        userId = response.path("id");
        System.out.println("user id:" + userId);
        response.prettyPrint();
    }

    @Test
    public void getCurrentUserProfile() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/users/" + userId + "/");
        response.prettyPrint();
    }

    @Test
    public void getCurrentUserPlaylist() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");
        response.prettyPrint();
    }

    @Test
    public void addItemToPlaylist() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .queryParams("uris","spotify:track:6cUOiOY5qh2FpIQWIYAd2h")
                .when()
                .post("https://api.spotify.com/v1/playlists/50E8AthTv1yzalDGkj9cEa/tracks");
        response.prettyPrint();
    }

    @Test
    public void addPlaylist() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .body("{\n" +
                        "  \"name\": \"New Playlist5\",\n" +
                        "  \"description\": \"New playlist description\",\n" +
                        "  \"public\": false\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/users/z9rvy6tvyadtjlr6w23p4eoks/playlists");
        response.prettyPrint();
    }

    @Test
    public void changePlaylistName() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .body("{\n" +
                        "  \"name\": \"Kalpesh\",\n" +
                        "  \"description\": \"Updated playlist description\",\n" +
                        "  \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/3M9ykRDHlDfkbuz4Tkelnb");
        response.prettyPrint();
    }

    @Test
    public void removeItemFromPlaylist() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .body("{\n" +
                        "  \"tracks\": [\n" +
                        "    {\n" +
                        "      \"uri\": \"spotify:track:3Wrjm47oTz2sjIgck11l5e\",\n" +
                        "      \"positions\": [\n" +
                        "        0\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")

                .when()
                .delete("https://api.spotify.com/v1/playlists/3M9ykRDHlDfkbuz4Tkelnb/tracks");
        response.prettyPrint();
    }
}

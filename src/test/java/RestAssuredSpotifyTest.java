import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class RestAssuredSpotifyTest {
    public String token = "";
    public static String userId;

    @BeforeTest
    public void setUp() {
        token = "Bearer BQBpVDPFWu7VJ0slsK_ZuxXZGOSzJFBDINhrtGH-6Ywvl6y3mJHuIQfO8EFAN4PSbi6my8n_MfPZ5spqW_R_sXFyGXjrnKmfs3rq1uzjhM67eZbb5kQ_4wYCt5eFynTpGFBsEOlh1v197XdxwIgILbo0ktO8f-_Bg8Yx0LDcmaOLgruPUH6bQe3j2PzIPYmS4c3e24RJExiVj0P3ExbZf6ko55zLHd1M5Tyawf8q6DpEcbCZU3LubdudoV2S6IMbr6N_iCDAERYp7BEaHWUQLX0THTGNEhKpk8fTZVgp";
    }

    //GET APIs
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
    public void getPlaylistCoverImage() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/playlists/1x4pEX3db93gUe7zweLr9u/images");
        response.prettyPrint();
    }

    @Test
    public void getPlaylist() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/playlists/1x4pEX3db93gUe7zweLr9u");
        response.prettyPrint();
    }

    @Test
    public void getListOfUsersPlaylist() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/users/z9rvy6tvyadtjlr6w23p4eoks/playlists");
        response.prettyPrint();
    }

    //POST APIs
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
    public void createPlaylist() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .body("{\n" +
                                           "  \"name\": \"New Playlist7\",\n" +
                                           "  \"description\": \"New playlist description\",\n" +
                                           "  \"public\": false\n" +
                                           "}")
                                   .when()
                                   .post("https://api.spotify.com/v1/users/z9rvy6tvyadtjlr6w23p4eoks/playlists");
        response.prettyPrint();
    }

    //PUT APIs
    @Test
    public void changePlaylistName() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .body("{\n" +
                                           "  \"name\": \"Updated Playlist Name\",\n" +
                                           "  \"description\": \"Updated playlist description\",\n" +
                                           "  \"public\": false\n" +
                                           "}")
                                   .when()
                                   .put("https://api.spotify.com/v1/playlists/3M9ykRDHlDfkbuz4Tkelnb");
        response.prettyPrint();
    }

    @Test
    public void ReorderPlaylistItems() {
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .body("{\n" +
                        "  \"range_start\": 1,\n" +
                        "  \"insert_before\": 3,\n" +
                        "  \"range_length\": 2\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/1x4pEX3db93gUe7zweLr9u/tracks");
        response.prettyPrint();
    }

    //Delete APIs
    @Test
    public void removeItemFromPlaylist() {
        Response response = given().contentType(ContentType.JSON)
                                   .accept(ContentType.JSON)
                                   .header("Authorization", token)
                                   .body("{\n" +
                                           "  \"tracks\": [\n" +
                                           "    {\n" +
                                           "      \"uri\": \"spotify:track:2DB2zVP1LVu6jjyrvqD44z\",\n" +
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
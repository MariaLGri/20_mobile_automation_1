package helpers;

import static io.restassured.RestAssured.given;

public class Browserstack {
    // curl -u "bsuser_bInkOF:fRMdRvRyYQAseb2m1p7A" -X GET "https://api.browserstack.com/app-automate/sessions/acf4f3eb814c3b567a92305743a2d219c80df37d.json"
    // automation_session.video_url

    public static String videoUrl(String sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic("panchmaria_ygdXiH", "66sFgUe7JzauwdSYbky4")
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
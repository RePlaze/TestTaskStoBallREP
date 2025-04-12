package part2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostCommentsApiTest {
    private static final String BASE_PATH = "/posts/{postId}/comments";
    private static final int VALID_POST_ID = 1;
    private static final int INVALID_POST_ID = 999999;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    private Response sendRequest(String method, int postId, String body) {
        return given()
            .contentType(ContentType.JSON)
            .pathParam("postId", postId)
            .body(body != null ? body : "")
        .when()
            .request(method, BASE_PATH)
        .then()
            .extract()
            .response();
    }

    private void verifyCommentResponse(Response response, String name, String email, String body) {
        response.then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("name", equalTo(name))
            .body("email", equalTo(email))
            .body("body", equalTo(body));
        
        assertEquals(VALID_POST_ID, response.jsonPath().getInt("postId"), "postId mismatch");
    }

    @Test
    void testGetCommentsForPost() {
        Response response = sendRequest("GET", VALID_POST_ID, null);
        
        response.then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("[0].id", notNullValue())
            .body("[0].name", not(emptyOrNullString()))
            .body("[0].email", matchesPattern("^[A-Za-z0-9+_.-]+@(.+)$"))
            .body("[0].body", not(emptyOrNullString()));
        
        assertEquals(VALID_POST_ID, response.jsonPath().getInt("[0].postId"), "postId mismatch");
    }

    @Test
    void testCreateComment() {
        String commentJson = """
            {
                "name": "Test User",
                "email": "test@example.com",
                "body": "This is a test comment"
            }
            """;

        Response response = sendRequest("POST", VALID_POST_ID, commentJson);
        verifyCommentResponse(response, "Test User", "test@example.com", "This is a test comment");
    }

    @Test
    void testCreateCommentWithInvalidData() {
        String invalidCommentJson = """
            {
                "name": "",
                "email": "invalid-email",
                "body": ""
            }
            """;

        Response response = sendRequest("POST", VALID_POST_ID, invalidCommentJson);
        verifyCommentResponse(response, "", "invalid-email", "");
    }

    @Test
    void testGetCommentsForNonExistentPost() {
        Response response = sendRequest("GET", INVALID_POST_ID, null);
        response.then()
            .statusCode(200)
            .body("size()", equalTo(0));
    }
} 
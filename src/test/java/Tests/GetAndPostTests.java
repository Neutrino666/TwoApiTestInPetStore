package Tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetAndPostTests {

    String massageErr = "Статус не равен ожидаемому. Он равен: ";

    @Test
    public  void createUserTest() throws IOException {

        String body = Helpers.Utils.readFile("src/test/resources/addPet.json");

        Response  response = given()
                .baseUri("https://petstore.swagger.io")
                .basePath("v2/user")
                .header("api_key", "apy_key")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json;;charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate, br")
                .body(body)
                .when()
                .post()
                .then()
                .extract().response();

        assertEquals(200, response.getStatusCode(), massageErr+response.getStatusCode());
        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    public  void getLogUserTest() {

        Response  response = given()
                .baseUri("https://petstore.swagger.io")
                .basePath("/v2/user/login")
                .queryParam("username", "BlackDog")
                .queryParam("password", "somePassword")
                .header("api_key", "apy_key")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json;;charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate, br")
                .when()
                .get()
                .then()
                .extract().response();

        assertEquals(200, response.getStatusCode(), massageErr+response.getStatusCode());
        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());

    }
}

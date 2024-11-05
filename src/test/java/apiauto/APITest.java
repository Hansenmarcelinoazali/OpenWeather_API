package apiauto;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.module.jsv.JsonSchemaValidator;



public class APITest {
    @Test
    public void GetWeatherCurrently() {

        // Documentation https://openweathermap.org/current

        RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/";

        File jsonWeather = new File("src/test/resources/jsonschemavalidator/validatorcurrentweather.json");

        Response response = given()
                .queryParam("q", "Terban,Sleman,Yogyakarta,ID")
                .queryParam("appid", "ce069d17bee744158b18e562e943aa0c")
                .when()
                .get("weather")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonWeather)) // json schema validator
                .and().body("name", equalTo("Terban"))
                .and().body("sys.country", equalTo("ID"))
                .extract().response();

        System.out.println("Response Body: " + response.getBody().prettyPrint());
    }

    @Test
    public void AirPolution() {

//        documentation = https://openweathermap.org/api/air-pollution

        RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/";

        File jsonschemaPolution = new  File("src/test/resources/jsonschemavalidator/validatorcurrentpolution.json");

        Response response = given()
                .queryParam("lat", "-7.7743")
                .queryParam("lon", "110.3638")
                .queryParam("appid", "ce069d17bee744158b18e562e943aa0c")
                .when()
                .get("air_pollution")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonschemaPolution))
                .extract().response();

        System.out.println(response.getBody().prettyPrint());

    }
}

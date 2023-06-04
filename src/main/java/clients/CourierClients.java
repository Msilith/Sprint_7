package clients;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import pojo.CreateCourierRequest;
import pojo.LoginCourierRequest;

import static io.restassured.RestAssured.given;

public class CourierClients {
    public ValidatableResponse create (CreateCourierRequest createCourierRequest) {
        return given()
                .contentType(ContentType.JSON)
                .body( createCourierRequest)
                .when()
                .post("http://qa-scooter.praktikum-services.ru/api/v1/courier")
                .then();

    }

    public ValidatableResponse login (LoginCourierRequest loginCourierRequest) {
        return given()
                .contentType(ContentType.JSON)
                .body(loginCourierRequest)
                .when()
                .post("http://qa-scooter.praktikum-services.ru/api/v1/courier/login")
                .then();
    }

    public ValidatableResponse delete (int id){
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .delete("http://qa-scooter.praktikum-services.ru/api/v1/courier/{id}", id)
                .then();
    }
}

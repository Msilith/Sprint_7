package sprint_7;

import clients.CourierClients;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import pojo.CreateCourierRequest;
import pojo.LoginCourierRequest;

import static io.restassured.RestAssured.given;


public class CourierTest {
    private CourierClients courierClients = new CourierClients();
    private int id;

    @Test
    public void corierShouldBeCreated() {
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();
        createCourierRequest.setLogin("godHatesUsAll");
        createCourierRequest.setPassword("121314");
        createCourierRequest.setFirstName("Uzulbek");

        CourierClients courierClients = new CourierClients();
        courierClients.create(createCourierRequest)
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));

        LoginCourierRequest loginCourierRequest = new LoginCourierRequest();
        loginCourierRequest.setLogin("godHatesUsAll");
        loginCourierRequest.setPassword("121314");
        id = courierClients.login(loginCourierRequest)
                .statusCode(200)
                .extract().jsonPath().get("id");
    }
    @After
    public void tearDown() {
        courierClients.delete(id)
                .statusCode(200);
    }
}

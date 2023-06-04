package sprint_7;

import clients.CourierClients;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.CreateCourierRequest;
import pojo.LoginCourierRequest;

import static io.restassured.RestAssured.given;


public class CourierTest {
    private CourierClients courierClients = new CourierClients();
    private int id;
    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

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
//to be continued
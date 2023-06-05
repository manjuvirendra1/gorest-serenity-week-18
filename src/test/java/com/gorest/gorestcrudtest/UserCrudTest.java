package com.gorest.gorestcrudtest;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import static com.gorest.utils.TestUtils.getRandomValue;

//@RunWith(SerenityRunner.class)
public class UserCrudTest extends TestBase {

    static String name = "Mahi Thakur" + TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static String gender = "female";
    static String status = "inactive";
    static int userId;

    @Title("This will create a new user")
    @Test
    public void verifyUserCreatedSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Mahi Thakur");
        userPojo.setEmail(getRandomValue() + "@gmail.com");
        userPojo.setGender("female");
        userPojo.setStatus("active");
        SerenityRest. given()
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .post()
                .then().statusCode(201);
    }

    @Title("Verify if the user added successfully to the application")
    @Test
    public void userGetSuccessfully() {
       SerenityRest.given()
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .header("Connection", "keep-alive")
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then().statusCode(200);

    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void userUpdateSuccessfully() {
        name = name + "_updated";
        UserPojo userPojo = new UserPojo();

        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
       SerenityRest. given()
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .header("Content-Type","application/json")
                .header("Connection","keep-alive")
                .when()
                .body(userPojo)
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then().statusCode(200);

    }
    @Title("Delete the user and verify if the user is deleted")
    @Test
    public void userDeleteSuccessfully() {
       SerenityRest. given()
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .header("Connection", "keep-alive")
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then().statusCode(204);

       SerenityRest.given().log().all()
               .pathParam("userId", userId)
               .when()
               .get(EndPoints.GET_SINGLE_USER_BY_ID)
               .then()
               .statusCode(404);


    }
}

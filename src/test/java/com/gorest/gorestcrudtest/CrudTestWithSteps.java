package com.gorest.gorestcrudtest;

import com.gorest.gorestinfo.UsersSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.HashMap;
import static org.hamcrest.Matchers.hasKey;

@RunWith(SerenityRunner.class)
public class CrudTestWithSteps extends TestBase {
    static String name = "Mahi Thakur" + TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static String gender = "female";
    static String status = "inactive";
    static int userId;

    @Steps
    UsersSteps usersSteps;


    @Title("This method will get all users records")
    @Test
    public void test001() {
        usersSteps.getUserIds();
    }

    @Title("This method will create a new users record and verify it by its ID")
    @Test
    public void test002() {
        ValidatableResponse getId = usersSteps.createUser(name, email, gender, status);
        userId = getId.extract().path("id");
        ValidatableResponse response = usersSteps.getUserIds();
        ArrayList<?> userId = response.extract().path("id");
        Assert.assertTrue(userId.contains(userId));
    }

    @Title("This method will update the existing record")
    @Test
    public void test003() {
        status = "inactive";
       usersSteps.updateUser(userId, name, email, gender, status);
        ValidatableResponse response = usersSteps.getUserById(userId).statusCode(200);
        HashMap<String, ?> userRecord = response.extract().path("");
        Assert.assertThat(userRecord, hasKey("inactive"));
    }

    @Title("This method will delete the existing record")
    @Test
    public void test004() {
       usersSteps.deleteUser(userId).statusCode(204);
       usersSteps.getUserById(userId).statusCode(404);
    }
}

package com.gorest.gorestcrudtest;

import com.gorest.gorestinfo.UsersSteps;
import com.gorest.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

//@Concurrent(threads = "4x")
//@UseTestDataFrom("src/test/java/resources/testdata/studentinfo.csv")
//@RunWith(SerenityParameterizedRunner.class)
public class CreateUserDataDrivenTest extends TestBase {

    private String name;
    private String email;
    private String gender;
    private String status;


    @Steps
    UsersSteps usersSteps;

    @Title("Data driven test for adding multiple students to the application")
    @Test
    public void createMultipleStudents(){

        UsersSteps.createUser(name, email, gender, status).statusCode(201);
    }
}

package com.spartan.DDF_tests.tests;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;



public class MackarooApi {


    @Test
    public void verifyApiConnection(){


        given().accept(ContentType.JSON)
                .and()
                .header("X-API-Key", "30c1ff30")
                .when()
                .get()
                .then()
                .log().body()
                .statusCode(200)
                .and()
                .contentType("application/json");


    }




    @Test
    public void indexElements(){
        Response response = given().accept(ContentType.JSON)
                .header("X-API-Key", "30c1ff30")
                .when()
                .get("https://my.api.mockaroo.com/sp_dummy.json");

        JsonPath jsonPath = response.jsonPath();

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "application/json");


        Gson gson = new Gson();

        Spartan spartan = new Spartan();

        spartan = gson.fromJson(jsonPath.get("[0]").toString(), Spartan.class);

        System.out.println(spartan.toString());


    }


    @Test
    public void postToSpartansWithMockarooApi(){


        Response response = given().accept(ContentType.JSON)
                .header("X-API-Key", "30c1ff30")
                .when()
                .get("https://my.api.mockaroo.com/sp_dummy.json");

        JsonPath jsonPath = response.jsonPath();
        Gson gson = new Gson();


        for (int i = 0; i < 10; i++) {
            Spartan spartan = new Spartan();
            spartan = gson.fromJson(jsonPath.get("[" + i + "]").toString(), Spartan.class);
            given().accept(ContentType.JSON)
                    .and()
                    .contentType(ContentType.JSON)
                    .body(spartan)
                    .when()
                    .post("http://100.26.102.120:8000" + "/api/spartans")
                    .then()
                    .log().body()
                    .and()
                    .statusCode(201)
                    .and()
                    .contentType("application/json")
                    .body("success", equalTo("A Spartan is Born!"),
                            "data.name", equalTo(spartan.getName()),
                            "data.gender", equalTo(spartan.getGender()),
                            "data.phone".toString(), equalTo(spartan.getPhone()));
        }

    }








}

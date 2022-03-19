package com.spartan.DDF_tests.tests;
import com.google.gson.Gson;
import com.spartan.DDF_tests.utilities.ConfigurationReader;
import com.spartan.DDF_tests.utilities.ExcelUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
public class SpartansApi {


    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.getKey("spartan_api_url");
    }



    @DataProvider(name = "dp")
    public Object [][] userData(){

        ExcelUtil dummyData = new ExcelUtil("src/test/resources/dummy_spartans.xlsx", "data");

        String [][] dataArray = dummyData.getDataArrayWithoutFirstRow();

        return dataArray;

    }




    @Test
    public void testVerifyApiConnected(){
        given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(200)
                .and().contentType("application/json");

    }


    @Test(dataProvider = "dp")
    public void testDP(String name, String gender, String phone){

        Map<Object, Object> map = new HashMap<>();
        BigDecimal bigDecimal = new BigDecimal(phone);
        map.put("name", name);
        map.put("gender", gender);
        map.put("phone", bigDecimal.longValue());

        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(map).log().all()
                .when().post("/api/spartans")
                .then()
                .statusCode(201)
                .and()
                .contentType("application/json")
                .body("success", equalTo("A Spartan is Born!"),
                        "data.name", equalTo(map.get("name")),
                        "data.gender", equalTo(map.get("gender")),
                        "data.phone", equalTo(map.get("phone")));



//        assertEquals(response.statusCode(), 201);
//        assertEquals(response.contentType(), "application/json");
//
//
//        assertEquals(response.path("data.phone"), map.get("phone"));
//        assertEquals(response.path("data.name"), map.get("name"));
//        assertEquals(response.path("data.gender"), map.get("gender"));



    }

}

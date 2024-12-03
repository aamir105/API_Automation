package Pet_Store;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static junit.framework.Assert.assertEquals;

public class Pet_Store_Automation {
    @Test
    public void user(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 3077,\n" +
                        "  \"username\": \"Aamir\",\n" +
                        "  \"firstName\": \"string\",\n" +
                        "  \"lastName\": \"string\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"Aamir\",\n" +
                        "  \"phone\": \"string\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/user");
//                .then() if we want validation
        response.prettyPrint();
        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(200));
    }

    @Test
    public void login(){
        Response logRes = given()
                .header("accept","application/json")
                .queryParam("username","aamir123")
                .queryParam("password","123")
                .when()
                .get("https://petstore.swagger.io/v2/user/login");
        logRes.prettyPrint();
        logRes.then().statusCode(200);
    }

    @Test
    public void logout() {
        Response res = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get("https://petstore.swagger.io/v2/user/logout");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }

    @Test
    public void list_of_user(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": \"1\",\n" +
                        "  \"username\": \"aamir123\",\n" +
                        "  \"firstName\": \"Md\",\n" +
                        "  \"lastName\": \"aamir\",\n" +
                        "  \"email\": \"aamir@gmail.com\",\n" +
                        "  \"password\": \"123\",\n" +
                        "  \"phone\": \"123456789\",\n" +
                        "  \"userStatus\": \"0\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 2,\n" +
                        "    \"username\": \"sahil123\",\n" +
                        "    \"firstName\": \"sahil\",\n" +
                        "    \"lastName\": \"sontakke\",\n" +
                        "    \"email\": \"sahil@gmail.com\",\n" +
                        "    \"password\": \"789\",\n" +
                        "    \"phone\": \"654987123\",\n" +
                        "    \"userStatus\": \"1\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 2,\n" +
                        "    \"username\": \"abhi123\",\n" +
                        "    \"firstName\": \"abhishek\",\n" +
                        "    \"lastName\": \"sharma\",\n" +
                        "    \"email\": \"abhi@gmail.com\",\n" +
                        "    \"password\": \"456\",\n" +
                        "    \"phone\": \"987654321\",\n" +
                        "    \"userStatus\": \"2\"\n" +
                        "  }\n" +
                        "]")
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithList");
//                .then() if we want validation
        response.prettyPrint();
        response.then().statusCode(200);
//        Assert.assertEquals(response.statusCode(200));
    }

    @Test
    public void delete_user() {
        Response res = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/user/Aamir");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }

    @Test
    public void update_existing_pet(){
        String username = "aamir123";
        Response res = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"cat\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/pet");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);
//        Assert.assertEquals(name,"Aamir");
    }

    @Test
    public void get_user_by_name() {
        String username = "aamir123"; // Replace with the actual username
        Response res = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .pathParam("username", username) // Use path parameter
                .when()
                .get("https://petstore.swagger.io/v2/user/{username}");

        res.prettyPrint();
        res.then().assertThat().statusCode(200);

        // Optionally, you can add assertions to verify the response
//        String actualUsername = res.jsonPath().getString("username");
//        assertEquals(username, actualUsername);
    }

    @Test
    public void place_an_order_for_pet(){
        Response petres = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 30,\n" +
                        "  \"petId\": 101,\n" +
                        "  \"quantity\": 5,\n" +
                        "  \"shipDate\": \"2024-09-20T05:32:31.168Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/store/order");
//                .then() if we want validation
        petres.prettyPrint();
        petres.then().statusCode(200);
    }

    @Test
    public void return_pet_inventory(){
        Response resp = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/store/inventory");
        resp.prettyPrint();
        resp.then().assertThat().statusCode(200);
    }

    @Test
    public void delete_order_by_id(){
        Response res = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/store/order/101");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }

    @Test
    public void find_purchase_order_by_id(){
        Response res = given()
                .header("accept","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/store/order/1");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);
//        String Id = res.path("1");
//        Assert.assertEquals(Id,"1");
    }

    @Test
    public void add_new_pet_to_the_store(){
        Response response = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 10,\n" +
                        "    \"name\": \"Oggy\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 101,\n" +
                        "      \"name\": \"DodTagged\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/pet");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void find_pet_by_id() {
        Response response = given()
                .header("accept", "application/json")
                .when()
                .get("https://petstore.swagger.io/v2/pet/101");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
//        String Id = response.path("10");
//        Assert.assertEquals(Id, "10");
    }

    @Test
    public void Delete_pet_by_status(){
        Response resp = given()
                .header("accept","application/json")
                .queryParam("status","available")
                .when()
                .delete("https://petstore.swagger.io/v2/pet/findByStatus");
        resp.prettyPrint();
        resp.then().statusCode(200);
    }

}


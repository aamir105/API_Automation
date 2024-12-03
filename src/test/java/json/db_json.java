package json;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class db_json {

    @Test
    public void addData(){
        Response res = given()
                .header("Accept","*/*")
                .header("Content-Type","application/json")
                .body(" {\n" +
                        "      \"id\": \"13\",\n" +
                        "      \"name\": \"Chelsey Dietrich\",\n" + "      \"username\": \"Kamren\",\n" +
                        "      \"email\": \"Lucio_Hettinger@annie.ca\",\n" + "      \"address\": {\n" +
                        "        \"street\": \"Skiles Walks\",\n" + "        \"suite\": \"Suite 351\",\n" +
                        "        \"city\": \"Roscoeview\",\n" + "        \"zipcode\": \"33263\",\n" +
                        "        \"geo\": {\n" + "          \"lat\": \"-31.8129\",\n" +
                        "          \"lng\": \"62.5342\"\n" + "        }\n" +
                        "      },\n" +
                        "      \"phone\": \"(254)954-1289\",\n" + "      \"website\": \"demarco.info\",\n" +
                        "      \"company\": {\n" + "        \"name\": \"Keebler LLC\",\n" +
                        "        \"catchPhrase\": \"User-centric fault-tolerant solution\",\n" +
                        "        \"bs\": \"revolutionize end-to-end systems\"\n" +
                        "      }\n" +
                        "    }")
                .when()
                .post("http://localhost:3000/Users");
        res.prettyPrint();

    }


    @Test
    public void retrieveData(){
        Response res = given()
                .header("Accept","*/*")
                .header("Content-Type","application/json")

                .when()
                .get("http://localhost:3000/Users/1");
        res.prettyPrint();

    }

    @Test
    public void changeData(){
        Response res = given()
                .header("Accept","*/*")
                .header("Content-Type","application/json")
                .body(" {\n" +
                        "      \"id\": \"13\",\n" +
                        "      \"name\": \"Ak47 Dietrich\",\n" + "      \"username\": \"Kamren\",\n" +
                        "      \"email\": \"Lucio_Hettinger@annie.ca\",\n" + "      \"address\": {\n" +
                        "        \"street\": \"Skiles Walks\",\n" + "        \"suite\": \"Suite 351\",\n" +
                        "        \"city\": \"Roscoeview\",\n" + "        \"zipcode\": \"33263\",\n" +
                        "        \"geo\": {\n" + "          \"lat\": \"-31.8129\",\n" +
                        "          \"lng\": \"62.5342\"\n" + "        }\n" +
                        "      },\n" +
                        "      \"phone\": \"(254)954-1289\",\n" + "      \"website\": \"demarco.info\",\n" +
                        "      \"company\": {\n" + "        \"name\": \"Keebler LLC\",\n" +
                        "        \"catchPhrase\": \"User-centric fault-tolerant solution\",\n" +
                        "        \"bs\": \"revolutionize end-to-end systems\"\n" +
                        "      }\n" +
                        "    }")
                .when()
                .put("http://localhost:3000/Users/13");
        res.prettyPrint();

    }

    @Test
    public void modifyData(){
        Response res = given()
                .header("Accept","*/*")
                .header("Content-Type","application/json")
                .body(" {\n" +
                        "      \"email\": \"Shreya@annie.ca\",\n"+
                        "    }")
                .when()
                .patch("http://localhost:3000/Users/13");
        res.prettyPrint();

    }


    @Test
    public void removeData(){
        Response res = given()
                .header("Accept","*/*")
                .header("Content-Type","application/json")

                .when()
                .delete("http://localhost:3000/Users/5");
        res.prettyPrint();

    }


}

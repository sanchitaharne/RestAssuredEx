package models;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ChallangeEx {
    @Test
    public void createProduct(){
        String endpoint = "http://localhost:80/api_testing/product/create.php";
        Product product = new Product(
               "Sweatband",
                "Your healthy friend",
                5,
                3
        );
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateProduct(){
        String endpoint = "http://localhost:80/api_testing/product/update.php";
        Product product = new Product(
                1003,
                "Sweatband",
                "Your healthy friend",
                6,
                3
        );
       var response = given().body(product).when().put(endpoint).then();
       response.log().body();
    }

    @Test
    public void getProduct(){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";

        given().queryParam("id", 1003).
                when().
                get(endpoint).
                then().
                log().
                headers().
                header("Content-Type", equalTo("application/json"));

    }

    @Test
    public void deleteProduct(){
        String endpoint = "http://localhost:80/api_testing/product/delete.php";
        String body = "{\"id\":1003}";
        given().body(body).when().delete(endpoint).then().log().headers();
    }
}

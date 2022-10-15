package models;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;

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
                3,
                "Active Wear - Unisex"
        );
       var response = given().body(product).when().put(endpoint).then();
       response.log().body();
    }

    @Test
    public void getProduct(){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";

        given().queryParam("id", 1004).
                when().
                get(endpoint).
                then().
                log().
                body().
                header("Content-Type", equalTo("application/json"));

    }

    @Test
    public void deleteProduct(){
        String endpoint = "http://localhost:80/api_testing/product/delete.php";
        String body = "{\"id\":1003}";
        given().body(body).when().delete(endpoint).then().log().headers();
    }

    @Test
    public void getDeserializedProduct(){

        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        Product expectedProduct = new Product(
            1004,
            "Sweatband",
            "Your healthy friend",
            5.00,
            3,
            "Active Wear - Unisex"
        );
        Product actualProduct = given().queryParam("id", "1004").when().get(endpoint).as(Product.class);
    //    assertThat(actualProduct, samePropertyValuesAs(expectedProduct) );
    }

    @Test
    public void getMultiVitaminProduct(){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        given().
                queryParam("id", 18).
                when().
                get(endpoint).
                then().assertThat().statusCode(200).
                header("Content-Type", "application/json").
                body("id", notNullValue()).
                body("name", equalTo("Multi-Vitamin (90 capsules)")).
                body("description", equalTo("A daily dose of our Multi-Vitamins fulfills a day’s nutritional needs for over 12 vitamins and minerals.")).
                body("price", equalTo("10.00")).
                body("category_id", equalTo("4")).
                body("category_name", equalTo("Supplements"));
    }
}

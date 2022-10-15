import models.Product;
import org.junit.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;
public class ApiTests {
/* */
    @Test
    public void getResources(){

        String endpoint = "http://localhost:80/api_testing/category/read.php";
        var response = given().when().get(endpoint).then();
        response.log().body();
    }

    @Test
    public void getResource(){

        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        var response = given().queryParam("id", 1).when().get(endpoint).then();
        response.log().body();
    }

   @Test
    public void createResource(){

        String endpoint = "http://localhost:80/api_testing/product/create.php";
        String body= "{" +
                     "\"name\": \"water bottle\", " +
                     "\"description\": \"This is a blue bottle\", " +
                     "\"price\": 12, " +
                     "\"category_id\":3" +
                     "}";

        var response = given().body(body).when().post(endpoint).then();
        response.log().body();
   }

   @Test
    public void updateResourse(){

        String endpoint = "http://localhost:80/api_testing/product/update.php";
        String body = "{" +
                "\"id\" = 1001, " +
                "\"name\": \"water bottle\", " +
                "\"price\": 99.00, " +
                "\"category_id\":3" +
                "}";
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
   }

   @Test
    public void deleteResource(){
        String endpoint= "http://localhost:80/api_testing/product/delete.php";
        String body = "{\"id\":1001}";
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();

   }
   @Test
    public void createSerializedProduct(){

       String endpoint = "http://localhost:80/api_testing/product/create.php";
       Product product = new Product(
         "pencil",
         "Used for writing texts",
         2,
         1
       );

       var response = given().body(product).when().post(endpoint).then();
       response.log().body();
   }
}

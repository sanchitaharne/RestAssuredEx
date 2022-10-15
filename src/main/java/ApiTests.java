import models.Product;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;

public class ApiTests {
/* */
    @Test
    public void getResources(){

        String endpoint = "http://localhost:80/api_testing/category/read.php";
        given().
        when().
          get(endpoint).
        then().
          assertThat().
              statusCode(200).
              log().
               body().
              body("records.size()", greaterThan(0)).
              body("records.id", everyItem(notNullValue())).
              body("records.name", notNullValue()).
              body("records.description", notNullValue()).
              body("records.id[0]", equalTo("1"));

    }

    @Test
    public void getResource(){

        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        given().
           queryParam("id", 1).
              when().
              get(endpoint).
              then().assertThat().
                body("id", equalTo("1")).
                body("name", equalTo("Bamboo Thermal Ski Coat")).
                body("description", equalTo("You’ll be the most environmentally conscious skier on the slopes – and the most stylish – wearing our fitted bamboo thermal ski coat, made from organic bamboo with recycled plastic down filling.")).
                body("price", equalTo("99.00"));
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

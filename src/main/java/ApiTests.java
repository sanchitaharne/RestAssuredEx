import org.junit.Test;
import static io.restassured.RestAssured.given;
public class ApiTests {
/* */
    @Test
    public void getResources(){

        String endpoint = "http://localhost/api_testing/category/read.php";
        var response = given().when().get(endpoint).then();
        response.log().body();
    }

    @Test
    public void getResource(){
        String endpoint = "http://localhost/api_testing/category/read_one.php";
        var response =
                given().
                        queryParam("id", 1)
               .when().
                        get(endpoint)
               .then();
        response.log().body();
    }
}

package com.test.automation.restassured ;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;




public class TC_0001_GetRequest {
	
	@Test
	public void tc001getMethod() {
		
		
		given()
        .header("Content-Type", "application/json")
        .when()
        .get("https://reqres.in/api/users/2")
        .then()
        .log().all() // Prints the complete response logs to the console
        .statusCode(200).// Validates that the response status is 200 OK
		body("data.id" , equalTo(2)); 
   }
	

	@Test
	public void tc0002postMethod() {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "John Doe");
        requestBody.put("job", "QA Engineer");

        given()
            .baseUri("https://reqres.in")
            .basePath("/api/users")
            .header("Content-Type", "application/json") // Tells the server we are sending JSON
            .body(requestBody)                          // Attaches the payload to the request
        .when()
            .post()                                     // Triggers the HTTP POST action
        .then()
            .statusCode(201)                            // Verifies the status code is 201 Created
            .body("name", equalTo("John Doe"))         // Optional: Asserts the response contains the sent name
            .body("id", notNullValue())                 // Optional: Asserts the server generated a user ID
            .log().all()                               // Prints response details to the console
        	.statusCode(201) ;
		
	}
	
	@Test
	public void tc0003putRequest() {
	
	    Map<String, Object> requestBody = new HashMap<>();
	    requestBody.put("name", "John Doe");
	    requestBody.put("job", "QA Engineer");

	    given()
	        .baseUri("https://reqres.in")
	        .basePath("/api/users")
	        .header("Content-Type", "application/json") // Tells the server we are sending JSON
	        .body(requestBody)                          // Attaches the payload to the request
	    .when()
	        .put("/2")                                  // FIX 1 & 3: Specifies the user ID (e.g., user 2) to update
	    .then()
	        .statusCode(200)                            // FIX 2: ReqRes returns 200 OK for successful PUT updates
	        .log().all();                               // Prints response details to the consol
	        
			
	}
	
    @Test
    public void tc0004patchRequest() {
        // 1. Define the partial data you want to update
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("job", "Senior QA Automation Engineer"); // Only updating the job title

        given()
            .baseUri("https://reqres.in")
            .basePath("/api/users")
            .header("Content-Type", "application/json") 
            .body(requestBody)                          
        .when()
            .patch("/2")                                 // Triggers PATCH on user ID 2
        .then()
            .statusCode(200)                            // ReqRes returns 200 OK for a successful PATCH
            .body("job", equalTo("Senior QA Automation Engineer")) // Validates the change took place
            .body("updatedAt", notNullValue())          // Validates the server returned a timestamp
            .log().all();                               // Prints the response to the console
    }
    
    
    @Test
    public void tc0005deleteRequest() {
        given()
            .baseUri("https://reqres.in")
            .basePath("/api/users")
        .when()
            .delete("/2")                              // Triggers the HTTP DELETE action on user ID 2
        .then()
            .statusCode(204)                           // ReqRes returns 204 No Content for successful deletions
            .log().all();                              // Prints console logs (will show headers but empty body)
    }

}

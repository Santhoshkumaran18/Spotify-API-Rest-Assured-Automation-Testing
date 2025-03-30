package com.spotify.OAuth2.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PlaylistTests {
	RequestSpecification requestspecification;
	ResponseSpecification responseSpecification;
	String access_token = "BQDvfIUI0gkizUglXTszersJ-n3844AUhwyGmHw1qekbH8NT6Q_Xv09vDF95csMn79CNyx9NKfuQDfTp54G-ebuubzfhegfAuoPbA9Qenp4YT3BQwokicmH8GSsjvXONhi7sgzYIKgzA5waIXh8CIvVp-Ixp-Gbc0d0KGnB7potX5boc2jtehgqd0FFr78yGAXEHPTovRpuARTzJNfpl-TLHMqHWlVSPhfvmut6wBnvJJmyR76HZahzwAvgvfniRcxLEiByXPEiGGepaYjVrrmIA-cwnWj6RBQfsFsqjkPDagW7v";

	@BeforeClass
	public void beforeClass() {
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().setBaseUri("https://api.spotify.com")
				.setBasePath("/v1").addHeader("Authorization", "Bearer " + access_token)
				.setContentType(ContentType.JSON).log(LogDetail.ALL);
		requestspecification = requestSpecBuilder.build();

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().log(LogDetail.ALL);

		responseSpecification = responseSpecBuilder.build();
	}

	@Test
	public void shouldbeAbleTocreatePlaylist() {
		String payload = "{\r\n" + "    \"name\": \"AR Rahman and Anirudh playlist updated\",\r\n"
				+ "    \"description\": \"New playlist description\",\r\n" + "    \"public\": false\r\n" + "}";

		Response res = given().spec(requestspecification).body(payload).when()
				.post("/users/31oc6ejhybrxk37sq6e2xj5btjg4/playlists").then().spec(responseSpecification).assertThat()
				.statusCode(201).body("name", equalTo("AR Rahman and Anirudh playlist updated"), "description",
						equalTo("New playlist description"))
				.extract().response();

		String id = res.path("id");
		System.out.println(id);

	}

	@Test
	public void shouldbeabletoGetAPlaylist() {

		given().spec(requestspecification).when().get("/playlists/4s6DQK4WEWrKeQWL0aV6Sq").then()
				.spec(responseSpecification).assertThat().statusCode(200).contentType(ContentType.JSON);

	}

	@Test
	public void shouldBeAbletoupdatethePlaylist() {

		String payload = "{\r\n" + "    \"name\": \"AR Rahman and Anirudh playlist updated\",\r\n"
				+ "    \"description\": \"New playlist description\",\r\n" + "    \"public\": false\r\n" + "}";

		given().spec(requestspecification).body(payload).when().put("/playlists/4s6DQK4WEWrKeQWL0aV6Sq").then()
				.spec(responseSpecification).assertThat().statusCode(200);

	}

	@Test
	public void shouldnotbeAbleTocreatePlaylist() {
		String payload = "{\r\n" + "    \"name\": \"\",\r\n" + "    \"description\": \"New playlist description\",\r\n"
				+ "    \"public\": false\r\n" + "}";

		Response res = given().spec(requestspecification).body(payload).when()
				.post("/users/31oc6ejhybrxk37sq6e2xj5btjg4/playlists").then().spec(responseSpecification).assertThat()
				.statusCode(400)
				.body("error.status", equalTo(400), "error.message", equalTo("Missing required field: name")).extract()
				.response();

	}

	@Test
	public void shouldnotbeAbleTocreatePlaylistwithexpiredtoken() {
		String payload = "{\r\n" + "    \"name\": \"\",\r\n" + "    \"description\": \"New playlist description\",\r\n"
				+ "    \"public\": false\r\n" + "}";

		Response res = given().baseUri("https://api.spotify.com").basePath("/v1")
				.header("Authorization", "Bearer " + 122323434 ).contentType(ContentType.JSON).log().all()

				.body(payload).when().post("/users/31oc6ejhybrxk37sq6e2xj5btjg4/playlists").then()
				.spec(responseSpecification).assertThat().statusCode(401)
				.body("error.status", equalTo(401), "error.message", equalTo("Invalid access token")).extract()
				.response();

	}

}

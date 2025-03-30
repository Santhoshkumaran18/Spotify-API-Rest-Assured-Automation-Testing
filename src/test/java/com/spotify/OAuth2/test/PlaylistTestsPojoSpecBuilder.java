package com.spotify.OAuth2.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spotify.OAuth2.api.SpecBuilder;
import com.spotify.OAuth2.pojo.Error;
import com.spotify.OAuth2.pojo.InnerError;
import com.spotify.OAuth2.pojo.Playlist;

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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTestsPojoSpecBuilder extends SpecBuilder {


	@Test
	public static void shouldbeAbleTocreatePlaylist() {
		Playlist requestPlaylist = new Playlist().setName("New Playlist").setDescription("New playlist Descritption")
				.setPublic(false);

		Playlist responseplaylist = given(getrequestSpec()).body(requestPlaylist).when()
				.post("/users/31oc6ejhybrxk37sq6e2xj5btjg4/playlists").then().spec(getResponseSpec()).assertThat()
				.statusCode(201).extract().response().as(Playlist.class);

		assertThat(responseplaylist.getName(), equalTo(requestPlaylist.getName()));
		assertThat(responseplaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
		assertThat(responseplaylist.getPublic(), equalTo(requestPlaylist.getPublic()));

	}

	@Test
	public void shouldbeabletoGetAPlaylist() {

		given().spec(getrequestSpec()).when().get("/playlists/4s6DQK4WEWrKeQWL0aV6Sq").then()
				.spec(getResponseSpec()).assertThat().statusCode(200).contentType(ContentType.JSON);

	}

	@Test
	public void shouldBeAbletoupdatethePlaylist() {
		Playlist requestPlaylist = new Playlist().setName("AR Rahman and Anirudh playlist updated")
				.setDescription("New playlist Descritption").setPublic(false);

		given().spec(getrequestSpec()).body(requestPlaylist).when().put("/playlists/4s6DQK4WEWrKeQWL0aV6Sq").then()
				.assertThat().statusCode(200);

	}

	@Test
	public void shouldnotbeAbleTocreatePlaylist() {
		Playlist requestPlaylist = new Playlist().setName("").setDescription("New playlist Descritption")
				.setPublic(false);

		Error error = given().spec(getrequestSpec()).body(requestPlaylist)
				.post("/users/31oc6ejhybrxk37sq6e2xj5btjg4/playlists").then().spec(getResponseSpec()).assertThat()
				.statusCode(400).extract().response().as(Error.class);

		assertThat(error.getError().getStatus(), equalTo(400));
		assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));

	}

	@Test
	public void shouldnotbeAbleTocreatePlaylistwithexpiredtoken() {
		Playlist requestPlaylist = new Playlist().setName("").setDescription("New playlist Descritption")
				.setPublic(false);

		Error error = given().baseUri("https://api.spotify.com").basePath("/v1")
				.header("Authorization", "Bearer " + 122323434).contentType(ContentType.JSON).log().all()

				.body(requestPlaylist).when().post("/users/31oc6ejhybrxk37sq6e2xj5btjg4/playlists").then()
				.spec(getResponseSpec()).assertThat().statusCode(401).extract().response().as(Error.class);

		assertThat(error.getError().getStatus(), equalTo(401));
		assertThat(error.getError().getMessage(), equalTo("Invalid access token"));

	}

}

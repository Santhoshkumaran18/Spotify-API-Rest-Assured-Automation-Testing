package com.spotify.OAuth2.api.applicationApi;

import static io.restassured.RestAssured.given;

import com.spotify.OAuth2.api.SpecBuilder;
import com.spotify.OAuth2.pojo.Playlist;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PlaylistAPIGeneric extends SpecBuilder {
	public static Response post(Playlist requestPlaylist){
		return given().spec(getrequestSpec()).body(requestPlaylist)
				.header("Authorization", "Bearer " + SpecBuilder.access_token).when()
				.post("/users/31oc6ejhybrxk37sq6e2xj5btjg4/playlists")
				.then().spec(getResponseSpec()).extract().response();
	}
	
	public static Response post(Playlist requestPlaylist,String token){
		return given().spec(getrequestSpec()).body(requestPlaylist).header("Authorization", "Bearer " + token)
				.when()
				.post("/users/31oc6ejhybrxk37sq6e2xj5btjg4/playlists")
				.then().spec(getResponseSpec()).extract().response();
	}
	
	public static Response get(String playlistId) {
		return given().spec(getrequestSpec())
				.header("Authorization", "Bearer " + SpecBuilder.access_token).when().get("/playlists/" + playlistId).then()
		.spec(getResponseSpec()).contentType(ContentType.JSON).extract().response();
	}
	
	public static Response update(Playlist requestPlaylist,String playlistId) {
		return given().spec(getrequestSpec()).body(requestPlaylist)
				.header("Authorization", "Bearer " + SpecBuilder.access_token).when().put("/playlists/" + playlistId ).then()
		.assertThat().statusCode(200).extract().response();
	}
}

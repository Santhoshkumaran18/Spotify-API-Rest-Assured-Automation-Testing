package com.spotify.OAuth2.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import com.spotify.OAuth2.api.SpecBuilder;
import com.spotify.OAuth2.pojo.Playlist;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestResource extends SpecBuilder {
	public static Response post(String path,String token,Object requestPlaylist){
		return given().spec(getrequestSpec()).body(requestPlaylist)
				.auth().oauth2(token)
				.when()
				.post(path)
				.then().spec(getResponseSpec()).extract().response();
	}
	
	public static Response post(String path,Object requestPlaylist,String token){
		return given().spec(getrequestSpec()).body(requestPlaylist)
				.auth().oauth2(token)
				.when()
				.post(path)
				.then().spec(getResponseSpec()).extract().response();
	}
	
	public static Response get(String path,String token) {
		return given().spec(getrequestSpec())
				.auth().oauth2(token)
				.when().get(path).then()
		.spec(getResponseSpec()).extract().response();
	}
	
	public static Response update(String path,String token,Object requestPlaylist) {
		return given().spec(getrequestSpec()).body(requestPlaylist)
				.auth().oauth2(token).when().put(path).then()
		.assertThat().statusCode(200).extract().response();
	}
	
	public static Response postAccount(HashMap<String,String> formparamerters) {
		return given(getAccountrequestspec())
		.formParams(formparamerters).when().post(Route.API + Route.TOKEN)
		.then().spec(getResponseSpec()).extract().response();
	}
}

package com.spotify.OAuth2.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spotify.OAuth2.api.applicationApi.PlaylistAPIGeneric;
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

public class PlaylistTestsReuasableMethod extends PlaylistAPIGeneric{

	@Test
	public void shouldbeAbleTocreatePlaylist() {
		Playlist requestPlaylist = new Playlist().setName("New Playlist").setDescription("New playlist Descritption")
				.setPublic(false);
		Response res=PlaylistAPIGeneric.post(requestPlaylist);
		assertThat(res.statusCode(),equalTo(201));
		
		Playlist responseplaylist=res.as(Playlist.class);

		assertThat(responseplaylist.getName(), equalTo(requestPlaylist.getName()));
		assertThat(responseplaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
		assertThat(responseplaylist.getPublic(), equalTo(requestPlaylist.getPublic()));

	}

	@Test
	public void shouldbeabletoGetAPlaylist() {
		Response res=PlaylistAPIGeneric.get("4s6DQK4WEWrKeQWL0aV6Sq");
		assertThat(res.getStatusCode(),equalTo(200));

	}

	@Test
	public void shouldBeAbletoupdatethePlaylist() {
		Playlist requestPlaylist = new Playlist().setName("AR Rahman and Anirudh playlist updated")
				.setDescription("New playlist Descritption").setPublic(false);
		Response res=PlaylistAPIGeneric.update(requestPlaylist, "4s6DQK4WEWrKeQWL0aV6Sq");
		assertThat(res.statusCode(),equalTo(200));
	

	}

	@Test
	public void shouldnotbeAbleTocreatePlaylist() {
		Playlist requestPlaylist = new Playlist().setName("").setDescription("New playlist Descritption")
				.setPublic(false);
		Response res=PlaylistAPIGeneric.post(requestPlaylist);
		Error error=res.as(Error.class);

		assertThat(error.getError().getStatus(), equalTo(400));
		assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));

	}

	@Test
	public void shouldnotbeAbleTocreatePlaylistwithexpiredtoken() {
		String invalidToken="1233434";
		Playlist requestPlaylist = new Playlist().setName("").setDescription("New playlist Descritption")
				.setPublic(false);
		Response res=PlaylistAPIGeneric.post(requestPlaylist, invalidToken);
		Error error=res.as(Error.class);
		
		assertThat(error.getError().getStatus(), equalTo(401));
		assertThat(error.getError().getMessage(), equalTo("Invalid access token"));

	}

}

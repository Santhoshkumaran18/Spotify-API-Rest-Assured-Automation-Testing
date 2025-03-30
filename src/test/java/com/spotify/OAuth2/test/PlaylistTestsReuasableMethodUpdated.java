package com.spotify.OAuth2.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spotify.OAuth2.api.applicationApi.PlaylistAPI;
import com.spotify.OAuth2.pojo.Error;
import com.spotify.OAuth2.pojo.InnerError;
import com.spotify.OAuth2.pojo.Playlist;
import com.spotify.OAuth2.utils.DataLoader;

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

public class PlaylistTestsReuasableMethodUpdated extends PlaylistAPI {

	@Test
	public Playlist playlistBuilder(String name, String Description, boolean _public) {
		Playlist requestPlaylist = new Playlist().setName("New Playlist").setDescription("New playlist Descritption")
				.setPublic(false);
		return requestPlaylist;
	}

	@Test
	public void assertPlaylist(Playlist responseplaylist, Playlist requestPlaylist) {
		assertThat(responseplaylist.getName(), equalTo(requestPlaylist.getName()));
		assertThat(responseplaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
		assertThat(responseplaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
	}
	
	@Test
	public void assertStatuscode(int actualStatusCode,int expecteStatusCode) {
		assertThat(expecteStatusCode, equalTo(actualStatusCode));
	}
	
	@Test
	public void assertError(Error error,String Errormessage,int expectedStatusCode) {
		assertThat(error.getError().getStatus(), equalTo(expectedStatusCode));
		assertThat(error.getError().getMessage(), equalTo(Errormessage));
	}

	@Test
	public void shouldbeAbleTocreatePlaylist() {
		Playlist requestPlaylist = playlistBuilder("New Playlist", "New playlist Descritption", false);
		Response res = PlaylistAPI.post(requestPlaylist);		
		assertStatuscode(res.statusCode(), 201);
		assertPlaylist(res.as(Playlist.class), requestPlaylist);

	}

	@Test
	public void shouldbeabletoGetAPlaylist() {
		
		Response res = PlaylistAPI.get(DataLoader.getInstance().getPlaylistid());
		assertStatuscode(res.statusCode(), 200);

	}

	@Test
	public void shouldBeAbletoupdatethePlaylist() {
		Playlist requestPlaylist=playlistBuilder("AR Rahman and Anirudh playlist updated", "New playlist Descritption", false);
		Response res = PlaylistAPI.update(requestPlaylist, DataLoader.getInstance().getUpdatePlaylistID());
		assertStatuscode(res.statusCode(), 200);

	}

	@Test
	public void shouldnotbeAbleTocreatePlaylist() {
		Playlist requestPlaylist=playlistBuilder("", "New playlist Descritption", false);
	
		Response res = PlaylistAPI.post(requestPlaylist);
		assertError(res.as(Error.class), "Missing required field: name", 400);
	}

	@Test
	public void shouldnotbeAbleTocreatePlaylistwithexpiredtoken() {
		Playlist requestPlaylist=playlistBuilder("AR Rahman and Anirudh playlist updated", "New playlist Descritption", false);
		String invalidToken = "1233434";
		Response res = PlaylistAPI.post(requestPlaylist, invalidToken);
		assertError(res.as(Error.class), "Invalid access token", 401);

	}

}

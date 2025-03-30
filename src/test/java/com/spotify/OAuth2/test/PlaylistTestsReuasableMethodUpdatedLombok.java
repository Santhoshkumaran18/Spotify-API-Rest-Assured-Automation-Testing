package com.spotify.OAuth2.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.util.concurrent.FakeTimeLimiter;
import com.spotify.OAuth2.api.StatusCode;
import com.spotify.OAuth2.api.applicationApi.PlaylistAPI;
import com.spotify.OAuth2.pojo.Error;
import com.spotify.OAuth2.pojo.InnerError;
import com.spotify.OAuth2.pojo.Playlist;
import com.spotify.OAuth2.pojo.PlaylistLombok;
import com.spotify.OAuth2.utils.DataLoader;
import com.spotify.OAuth2.utils.FakerUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
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

import java.lang.reflect.Method;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistTestsReuasableMethodUpdatedLombok extends BaseTest {
	
	
	
	@Step
	public Playlist playlistBuilder(String name, String Description, boolean _public) {
		Playlist playlist=new Playlist();
			playlist.setName(name);
			playlist.setDescription(Description);
			playlist.setPublic(false);
			return playlist;
	
	}

	@Step
	public void assertPlaylist(Playlist responseplaylist, Playlist requestPlaylist) {
		assertThat(responseplaylist.getName(), equalTo(requestPlaylist.getName()));
		assertThat(responseplaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
		assertThat(responseplaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
	}
	
	@Step
	public void assertStatuscode(int actualStatusCode,int expecteStatusCode) {
		assertThat(actualStatusCode, equalTo(expecteStatusCode));
	}
	
	@Step
	public void assertError(Error error,String Errormessage,int expectedStatusCode) {
		assertThat(error.getError().getStatus(), equalTo(expectedStatusCode));
		assertThat(error.getError().getMessage(), equalTo(Errormessage));
	}
	
	@Story("Playlist story")
	@Link("https://example.org")
	@Link(name = "Allure", type = "mylink")
	@TmsLink("1232334")
	@Issue("12323")
	@Description("This is part of the Create playlist ")
	@Test (description = "Create Playlist")
	public void shouldbeAbleTocreatePlaylist() {
		Playlist requestPlaylist = playlistBuilder(FakerUtils.generatePlaylistName(),FakerUtils.generateDescription(), false);
		Response res = PlaylistAPI.post(requestPlaylist);		
		assertStatuscode(res.statusCode(), StatusCode.CODE_201.getCode());
		assertPlaylist(res.as(Playlist.class), requestPlaylist);

	}

	@Test
	public void shouldbeabletoGetAPlaylist() {
		
		Response res = PlaylistAPI.get(DataLoader.getInstance().getPlaylistid());
		assertStatuscode(res.statusCode(), StatusCode.CODE_200.getCode());

	}

	@Test
	public void shouldBeAbletoupdatethePlaylist() {
		Playlist requestPlaylist=playlistBuilder(FakerUtils.generatePlaylistName(),FakerUtils.generateDescription(), false);
		Response res = PlaylistAPI.update(requestPlaylist, DataLoader.getInstance().getUpdatePlaylistID());
		assertStatuscode(res.statusCode(), StatusCode.CODE_200.getCode());

	}

	@Test
	public void shouldnotbeAbleTocreatePlaylist() {
		Playlist requestPlaylist=playlistBuilder("",FakerUtils.generateDescription(), false);
	
		Response res = PlaylistAPI.post(requestPlaylist);
		assertError(res.as(Error.class), StatusCode.CODE_400.getMsg(), StatusCode.CODE_400.getCode());
	}

	@Test
	public void shouldnotbeAbleTocreatePlaylistwithexpiredtoken() {
		Playlist requestPlaylist=playlistBuilder(FakerUtils.generatePlaylistName(),FakerUtils.generateDescription(), false);
		String invalidToken = "1233434";
		Response res = PlaylistAPI.post(requestPlaylist, invalidToken);
		assertError(res.as(Error.class), StatusCode.CODE_401.getMsg(), StatusCode.CODE_401.getCode());

	}

}

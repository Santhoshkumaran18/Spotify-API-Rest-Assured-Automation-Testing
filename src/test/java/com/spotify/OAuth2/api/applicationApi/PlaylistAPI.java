package com.spotify.OAuth2.api.applicationApi;

import static io.restassured.RestAssured.given;

import com.spotify.OAuth2.api.RestResource;
import com.spotify.OAuth2.api.Route;
import com.spotify.OAuth2.api.SpecBuilder;
import com.spotify.OAuth2.api.TokenManager;
import com.spotify.OAuth2.pojo.Playlist;
import com.spotify.OAuth2.utils.ConfigLoader;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PlaylistAPI extends SpecBuilder{
	@Step
	public static Response post(Playlist requestPlaylist){
		return RestResource.post(Route.USERS + ConfigLoader.getInstance().getUserId() + Route.PLAYLISTS, requestPlaylist, TokenManager.getToken());
		
	}
	
	public static Response post(Playlist requestPlaylist,String token){
		return RestResource.post(Route.USERS + ConfigLoader.getInstance().getUserId() + Route.PLAYLISTS, requestPlaylist, "12234");
		
	}
	
	public static Response get(String playlistId) {
		return RestResource.get(Route.PLAYLISTS + "/" + playlistId, TokenManager.getToken());
		
	}
	
	public static Response update(Playlist requestPlaylist,String playlistId) {
		return RestResource.update(Route.PLAYLISTS + "/" +playlistId, TokenManager.getToken(), requestPlaylist);
		
	}
}

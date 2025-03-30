package com.spotify.OAuth2.api;

import java.time.Instant;
import java.util.HashMap;

import com.spotify.OAuth2.utils.ConfigLoader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TokenManager extends SpecBuilder {
	private static String access_token;
	private static Instant expiry_time;
	public synchronized static String getToken() {
		try {
			if(access_token==null || Instant.now().isAfter(expiry_time)) {
				System.out.println("Renewing the token....");
				access_token=renew_Token().path("access_token");
				int expiry_duration_in_seconds=renew_Token().path("expires_in");
				expiry_time=Instant.now().plusSeconds(expiry_duration_in_seconds-300);
			}else {
				System.out.println("Token is good to use");
			}
			
		}catch (Exception e) {
			throw new RuntimeException("ABORT! Failed to get the token");
		}
		return access_token;
		
	}
	private static Response renew_Token() {
		HashMap<String,String> formparamerters=new HashMap<String,String>();
		formparamerters.put("grant_type", ConfigLoader.getInstance().getGranttype());
		formparamerters.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
		formparamerters.put("client_id", ConfigLoader.getInstance().getClient());
		formparamerters.put("client_secret", ConfigLoader.getInstance().getClientSecret());
		
		Response res=RestResource.postAccount(formparamerters);
		
		if(res.statusCode()!=200) {
			throw new RuntimeException("ABORT! Renvew token faileed");
		}
		return res;
		
		
	}
}

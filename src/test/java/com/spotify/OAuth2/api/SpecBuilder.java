package com.spotify.OAuth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	public static String access_token = "BQBAxOzyeZxPSprncL45K3TbjfTFCod2zRTKFwZH3BcLQ2pzd8Mh2ha28oIVuH1Hcmf95sHLnPD6rhbWtOGfruOU7kkAX7nFN2o0kD_5DSGSQa2A4Qgy7TJZOzN7ADDXsIfQE94E3O3pHPMzu8ruyPW8nmiD0vkV9evQoX2U6mxspFJqMhDV5zJ0Pj8D0pQ0AU1xKoIAlDqJfWSHsy5x8W_q3xlICqIn-Uvoj-c-gVHrmeQhtbuZ225sMzYRa_KCB6jDcbzXvl6o4WQTJCLaDkd1oARhb63Tl7QQjsKrIi5aHRlZ";
	public static RequestSpecification getrequestSpec() {
		return new RequestSpecBuilder()
				.setBaseUri(System.getProperty("BASE_URI"))
//				.setBaseUri("https://api.spotify.com")
				.setBasePath(Route.BASE_PATH)
				.addFilter(new AllureRestAssured())
				.setContentType(ContentType.JSON).log(LogDetail.ALL).build();
		
		
	}
	
	public static RequestSpecification getAccountrequestspec() {
		return new RequestSpecBuilder()
				.setBaseUri(System.getProperty("ACCOUNT_BASE_URI"))
//				.setBaseUri("https://accounts.spotify.com")
				.addFilter(new AllureRestAssured())
				.setContentType(ContentType.URLENC).log(LogDetail.ALL).build();
		
		
	}
	
	public static ResponseSpecification getResponseSpec() {
		return new ResponseSpecBuilder().log(LogDetail.ALL).build();

	}
}

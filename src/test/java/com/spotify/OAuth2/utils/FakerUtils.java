package com.spotify.OAuth2.utils;

import com.github.javafaker.Faker;

public class FakerUtils {
	Faker faker;
	public static String generatePlaylistName() {
		Faker faker=new Faker();
//		return "Playlist" + faker.regexify("[A-Za-z0-9,-_]{10}");
		return faker.name().fullName();
		
	}
	
	public static String generateDescription() {
		Faker faker=new Faker();
//		return "Description" + faker.regexify("[A-Za-z0-9,-_@#$]{50}");
		return faker.music().genre();
		
	}
}

package com.spotify.OAuth2.test;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;

public class BaseTest {
	@BeforeMethod
	public static void beforeMethod(Method m) {
		System.out.println("Starting the test: " + m.getName());
		System.out.println("Thread ID:" + Thread.currentThread().getId());
	}
}

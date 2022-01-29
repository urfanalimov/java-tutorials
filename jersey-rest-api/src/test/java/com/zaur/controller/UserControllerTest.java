package com.zaur.controller;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

public class UserControllerTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(UserController.class);
	}

	@Test
	public void get() {
		String response = target("/users/1").request().get(String.class);
		Assert.assertTrue("{\"id\":1,\"firstName\":\"Zaur\",\"lastName\":\"Alimov\"}".equals(response));
	}

	@Test
	public void me() {
		String response = target("/users/me").request().get(String.class);
		Assert.assertTrue("Me is".equals(response));
	}
}

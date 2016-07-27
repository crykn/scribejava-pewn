package de.damios.pewn.api;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import de.damios.pewn.api.service.PewnOAuthServiceImpl;

public class PewnApi extends DefaultApi20 {

	protected PewnApi() {
	}

	private static class InstanceHolder {
		private static final PewnApi INSTANCE = new PewnApi();
	}

	public static PewnApi instance() {
		return InstanceHolder.INSTANCE;
	}

	@Override
	public Verb getAccessTokenVerb() {
		return Verb.POST;
	}

	@Override
	public String getAccessTokenEndpoint() {
		return "http://localhost:8080/api/v1/oauth/token/";
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return "http://localhost:8080/auth/";
	}

	@Override
	public String getRefreshTokenEndpoint() {
		return "http://localhost:8080/api/v1/oauth/token/";
	}

	@Override
	public OAuth20Service createService(OAuthConfig config) {
		return new PewnOAuthServiceImpl(this, config);
	}

}

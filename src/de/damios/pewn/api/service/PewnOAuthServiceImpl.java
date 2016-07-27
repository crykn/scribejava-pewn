package de.damios.pewn.api.service;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.AbstractRequest;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.OAuth20Service;

public class PewnOAuthServiceImpl extends OAuth20Service {

	public PewnOAuthServiceImpl(DefaultApi20 api, OAuthConfig config) {
		super(api, config);
	}

	@Override
	protected <T extends AbstractRequest> T createAccessTokenRequest(
			String oauthVerifier, T request) {
		final OAuthConfig config = getConfig();
		request.addBodyParameter(OAuthConstants.CLIENT_ID, config.getApiKey());
		request.addParameter(OAuthConstants.CLIENT_ID, config.getApiKey());
		request.addBodyParameter(OAuthConstants.CLIENT_SECRET,
				config.getApiSecret());
		request.addBodyParameter(OAuthConstants.REDIRECT_URI,
				config.getCallback());

		request.addBodyParameter(OAuthConstants.GRANT_TYPE,
				OAuthConstants.AUTHORIZATION_CODE);
		request.addBodyParameter(OAuthConstants.CODE, oauthVerifier);

		return request;
	}

	@Override
	public void signRequest(OAuth2AccessToken accessToken,
			AbstractRequest request) {
		request.addHeader("Authorization",
				"Bearer " + accessToken.getAccessToken());
	}
}

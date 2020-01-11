package com.firebase.demo.db.connection;

import java.io.UnsupportedEncodingException;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.service.Firebase;

@Component
public class FireBaseConnection {
	private Firebase firebase;
	@Value("${firebase_url}")
	private String fireBaseUrl;
	@Value("${firebase_key}")
	private String fireBaseKey;

	@PostConstruct
	private void init() throws FirebaseException, UnsupportedEncodingException {
		if (StringUtils.isEmpty(fireBaseUrl)) {
			throw new IllegalArgumentException("Program-argument 'baseUrl' not found but required");
		}
		firebase = new Firebase(fireBaseUrl);
		firebase.delete();
	}

	public Firebase getFirebase() {
		return firebase;
	}

}

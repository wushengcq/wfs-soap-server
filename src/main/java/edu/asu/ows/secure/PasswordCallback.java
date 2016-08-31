package edu.asu.ows.secure;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class PasswordCallback implements CallbackHandler {	
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		org.apache.wss4j.common.ext.WSPasswordCallback callback = (org.apache.wss4j.common.ext.WSPasswordCallback) callbacks[0];
		int usage = callback.getUsage();
		if (usage == WSPasswordCallback.USERNAME_TOKEN) {
			callback.setPassword("123456");
		}
	}
}

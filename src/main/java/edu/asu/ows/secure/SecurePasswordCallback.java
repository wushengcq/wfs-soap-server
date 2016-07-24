package edu.asu.ows.secure;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.log4j.Logger;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.springframework.beans.factory.annotation.Autowired;

public class SecurePasswordCallback implements CallbackHandler {
	private Logger logger = Logger.getLogger(SecurePasswordCallback.class);
	private UserManager userManager = null;
	
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];
		logger.debug(callback.getIdentifier());
		logger.debug(callback.getPassword());
		
		User user = this.getUserManager().getUser(callback.getIdentifier());
		if (user != null) {			
//			if (! user.getPassword().equals(callback.getPassword())) {
//				throw new SecurityException("Incorrect password.");
//			}
			callback.setPassword(user.getPassword());
		} else {
			throw new SecurityException("Invalid user.");
		}
	}

	@Autowired
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	public UserManager getUserManager() {
		return userManager;
	}

}

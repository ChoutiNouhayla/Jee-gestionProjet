package Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Data.UserDataAccess;
import Models.User;

public class AuthServiceImpl implements AuthService {

    private UserDataAccess userDataAccess;
    
    public AuthServiceImpl(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    

    @Override
    public User authenticate(String email, String password) {
        User user = null;
		try {
			user = UserDataAccess.getUserByEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Vérifiez si l'utilisateur existe et si le mot de passe correspond
        if (user != null && password.equals(user.getPassword())) {
            // L'authentification réussit
            return user;
        } else {
            // L'authentification échoue
            return null;
        }
    }
    

    
}

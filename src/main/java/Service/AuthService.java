package Service;

import Models.User;

public interface AuthService {
    User authenticate(String email, String password);
}
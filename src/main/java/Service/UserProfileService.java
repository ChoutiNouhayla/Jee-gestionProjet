package Service;

import java.util.List;
import java.util.Map;

import Models.User;

public interface UserProfileService {
	Map<String, Object> getUserProfile(long userId);
	    List<String> getUserCompetencies(long userId);

}

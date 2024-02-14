package Service;

import java.util.List;
import java.util.Map;

import Data.UserData;
import Models.User;

public class UserProfileServiceImpl implements UserProfileService  {
	

	public UserProfileServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, Object> getUserProfile(long userId) {
		// TODO Auto-generated method stub
		UserData userData= new UserData();
		return userData.getUserProfile(userId);
	}

	@Override
	public List<String> getUserCompetencies(long userId) {
		// TODO Auto-generated method stub
		UserData userData= new UserData();
		return userData.getUserCompetencies(userId);
	}

}

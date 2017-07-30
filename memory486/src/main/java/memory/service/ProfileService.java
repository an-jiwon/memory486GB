package memory.service;

import memory.model.Profile;

public interface ProfileService {

	Profile select(String id);

	int insert(Profile profile);

	Profile getProfile(int pro_no);

	int update(Profile profile);

	int delete(int pro_no);

}

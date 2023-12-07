package ru.george.booksharing.client.model.user;

import java.util.List;

public class UserDataSource {
    private final CreateUserRequest createUserRequest = new CreateUserRequest();
    private final GetUsersByCredentialsRequest getUsersByCredentialsRequest = new GetUsersByCredentialsRequest();
    private final GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();

    public GetUserResponse createUser(CreateUserBody body) throws Exception {
        return createUserRequest.makeRequest(body);
    }

    public GetUserResponse getUsersByCredentials(GetUsersByCredentialsBody body) throws Exception {
        return getUsersByCredentialsRequest.makeRequest(body);
    }

    public List<GetUserResponse> getAllUsers() throws Exception {
        return getAllUsersRequest.makeRequest();
    }
}

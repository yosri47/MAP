package interfaces;

import entities.User;

import javax.ejb.Remote;

@Remote
public interface UserServiceRemote {
    public User getUserById(int id);
    public boolean login (String username,String password);
    public User findUserByEmail(String email);

}


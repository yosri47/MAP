package utilities;

import entities.User;
import interfaces.UserServiceRemote;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;

@RequestScoped
public class AuthenticatedUserProducer {
    @EJB
    UserServiceRemote us;



    @Produces
    @RequestScoped
    @AuthenticatedUser
    private User authenticatedUser;


    public void handleAuthenticationEvent(@Observes @AuthenticatedUser String mail) {
        this.authenticatedUser = findUser(mail);
    }
    private User findUser(String mail) {
        // Hit the the database or a service to find a user by its username and return it
        // Return the User instance
        return us.findUserByEmail(mail);
    }


}

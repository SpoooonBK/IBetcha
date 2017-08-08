package net.estebanrodriguez.apps.ibetcha.data;

import net.estebanrodriguez.apps.ibetcha.model.Agreement;
import net.estebanrodriguez.apps.ibetcha.model.User;

public interface DataAccessObject {



    User getUser(String userId);
    void addUser(User user);
    User removeUser(String userId);
    User updateUser(User user);
    boolean hasUser(String userId);

    void AddAgreement(Agreement agreement);
    Agreement getAgreement(String agreementId);


}

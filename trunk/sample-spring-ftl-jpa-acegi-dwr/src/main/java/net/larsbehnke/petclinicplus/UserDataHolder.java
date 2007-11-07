package net.larsbehnke.petclinicplus;

/**
 * Parent class of various user categories, for instance vets and owner.
 * @author Lars Behnke
 */
public abstract class UserDataHolder extends BaseEntity {

    private User userData;

    public User getUserData() {
        if (userData == null) {
            userData = new User();
        }
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

}

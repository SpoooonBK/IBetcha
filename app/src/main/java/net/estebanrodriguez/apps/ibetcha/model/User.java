package net.estebanrodriguez.apps.ibetcha.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String mId;
    private double mCredits;
    private List<String> mAgreementIds;
    private List<String> mFriendIds;
    private String mName;

    public User() {
        mCredits = 100;
        mAgreementIds = new ArrayList<>();
        mFriendIds = new ArrayList<>();
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public double getCredits() {
        return mCredits;
    }

    public List<String> getAgreementIds() {
        return mAgreementIds;
    }

    public List<String> getFriendIds() {
        return mFriendIds;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void addFriend(String userId){
        mFriendIds.add(userId);
    }

    public void removeFriend(String userId){
        mFriendIds.remove(userId);
    }

    public void addAgreement(Agreement agreement) {
        mAgreementIds.add(agreement.getId());
    }


}

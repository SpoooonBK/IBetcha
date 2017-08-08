package net.estebanrodriguez.apps.ibetcha.model;

import java.util.List;

public class Agreement {

    private List<Wager> mWagers;
    private String mTerms;
    private String mJudgeId;
    private String mId;

    public Agreement() {
    }

    public Agreement(List<Wager> wagers, String terms, String judgeId) {
        mWagers = wagers;
        mTerms = terms;
        mJudgeId = judgeId;
    }

    public List<Wager> getWagers() {
        return mWagers;
    }

    public String getTerms() {
        return mTerms;
    }

    public String getJudgeId() {
        return mJudgeId;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}

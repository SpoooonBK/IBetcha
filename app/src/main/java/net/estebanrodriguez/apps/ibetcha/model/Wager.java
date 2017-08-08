package net.estebanrodriguez.apps.ibetcha.model;

import net.estebanrodriguez.apps.ibetcha.exceptions.InvalidWagerException;

public class Wager {

    private String userId;
    private double amountWagered;

    public Wager() {
    }

    private Wager(String userId, double amountWagered) {
            this.userId = userId;
            this.amountWagered = amountWagered;
    }

    public Wager buildWager(String userId, double amountWagered) throws InvalidWagerException {
        if(validateWager(amountWagered)){
            return new Wager(userId, amountWagered);
        }else throw new InvalidWagerException(amountWagered);
    }

    public String getUserId() {
        return userId;
    }

    public double getAmountWagered() {
        return amountWagered;
    }

    private boolean validateWager(double amountWagered){
        return amountWagered > 0;
    }
}

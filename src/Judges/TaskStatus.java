package Judges;

public class TaskStatus {
    private int HPlose = 0;
    private int numberSpellCards = 0;
    private int numberWeaponCards = 0;
    private int numberMinionCards = 0;
    private int useAllCardsInOneTurn = 0;

    private boolean winOrLose;

    private int currentOppLostHPInOneTurn = 0;

    private int oppLostHPInOneTurn = 0;

    public int getHPlose() {
        return HPlose;
    }

    public int getNumberMinionCards() {
        return numberMinionCards;
    }

    public int getNumberSpellCards() {
        return numberSpellCards;
    }

    public int getNumberWeaponCards() {
        return numberWeaponCards;
    }

    public int getUseAllCardsInOneTurn() {
        return useAllCardsInOneTurn;
    }

    public int getCurrentOppLostHPInOneTurn() {
        return currentOppLostHPInOneTurn;
    }

    public int getOppLostHPInOneTurn() {
        return oppLostHPInOneTurn;
    }



    public void setHPlose(int HPlose) {
        this.HPlose += HPlose;
    }

    public void setNumberMinionCards() {
        this.numberMinionCards++;
    }

    public void setNumberSpellCards() {
        this.numberSpellCards++;
    }

    public void setNumberWeaponCards() {
        this.numberWeaponCards++;
    }

    public void setUseAllCardsInOneTurn() {
        this.useAllCardsInOneTurn++;
    }

    public void setWinOrLose(boolean winOrLose) {
        this.winOrLose = winOrLose;
    }

    public void setCurrentOppLostHPInOneTurn(int damage) {
        this.currentOppLostHPInOneTurn += damage;
    }

    public void setOppLostHPInOneTurn() {
        if (currentOppLostHPInOneTurn > oppLostHPInOneTurn){
            oppLostHPInOneTurn = currentOppLostHPInOneTurn;
            currentOppLostHPInOneTurn = 0;
        }
        else{
            currentOppLostHPInOneTurn = 0;
        }
    }
}

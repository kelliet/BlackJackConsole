package com.tiy;


public class Card {

    int suit;
    int rank;

    public Card(int s, int r) {
        suit = s;
        rank = r;
    }

    public static final int CLUBS = 0;
    public static final int SPADES = 1;
    public static final int HEARTS = 2;
    public static final int DIAMONDS = 3;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    public static final int NINE = 9;
    public static final int TEN = 10;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;

    public String getSuitStringShort() {
        String suitString = null;

        if(suit == CLUBS) {
            suitString = "Cl";
        } else if(suit == SPADES) {
            suitString = "Sp";
        } else if(suit == HEARTS) {
            suitString = "He";
        } else if(suit == DIAMONDS) {
            suitString = "Di";
        }

        return suitString;
    }

    public String getRankStringShort() {
        String rankString = null;

        switch(rank) {
            case TWO:
            case THREE:
            case FOUR:
            case FIVE:
            case SIX:
            case SEVEN:
            case EIGHT:
            case NINE:
            case TEN:
                rankString = Integer.toString(rank);
                break;
            case JACK:
                rankString = "J";
                break;
            case QUEEN:
                rankString = "Q";
                break;
            case KING:
                rankString = "K";
                break;
            case ACE:
                rankString = "A";
                break;
        }

        return rankString;
    }
}

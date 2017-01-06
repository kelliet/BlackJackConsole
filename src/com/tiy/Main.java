package com.tiy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

        public static void main(String []args) {

        do {
        //Create a deck
        ArrayList<Card> deck = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = Card.TWO; j <= Card.ACE; j++) {
                deck.add(new Card(i, j));
            }
        }

         Collections.shuffle(deck);
        ArrayList<Card> dealer = new ArrayList<>();
        ArrayList<Card> player = new ArrayList<>();

        dealer.add(deck.remove(0));
        player.add(deck.remove(0));

        dealer.add(deck.remove(0));
        player.add(deck.remove(0));
        //Display hands
        System.out.println("The dealer stands at 17 or above, hits 16 or below.");

        printDealerHand(dealer, true);
        //Ask what player wants to do
        System.out.println("Press H to hit, S to stand");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("S")) {
            if (!input.equalsIgnoreCase("H")) {
                System.out.println("Invalid Input");

                System.out.println("Press H to hit, S to stand");
                input = scanner.nextLine();
            } else {
                player.add(deck.remove(0));
                printDealerHand(dealer, true);
                printPlayerHand(player);

                if (getHandTotal(player, false) > 21) { //BUST
                    System.out.println();

                    printDealerHand(dealer, false);
                    printPlayerHand(player);
                    System.out.println("Player Busted, Dealer Wins");

                    break;
                } else {
                    System.out.println("Press H to hit, S to stand");
                    input = scanner.nextLine();
                    if (getHandTotal(player, false) > 21) {
                        System.out.println();

                        printDealerHand(dealer, false);
                        printPlayerHand(player);
                        System.out.println("Player Busted, Dealer Wins");
                    } else {
                        printDealerHand(dealer, false);
                        printPlayerHand(player);

                        int dealerTotal = getHandTotal(dealer, true);
                        int playerTotal = getHandTotal(player, true);
                        if (playerTotal > 21) {
                            playerTotal = getHandTotal(player, false);
                        }

                        //Determine Dealer Actions
                        if (dealerTotal > 16) { //Dealer stands
                            System.out.println("Dealer Stands");
                            printResults(dealerTotal, playerTotal);
                        } else { //Dealer hits
                            boolean stand = false;
                            boolean bust = false;
                            while (!stand && !bust) {
                                System.out.println("Dealer Hits");
                                dealer.add(deck.remove(0));

                                printDealerHand(dealer, false);
                                printPlayerHand(player);

                                dealerTotal = getHandTotal(dealer, true);

                                if (dealerTotal > 16 && dealerTotal <= 21) { //If valid hand, stand
                                    stand = true;
                                } else { //If higher then 21, check with ace low, if still higher then 21, bust, else continue and hit
                                    if (dealerTotal > 21) {
                                        dealerTotal = getHandTotal(dealer, false);

                                        if (dealerTotal > 21) {
                                            bust = true;
                                        } else if(dealerTotal > 16) {
                                            stand = true;
                                        }
                                    }
                                    if (bust) {
                                        System.out.println("Dealer Busted, Player Wins");
                                    } else if (stand) {
                                        dealerTotal = getHandTotal(dealer, true);
                                        if (dealerTotal > 21) {
                                            dealerTotal = getHandTotal(dealer, false);
                                        }
                                        playerTotal = getHandTotal(player, true);
                                        if (playerTotal > 21) {
                                            playerTotal = getHandTotal(player, false);
                                        }
                                        System.out.println("Dealer Stands");
                                        printResults(dealerTotal, playerTotal);
                                    }

                                }
                            }

                        }
                    }
                }

            }
        }
            //Loop around to play again
            System.out.println("Would you like to play again? Y or N");
            String play = scanner.nextLine();
            if("N".equalsIgnoreCase(play)) {
                System.out.println("Thank you for playing!");
                break;
            }
        } while(true);

    }

    public static int getHandTotal(ArrayList<Card> hand, boolean aceHigh) {
        int total = 0;

        for(Card card: hand) {
            switch(card.rank) {
                case Card.TWO:
                case Card.THREE:
                case Card.FOUR:
                case Card.FIVE:
                case Card.SIX:
                case Card.SEVEN:
                case Card.EIGHT:
                case Card.NINE:
                case Card.TEN:
                    total += card.rank;
                    break;
                case Card.JACK:
                case Card.QUEEN:
                case Card.KING:
                    total += 10;
                    break;
                case Card.ACE:
                    if(aceHigh) {
                        total += 11;
                    } else {
                        total += 1;
                    }
                    break;
            }
        }

        return total;
    }

    private static void printDealerHand(ArrayList<Card> hand, boolean opening) {
        System.out.print("Dealer: ");
          if(opening) {
            System.out.printf("[%s%s][XX]", hand.get(0).getSuitStringShort(), hand.get(0).getRankStringShort());
            System.out.println();
        } else {
            for (Card card : hand) {
                System.out.printf("[%s%s]", card.getSuitStringShort(), card.getRankStringShort());
              }
            int total = getHandTotal(hand, true);
            if (total > 21) {
                total = getHandTotal(hand, false);
            }
            System.out.println(" " + total);
          }

    }

    private static void printPlayerHand(ArrayList<Card> hand) {
        System.out.print("Player: ");
         for(Card card : hand) {
            System.out.printf("[%s%s]", card.getSuitStringShort(), card.getRankStringShort());
        }
        int total = getHandTotal(hand, true);
        if(total > 21) {
            total = getHandTotal(hand, false);
        }
        System.out.println(" " + total );

    }

    private static void printResults(int dealerTotal, int playerTotal) {
        if(playerTotal > dealerTotal) {
            System.out.println("Player Wins");
        } else if(playerTotal < dealerTotal) {
            System.out.println("Dealer Wins");
         } else if(playerTotal == dealerTotal) {
            System.out.println("Push");
         }

    }
}

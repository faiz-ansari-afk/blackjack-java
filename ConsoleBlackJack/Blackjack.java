package com.ConsoleBlackJack;

import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        System.out.println("Welcome to Black Jack!");
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();
        double playerMoney = 100.00 ;
        Scanner userInput = new Scanner(System.in);
        while (playerMoney>0){
            System.out.println("You have $ " + playerMoney + " how much money would you like to bet");
            double playerBet = userInput.nextDouble();
            if (playerBet>playerMoney){
                System.out.println("Aukat me reh ");
                break;
            }
            boolean endRound  = false ;
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while (true){
                System.out.println("Your hand: ");
                System.out.println(playerDeck.toString());
                System.out.println("Your deck is valued at:" + playerDeck.cardsValue());

                System.out.println("Dealer hand: " + dealerDeck.getCard(0).toString()+ "and [HIDDEN]");

                System.out.println("Would you like ton 1.HIT and 2.STAY ?");
                int response = userInput.nextInt();
                if (response == 1){
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a "+ playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    if (playerDeck.cardsValue()>21){
                        System.out.println("BUST. currently valued at: "+ playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                if (response == 2)
                    break;
            }
//            reveal dealers card
            System.out.println("Dealer cards: "+ dealerDeck.toString());
            if (dealerDeck.cardsValue()>playerDeck.cardsValue() && endRound == false) {
                System.out.println("Dealer wins..... :>");
                playerMoney -= playerBet;
                endRound = true ;
            }
            while((dealerDeck.cardsValue()<17) && endRound == false){
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }
            System.out.println("Dealers Hand valued at: " + dealerDeck.cardsValue());
            if (dealerDeck.cardsValue()>21 && endRound == false){
                System.out.println("Dealer BUST, YOU WIN");
                playerMoney += playerBet;
                endRound = true;
            }
            if (playerDeck.cardsValue() == dealerDeck.cardsValue() && endRound == false){
                System.out.println("PUSH");
                endRound = true ;
            }
            if (playerDeck.cardsValue() > dealerDeck.cardsValue() && endRound == false){
                System.out.println("You win the HAND!");
                playerMoney += playerBet;
                endRound = true;
            }
            playerDeck.moveToDeck(playingDeck);
            dealerDeck.moveToDeck(playingDeck);
            System.out.println("End of hands");
        }
        System.out.println("Game over , you are out of money");
//        System.out.println(playingDeck);
    }

}

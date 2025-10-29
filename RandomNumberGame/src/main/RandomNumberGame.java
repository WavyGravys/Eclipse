package main;

import java.util.Random;


public class RandomNumberGame {

    static Random rand = new Random();
    
    static int playOneVictories = 0;
    static int playTwoVictories = 0;
    static int draws = 0;
    
    public static void playRound() { 
        int randomGoalNumber = rand.nextInt(100)+1;
        int playerOneDifference = Math.abs(randomGoalNumber-rand.nextInt(101));
        int playerTwoDifference = Math.abs(randomGoalNumber-50);
        
        if (playerOneDifference > playerTwoDifference) {
            playTwoVictories += 1;
        } else if (playerOneDifference == playerTwoDifference){
            draws += 1;
        } else {            
            playOneVictories += 1;
        }
    }
    
    public static void main(String[] args) {
        int simulatedRounds = 100000000;
        
        for(int i=0;i<simulatedRounds;i++) {
            playRound();
        }
        double playerOneWinrate = (double)playOneVictories/simulatedRounds*100;
        double playerTwoWinrate = (double)playTwoVictories/simulatedRounds*100;
        double drawrate = (double)draws/simulatedRounds*100;
        
        System.out.printf("\"random number\" player wins: %d; winrate: %.2f", playOneVictories, playerOneWinrate);
        System.out.print("% \n");
        System.out.printf("\"static 50\" player wins: %d; winrate: %.2f", playTwoVictories, playerTwoWinrate);
        System.out.print("% \n");
        System.out.printf("draws: %d; drawrate: %.2f", playTwoVictories, drawrate);
        System.out.print("% \n");
    }
}
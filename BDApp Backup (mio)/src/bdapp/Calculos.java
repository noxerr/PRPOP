/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bdapp;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
//import java.util.ArrayList;

/**
 *
 * @author dani__000
 */
public class Calculos extends Numeros {
    //public static ArrayList vec = new ArrayList();
    public static double SubDivision = 100;
    public static double[] v1 = new double[(int)SubDivision];
    public static double[] max = new double[(int)SubDivision];
    public static double[] min = new double[(int)SubDivision];
    //color, tercio, mitad, paridad, numero;(0 ->red, 1 ->black), (1, 2, 3), (1,2), (1 ->imp,2->par), (0-36)
    
    public void run(double n) throws FileNotFoundException, IOException{
        int How_Often = BDApplication.Numeros_ran / (int)SubDivision, Traveled = 0, Posicion = 1;
        
        int LosesInRow = 0, WinsInRow = 0, CurrentWiningRow = 0, CurrentLosingRow = 0;
        double CurrentMoney = 50.0, MaxDrawDown = 0.0, LosesAmountRow = 0.0, wins_loses;
        double totalWinGames = 0, totalLoseGames = 0; 
        double startBet = 0.5/*, maxBet = 3.0*/;
        double maxBet = n;
        
        int startBetNum = 0; //color rojo (0)
        
        DataInputStream in = new DataInputStream (
                new FileInputStream ("C:\\Users\\dani__000\\Desktop\\PRPROP\\BDApp\\src\\bdapp\\Secuencia.c"));
        int num;
        DecimalFormat df = new DecimalFormat("0.000000"); 
        
        if (n == 1){ //jugada normal de prueba sin subir ni variar apuesta
            double CurrentBet = startBet;   
            try {
                while ((num = in.readInt()) != -1) {
                    if (orden[num][0] == startBetNum){
                        LosesInRow = 0;
                        LosesAmountRow = 0;
                        ++WinsInRow;
                        if (WinsInRow > CurrentWiningRow) CurrentWiningRow = WinsInRow;
                        ++totalWinGames;
                        CurrentMoney = CurrentMoney + CurrentBet;
                    }
                    else{
                        WinsInRow = 0;
                        ++LosesInRow;
                        if (LosesInRow > CurrentLosingRow) CurrentLosingRow = LosesInRow;
                        ++totalLoseGames;
                        LosesAmountRow = LosesAmountRow + CurrentBet;
                        if (LosesAmountRow > MaxDrawDown) MaxDrawDown = LosesAmountRow;
                        CurrentMoney = CurrentMoney - CurrentBet;
                    }
                }
            } catch (IOException io) {
                System.out.println("end1.");
            }
        }
        
        
        //---------------------------------------------------------
        //---------------------------------------------------------
        //---------------------------------------------------------
        //---------------------------------------------------------
        
        else if (n == 2){
            double CurrentBet = startBet; 
            double gBack = 0;
            int count = 0;
            int seguidas = 0;
            int vez = 0;
            try {
                while ((num = in.readInt()) != -1) {
                    if (orden[num][0] == startBetNum){
                        seguidas++;
                        LosesInRow = 0;
                        LosesAmountRow = 0;
                        ++WinsInRow;
                        if (WinsInRow > CurrentWiningRow) CurrentWiningRow = WinsInRow;
                        ++totalWinGames;
                        CurrentMoney = CurrentMoney + CurrentBet;
                        if (count < 1) {
                            if (gBack < 1){
                                CurrentBet = startBet;
                                gBack = 0;
                                count = 0;
                            }
                            else {
                                gBack = gBack - CurrentBet;
                                //CurrentBet = startBet * 2;
                                count = 0;
                            }
                        } 
                        else {
                            gBack = gBack - CurrentBet;
                            if (vez == 0)CurrentBet = CurrentBet - startBet;
                            else vez = 0;
                            count--;
                        }
                        if ((seguidas > 4) && (startBetNum == 1)) startBetNum = 0;
                        else if ((seguidas > 4) && (startBetNum == 0)) startBetNum = 1;
                    }
                    else{
                        seguidas =0;
                        vez = 1;
                        WinsInRow = 0;
                        ++LosesInRow;
                        if (LosesInRow > CurrentLosingRow) CurrentLosingRow = LosesInRow;
                        ++totalLoseGames;
                        LosesAmountRow = LosesAmountRow + CurrentBet;//maxima apuesta hecha
                        if (LosesAmountRow > MaxDrawDown) MaxDrawDown = LosesAmountRow;
                        CurrentMoney = CurrentMoney - CurrentBet;
                        gBack = gBack + CurrentBet;
                        if (CurrentBet < maxBet) CurrentBet = CurrentBet + startBet;
                        count++;
                    }
                }
            } catch (IOException io) {
                System.out.println("end2.");
            }
        }
        
        
        //---------------------------------------------------------
        //---------------------------------------------------------
        //---------------------------------------------------------
        //---------------------------------------------------------
        
        
        else if (n == 3){
            double CurrentBet = startBet; 
            double gBack = 0;
            int count = 0;
            int vez = 0;
            try {
                while ((num = in.readInt()) != -1) {
                    if (orden[num][0] == startBetNum){
                        LosesInRow = 0;
                        LosesAmountRow = 0;
                        ++WinsInRow;
                        if (WinsInRow > CurrentWiningRow) CurrentWiningRow = WinsInRow;
                        ++totalWinGames;
                        CurrentMoney = CurrentMoney + CurrentBet;
                        if (count < 1) {
                            if (gBack < 1){
                                CurrentBet = startBet;
                                gBack = 0;
                                count = 0;
                            }
                            else {
                                gBack = gBack - CurrentBet;
                                //CurrentBet = startBet * 2;
                                count = 0;
                            }
                        } 
                        else {
                            gBack = gBack - CurrentBet;
                            if (vez == 0)CurrentBet = CurrentBet - startBet;
                            else vez = 0;
                            count--;
                        }
                    }
                    else{
                        vez = 1;
                        WinsInRow = 0;
                        ++LosesInRow;
                        if (LosesInRow > CurrentLosingRow) CurrentLosingRow = LosesInRow;
                        ++totalLoseGames;
                        LosesAmountRow = LosesAmountRow + CurrentBet;//maxima apuesta hecha
                        if (LosesAmountRow > MaxDrawDown) MaxDrawDown = LosesAmountRow;
                        CurrentMoney = CurrentMoney - CurrentBet;
                        gBack = gBack + CurrentBet;
                        if (CurrentBet < maxBet) CurrentBet = CurrentBet + startBet;
                        count++;
                    }
                }
            } catch (IOException io) {
                System.out.println("3end.");
            }
        }
        
        //---------------------------------------------------------
        //---------------------------------------------------------
        //---------------------------------------------------------
        //---------------------------------------------------------
        
        else if (n >= 4){
            double CurrentBet = startBet; 
            v1[0] = CurrentBet;
            max[0] = CurrentBet;
            min[0] = CurrentBet;
            double minimo = CurrentBet;
            double maximo = CurrentBet;
            double gBack = 0;
            startBetNum = 3; //sector que no cogemos
            int count = 0;
            int vez = 0;
            try {
                while ((num = in.readInt()) != -1) {
                    if ((orden[num][1] != startBetNum) && (num != 0)){
                        LosesInRow = 0;
                        LosesAmountRow = 0;
                        ++WinsInRow;
                        if (WinsInRow > CurrentWiningRow) CurrentWiningRow = WinsInRow;
                        ++totalWinGames;
                        CurrentMoney = CurrentMoney + CurrentBet;
                        if ((count < 1) || (gBack < 1)) {
                            count = 0;
                            CurrentBet = startBet;
                        } 
                        else {
                            if (vez == 0){
                                gBack = gBack - CurrentBet;
                                if (CurrentBet > 1) CurrentBet = CurrentBet -startBet;
                                count--;
                            }
                            else vez--;
                        }
                    }
                    else {
                        vez = 1;
                        WinsInRow = 0;
                        ++LosesInRow;
                        if (LosesInRow > CurrentLosingRow) CurrentLosingRow = LosesInRow;
                        ++totalLoseGames;
                        LosesAmountRow = LosesAmountRow + 2*CurrentBet;//maxima apuesta hecha
                        if (LosesAmountRow > MaxDrawDown) MaxDrawDown = LosesAmountRow;
                        //if (num == 0) CurrentMoney = CurrentMoney - CurrentBet;
                        CurrentMoney = CurrentMoney - 2*CurrentBet;
                        gBack = gBack + 2*CurrentBet;
                        if ((2*CurrentBet+2*startBet) < maxBet) CurrentBet = CurrentBet + startBet;
                        count = count+1;
                    }
                    if (CurrentMoney > maximo) maximo = CurrentMoney;
                    else if (CurrentMoney < minimo) minimo = CurrentMoney;
                    if (Traveled >= How_Often){
                        Traveled = 0;
                        v1[Posicion] = CurrentMoney;
                        max[Posicion] = maximo;
                        min[Posicion] = minimo;
                        maximo = CurrentMoney;
                        minimo = CurrentMoney;
                        Posicion++;
                    }
                    else Traveled++;
                }
            } catch (IOException io) {
                System.out.println("end4.");
            }
        }
        
        //---------------------------------------------------------
        //---------------------------------------------------------
        
        
        wins_loses = totalWinGames/totalLoseGames;
        System.out.println("Relacion: "+df.format(wins_loses) + ". Maxdraw: " + MaxDrawDown +
                "\nCurrentMoney and totalwins: " + CurrentMoney + " and " +  totalWinGames + "Losing and winingStreak: "
                + CurrentLosingRow + " " + CurrentWiningRow);
        //BDApplication.fin = true;
    }
}

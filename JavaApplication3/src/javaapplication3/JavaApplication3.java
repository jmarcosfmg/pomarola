/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Image;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author João
 */
public class JavaApplication3 {

    public static ArrayList<Image> imag = new ArrayList<Image>();
    public static TelaCalculo calculo;
    public static IconeTarefa tray;
    public static long tempoinicial, tempodespertar;
    public static JFrame frame, frame1;
    public static int tempoT = 25, tempoG = 10, tempoP = 5, ciclo = -1, timer = -1;
    private static Thread oi;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger.getLogger(JavaApplication3.class.getName()).log(Level.INFO, "Iniciando aplicacao");
        buscarImagens();
        frame = new TelaInicial();
        oi = new Thread();
        tray = new IconeTarefa();

        IconeTarefa.remover();
    }

    public static void novociclo() {
        if (ciclo < 7) ciclo++;
        else {
            ciclo = 0;
            timer--;
        }
        if (timer == 0) new NovaTela(frame, true);
        else {
            setTempoI();
            setTempoF(ciclo);
            verificatempo();
        }
    }

    public static void verificatempo() {
        oi = new Thread(() -> {
            try {
                Thread.sleep(tempodespertar - tempoinicial);
                IconeTarefa.remover();
                novociclo();
                new TelaMensagem(frame1, false);
            } catch (InterruptedException ex) {
                Logger.getLogger(JavaApplication3.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        oi.start();
    }

    private static void buscarImagens() {
        Class classe = JavaApplication3.class;
        String caminhoImagens = "resources/images/";
        Logger.getLogger(JavaApplication3.class.getName()).log(Level.INFO, "Carregando imagens em ", classe.getResource(caminhoImagens));
        imag.add((new ImageIcon(classe.getResource(caminhoImagens+"Pomarola16.png"))).getImage());
        imag.add((new ImageIcon(classe.getResource(caminhoImagens+"Pomarola128.png"))).getImage());
        imag.add((new ImageIcon(classe.getResource(caminhoImagens+"Pomarola192.png"))).getImage());
        imag.add((new ImageIcon(classe.getResource(caminhoImagens+"Pomarola24.png"))).getImage());
        imag.add((new ImageIcon(classe.getResource(caminhoImagens+"Pomarola256.png"))).getImage());
        imag.add((new ImageIcon(classe.getResource(caminhoImagens+"Pomarola32.png"))).getImage());
        imag.add((new ImageIcon(classe.getResource(caminhoImagens+"Pomarola48.png"))).getImage());
        imag.add((new ImageIcon(classe.getResource(caminhoImagens+"Pomarola64.png"))).getImage());
        imag.add((new ImageIcon(classe.getResource(caminhoImagens+"Pomarola96.png"))).getImage());
        Logger.getLogger(JavaApplication3.class.getName()).log(Level.INFO, "Imagens carregadas");
    }

    public static void caixa() {
        new TelaTempo(frame, false);
    }

    public static void setTempoT(int aux) {
        tempoT = aux;
    }

    public static int getTempoT() {
        return tempoT;
    }

    public static void setTempoG(int aux) {
        tempoG = aux;
    }

    public static int getTempoG() {
        return tempoG;
    }

    public static void setTempoP(int aux) {
        tempoP = aux;
    }

    public static int getTempoP() {
        return tempoP;
    }

    public static void setTempoI() {
        tempoinicial = System.currentTimeMillis();
    }

    public static long getTempoI() {
        return tempoinicial;
    }

    public static void setTempoF(int n) {
        if (n % 2 == 0) tempodespertar = tempoinicial + TimeUnit.MILLISECONDS.convert(tempoT, TimeUnit.MINUTES);
        else if (n < 6) tempodespertar = tempoinicial + TimeUnit.MILLISECONDS.convert(tempoP, TimeUnit.MINUTES);
        else if (n == 7) tempodespertar = tempoinicial + TimeUnit.MILLISECONDS.convert(tempoG, TimeUnit.MINUTES);
    }

    public static long getTempoF() {
        return tempodespertar;
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author João
 */



public class IconeTarefa {
    static TrayIcon trayIcon; 

    public IconeTarefa() {
        aparece();
    }
    
    public static void aparece(){
        if(!SystemTray.isSupported()){
            System.out.println("Erro na Tray");
        }
        else{
        trayIcon = new TrayIcon((new ImageIcon("Pomarola16.png").getImage()),"Pomarola");
        trayIcon.setImageAutoSize(true);
        tray = SystemTray.getSystemTray();
        final PopupMenu menu = new PopupMenu();
        
        MenuItem time = new MenuItem("Tempo");
        menu.add(time);
        MenuItem exit = new MenuItem("Encerrar");
        menu.addSeparator();
        menu.add(exit);
        
        time.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaTempo(new JFrame(),false);
                remover();
            }
        });
        
        exit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        trayIcon.setPopupMenu(menu);   
        colocar();
    }
        
    }
    
    public static void colocar(){
        try {
                tray.add(trayIcon);
            } catch (Exception ex) {
                Logger.getLogger(IconeTarefa.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void remover(){
            tray.remove(trayIcon);
    }
    
    protected static Image createIcon(String path, String desc){
        return (new ImageIcon(path, desc)).getImage();
    }
    private static SystemTray tray;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author João
 */


public class IconeTarefa {
    static TrayIcon trayIcon;

    public IconeTarefa() {
        aparece();
    }

    public static void aparece() {
        if (!SystemTray.isSupported()) {
            Logger.getLogger(IconeTarefa.class.getName()).log(Level.WARNING, "System Tray nao suportada");
        } else {
            Image trayImage = new ImageIcon(IconeTarefa.class.getResource("resources/images/Pomarola16.png")).getImage();

            tray = SystemTray.getSystemTray();
            final PopupMenu menu = new PopupMenu();

            MenuItem time = new MenuItem("Tempo Restante");
            menu.add(time);
            MenuItem exit = new MenuItem("Encerrar");
            menu.addSeparator();
            menu.add(exit);

            time.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new TelaTempo(new JFrame(), false);
                    remover();
                }
            });

            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            trayIcon = new TrayIcon(trayImage, "Pomarola", menu);
            trayIcon.setImageAutoSize(true);
            Logger.getLogger(IconeTarefa.class.getName()).log(Level.INFO, "Iniciando menu na barra de tarefas");
            colocar();
        }

    }

    public static void colocar() {
        try {
            Logger.getLogger(IconeTarefa.class.getName()).log(Level.INFO, "Minimizando icone na barra de tarefas");
            tray.add(trayIcon);
        } catch (Exception ex) {
            Logger.getLogger(IconeTarefa.class.getName()).log(Level.SEVERE, "Erro ao adicionar icone na barra de tarefas", ex);
        }
    }

    public static void remover() {
        Logger.getLogger(IconeTarefa.class.getName()).log(Level.INFO, "Abrindo icone da barra de tarefas");
        tray.remove(trayIcon);
    }

    private static SystemTray tray;
}

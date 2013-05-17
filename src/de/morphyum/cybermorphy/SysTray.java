package de.morphyum.cybermorphy;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class SysTray {
 
 private SystemTray sysTray;
 private Image icon;
 private PopupMenu menu;
 private String tooltip;
 
 
 public SysTray() {
  //final GUI gui = gui; 
  icon =  new ImageIcon("cybertrayicon.jpg").getImage();
  
  menu = new PopupMenu("Menü");
  
  MenuItem closeItem = new MenuItem("Beenden");
  closeItem.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent evt) {
    System.exit(1);
   }
  });
    
  menu.add(closeItem);
  
  tooltip = "CyberMorphy";
  
  sysTray = SystemTray.getSystemTray();
  TrayIcon tray = new TrayIcon(icon, tooltip, menu);
  try {
   sysTray.add(tray);
  } catch (AWTException e) {
   e.printStackTrace();
  }
 }
}

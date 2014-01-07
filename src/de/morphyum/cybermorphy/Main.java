package de.morphyum.cybermorphy;

import org.pircbotx.PircBotX;

public class Main {
	public static void main(String[] args) throws Exception {
		SysTray tray = new SysTray();
		newBot("cybermorphy");
		newBot("morphyum");
		newBot("dethwing");
		newBot("artegaomega");
		newBot("truman");
		newBot("xpaco5");
		newBot("mimiheart9");
		
		srlIrcBot();
	}
	
	public static void srlIrcBot() throws Exception{
		PircBotX bot = new PircBotX();
		bot.getListenerManager().addListener(new CyberMorphy());
		bot.setName("cybermorphy");
		bot.setVerbose(true);
		bot.setAutoReconnect(true);
		bot.setAutoReconnectChannels(true);
		bot.connect("irc2.speedrunslive.com", 6667);
		Thread.sleep(1000);
		bot.joinChannel("#smwracers");
		Thread.sleep(1000);
	}
	
	public static void newBot(String channel) throws Exception{
		PircBotX bot = new PircBotX();
		bot.getListenerManager().addListener(new CyberMorphy());
		bot.setName("cybermorphy");
		bot.setVerbose(true);
		bot.setAutoReconnect(true);
		bot.setAutoReconnectChannels(true);

		bot.connect("irc.twitch.tv", 6667, "oauth:fbjpmnege3g0aw4ffv802rgkle1q9vo");
		Thread.sleep(1000);

		bot.joinChannel("#"+channel);
		Thread.sleep(1000);
	}
}

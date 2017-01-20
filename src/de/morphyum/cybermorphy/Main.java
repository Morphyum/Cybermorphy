package de.morphyum.cybermorphy;

import java.io.IOException;
import java.util.ArrayList;

import org.pircbotx.Channel;
import org.pircbotx.Configuration;
import org.pircbotx.MultiBotManager;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.EnableCapHandler;
import org.pircbotx.exception.IrcException;

public class Main {
	//static ArrayList<PircBotX> bots = new ArrayList<PircBotX>();
	static MultiBotManager bots = new MultiBotManager();
	
	public static void main(String[] args) throws Exception {
		SysTray tray = new SysTray();
		ArrayList<String> channels = HELPER.readChannels();
		for (int i = 0; i < channels.size(); i++) {
			if (isChannelWithoutCyber(channels.get(i)))
				bots.addBot(newBot(channels.get(i)));
		}
		bots.addBot(srlIrcBot());
		bots.start();
	}

	public static void announce(String message) throws InterruptedException {
		for (PircBotX bot : bots.getBots()) {
			ArrayList<Channel> channels = new ArrayList<Channel>();
			for (int h = 0; h < bot.getUserChannelDao().getAllChannels().size(); h++) {
				channels.add(bot.getUserChannelDao().getAllChannels().iterator().next());
			}
			for (int j = 0; j < channels.size(); j++) {
				channels.get(j).send().action(message);
				Thread.sleep(3000);
			}
		}
	}

	public static PircBotX srlIrcBot() {
		Configuration configuration = new Configuration.Builder()
			    .setName("cybermorphy") //Set the nick of the bot. CHANGE IN YOUR CODE
			    .addListener(new SrlCyberMorphy())
			    .addServer("irc2.speedrunslive.com")
			    .setAutoReconnect(true)
			    .addAutoJoinChannel("#smwracers") 
			    .buildConfiguration();
			PircBotX bot = new PircBotX(configuration);

		try {
			bot.startBot();
		} catch (Exception e) {
			
		}
		return bot;
	}

	public static boolean isChannelWithoutCyber(String channel) {
		boolean empty = true;
		for (PircBotX bot : bots.getBots()) {
			if (bot.getUserChannelDao().containsChannel("#" + channel)) {
				empty = false;
				System.out.println("Join error: Cyber already in: " + channel);
				break;
			}
		}
		return empty;
	}

	public static PircBotX newBot(String channel) {
		
		Configuration configuration = new Configuration.Builder()
				.setAutoNickChange(false) //Twitch doesn't support multiple users
			    .setOnJoinWhoEnabled(false) //Twitch doesn't support WHO command
			    .setCapEnabled(true)
			    .addCapHandler(new EnableCapHandler("twitch.tv/membership")) //Twitch by default doesn't send JOIN, PART, and NAMES unless you request it, see https://github.com/justintv/Twitch-API/blob/master/IRC.md#membership
			    
			    .setName("cybermorphy") //Set the nick of the bot. CHANGE IN YOUR CODE
			    .addListener(new CyberMorphy())
			    .addServer("irc.twitch.tv")
			    .setServerPassword("oauth:mv0lnmnwn9ihqi0twkcavnrhk3tv38")
			    .setAutoReconnect(true)
			    .addAutoJoinChannel("#" + channel) 
			    .buildConfiguration();
			PircBotX bot = new PircBotX(configuration);
			
		try {
			Thread.sleep(1000);
			HELPER.newChannel(channel);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return bot;
	}
}

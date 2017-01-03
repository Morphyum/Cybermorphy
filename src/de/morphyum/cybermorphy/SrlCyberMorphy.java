package de.morphyum.cybermorphy;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;

public class SrlCyberMorphy extends ListenerAdapter {

	public void onJoin(JoinEvent event) throws Exception {

	}

	public void onMessage(MessageEvent event) throws Exception {
		if ((event.getMessage().toLowerCase()).contains("!leaderboard")) {
			event.getChannel().send().action( "You can find the SMW leaderboard here: http://deanyd.net/smw/index.php?title=Leaderboards");
		}

		else if ((event.getMessage().toLowerCase()).contains("!categories")) {
			event.getChannel().send().action( HELPER.showSMWCats());
		}

		else if (event.getMessage().equalsIgnoreCase("!smwwiki")) {
			event.getChannel().send().action( "http://www.smwwiki.com");
		}

		else if ((event.getMessage().toLowerCase()).contains("!quote")) {
			event.getChannel().send().action( HELPER.randomQuote());
		}

		else if ((event.getMessage().toLowerCase()).contains("!wr")) {
			event.getChannel().send().action( HELPER.getWR(event.getMessage().toLowerCase().substring(4)));
		}

		else if ((event.getMessage().toLowerCase()).contains("http://www.youtube.com/watch?v=")
				|| (event.getMessage().toLowerCase()).contains("https://www.youtube.com/watch?v=")) {

			String[] texte = event.getMessage().split(" ");
			for (int i = 0; i < texte.length; i++) {
				if (texte[i].toLowerCase().contains("http://www.youtube.com/watch?v=") || texte[i].toLowerCase().contains("https://www.youtube.com/watch?v=")) {
					String link = texte[i];
					event.getChannel().send().action( HELPER.getYoutube(link));
				}
			}
		}

		else if ((event.getMessage().toLowerCase()).contains("!pb")) {
			String[] message = event.getMessage().toLowerCase().split(" ");
			String category = "";
			for (int i = 1; i < message.length - 1; i++) {
				if (i > 1)
					category = category + " " + message[i];
				else
					category = message[i];
			}
			event.getChannel().send().action( HELPER.getPB(category, message[message.length - 1]));

		} else if (event.getMessage().toLowerCase().contains("!srlstandings")) {
			if (event.getMessage().equalsIgnoreCase("!srlstandings")) {
				event.getChannel().send().action( HELPER.srlStandings());
				event.getChannel().send().action( "The Rest of the Leaderboard can be found here: http://speedrunslive.com/races/game/#!/smw/1");
			} else {
				event.getChannel().send().action( HELPER.srlStandingsSearch(event.getMessage().toLowerCase().substring(14)));
			}
		}

		Thread.sleep(3000);
	}

}
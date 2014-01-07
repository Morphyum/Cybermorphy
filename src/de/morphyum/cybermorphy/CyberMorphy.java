package de.morphyum.cybermorphy;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;

public class CyberMorphy extends ListenerAdapter {
	int soldiers = 0;
	int capes = 0;
	int bonks = 0;
	int orbsgot = 0;
	int orbsfailed = 0;
	boolean greetings = false;
	String advertisement = "No advertisement set yet, use !advertise set [MESSAGE] to set one";

	public void onJoin(JoinEvent event) throws Exception {
		if (event.getUser().getNick().contentEquals("cybermorphy")) {
			event.getBot().sendMessage(event.getChannel(), "Yay! I'm back, type !help to get to know me.");
		} else if (event.getUser().getNick().contentEquals("morphyum")) {
			event.getBot().sendMessage(event.getChannel(), "My Creator is back, good time to praise him or make requests :P");
		} else if (greetings) {
			event.getBot().sendMessage(event.getChannel(), "Hi " + event.getUser().getNick() + ", Welcome to the stream! <3");
		}
	}

	public void onMessage(MessageEvent event) throws Exception {
		if (event.getMessage().equalsIgnoreCase("!bestmanever")) {
			event.getBot().sendMessage(event.getChannel(), "Its Bloody the man of the man!");
		}

		else if ((event.getMessage().toLowerCase()).contains("!truman")) {
			event.getBot().sendMessage(event.getChannel(), "maple sitory");
		}
		
		else if ((event.getMessage().toLowerCase()).contains("!door")) {
			event.getBot().sendMessage(event.getChannel(), "Fuck you door! FinalBoss!!");
		}

		else if ((event.getMessage().toLowerCase()).contains("!categories")) {
			event.getBot().sendMessage(event.getChannel(), HELPER.showSMWCats());
		}

		else if ((event.getMessage().toLowerCase()).contains("!leaderboard")) {
			event.getBot().sendMessage(event.getChannel(), "You can find the SMW leaaderboard here: http://deanyd.net/smw/index.php?title=Leaderboards");
		}

		else if ((event.getMessage().toLowerCase()).contains("!booring")) {
			event.getBot().sendMessage(event.getChannel(), " \"wow, nice boo ring shitbag\" xpaco5 (2013)");
		}

		else if ((event.getMessage().toLowerCase()).contains("teh urn")) {
			event.getBot().sendMessage(event.getChannel(), "It's TEH URN!!");
		}

		else if ((event.getMessage().toLowerCase()).contains("what is this")) {
			event.getBot().sendMessage(event.getChannel(), "That's called a Stream, open your eyes? o.O");
		}

		else if ((event.getMessage().toLowerCase()).contains("what it is?")) {
			event.getBot().sendMessage(event.getChannel(), "That's what it do!");
		}

		else if (event.getMessage().equalsIgnoreCase("!smwwiki")) {
			event.getBot().sendMessage(event.getChannel(), "http://www.smwwiki.com");
		}

		else if (event.getMessage().equalsIgnoreCase("!japanese")) {
			event.getBot().sendMessage(event.getChannel(),
					"The japanese version has less signs for the text, which makes it 20.8 seconds faster over the whole run.");
		}

		else if (event.getMessage().equalsIgnoreCase("!wingdupe")) {
			event.getBot().sendMessage(event.getChannel(),
					"Blocks can be duplicated to the side, upwards, or upwards-diagonally by throwing a item at it while Mario is close to it.");
			Thread.sleep(3000);
			event.getBot()
					.sendMessage(
							event.getChannel(),
							"When turn blocks are duplicated over half of a dragon coin and Mario collects the remaining half, the duplicated turn block will change into a key/wings/balloon/shell block based on horizontal position. And the Wings end the Level if collected with Yoshi!");
		}

		else if ((event.getMessage().toLowerCase()).contains("where am i")) {
			event.getBot().sendMessage(event.getChannel(), "In a chat, ofcourse.");
		}

		else if (event.getMessage().equalsIgnoreCase("!yoshi")) {
			event.getBot().sendMessage(event.getChannel(), "A Moment of Silence please for yoshi, that died to make this run possible!");
		}

		else if ((event.getMessage().toLowerCase()).contains("!wr")) {
			event.getBot().sendMessage(event.getChannel(), HELPER.getWR(event.getMessage().toLowerCase().substring(4)));
		}

		else if ((event.getMessage().toLowerCase()).contains("http://www.youtube.com/watch?v=")
				|| (event.getMessage().toLowerCase()).contains("https://www.youtube.com/watch?v=")) {

			String[] texte = event.getMessage().split(" ");
			for (int i = 0; i < texte.length; i++) {
				if (texte[i].toLowerCase().contains("http://www.youtube.com/watch?v=") || texte[i].toLowerCase().contains("https://www.youtube.com/watch?v=")) {
					System.out.println(texte[i]);
					String link = texte[i];
					event.getBot().sendMessage(event.getChannel(), HELPER.getYoutube(link));
				}
			}
		}

		else if ((event.getMessage().toLowerCase()).contains("!goto") && event.getUser().getNick().equalsIgnoreCase("morphyum")) {
			String[] message = event.getMessage().toLowerCase().split(" ");
			String channel = message[1];
			Main.newBot(channel);
		}

		else if ((event.getMessage().toLowerCase()).contains("!announce") && event.getUser().getNick().equalsIgnoreCase("morphyum")) {
			String[] message = event.getMessage().toLowerCase().split(" ");
			String channel = message[1];
			Main.announce(message[1]);
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
			event.getBot().sendMessage(event.getChannel(), HELPER.getPB(category, message[message.length - 1]));
		}

		else if (event.getMessage().equalsIgnoreCase("!king")) {
			event.getBot().sendMessage(event.getChannel(), "Oh, the King has been transformed ! Please find the Magic Wand so we can change him back.");
		}

		else if ((event.getMessage().toLowerCase()).contains("!cape")) {
			capes(event);
		}

		else if ((event.getMessage().toLowerCase()).contains("!soldier")) {
			soldiers(event);
		}

		else if ((event.getMessage().toLowerCase()).contains("!bonk")) {
			bonks(event);
		}

		else if (event.getMessage().equalsIgnoreCase("!race")) {
			event.getBot().sendMessage(event.getChannel(), "To see all contestent of a race go to http://speedrunslive.com/races/ !");
		}

		else if (event.getMessage().equalsIgnoreCase("!help")) {
			event.getBot().sendMessage(event.getChannel(), "http://pastebin.com/9LxubXzA");

		} else if (event.getMessage().toLowerCase().contains("!srlstandings")) {
			if (event.getMessage().equalsIgnoreCase("!srlstandings")) {
				HELPER.srlStandings(event);
				event.getBot().sendMessage(event.getChannel(), "The Rest of the Leaderboard can be found here: http://speedrunslive.com/races/game/#!/smw/1");
			} else {
				HELPER.srlStandingsSearch(event, event.getMessage().toLowerCase().substring(14));
			}
		}

		else if (event.getMessage().equalsIgnoreCase("!join")) {
			Main.newBot(event.getUser().getNick());
		}

		else if (event.getMessage().toLowerCase().contains("!advertise")) {
			if (event.getMessage().equalsIgnoreCase("!advertise")) {
				event.getBot().sendMessage(event.getChannel(), advertisement);
			} else if(event.getMessage().toLowerCase().contains("!advertise set")) {
				advertisement = event.getMessage().substring(15);
			}
		}

		else if (event.getMessage().toLowerCase().contains("!orb")) {
			if (event.getMessage().equalsIgnoreCase("!orb")) {
				event.getBot().sendMessage(event.getChannel(), "OrbOrbOrbOrbOrb!");
			} else {
				orbcount(event);
			}
		}

		else if (event.getMessage().toLowerCase().contains("!greet")) {
			greeting(event);
		}

		else if (event.getMessage().toLowerCase().contains("!leave")) {
			if (event.getChannel().isOp(event.getUser()) || event.getUser().getNick().equalsIgnoreCase("morphyum")) {
				event.getBot().sendMessage(event.getChannel(), "Fine i leave :(");
				Thread.sleep(3000);
				event.getBot().shutdown(true);
			}
		}
		Thread.sleep(3000);
	}

	private void greeting(MessageEvent event) {

		if (event.getChannel().isOp(event.getUser())) {
			if (event.getMessage().equalsIgnoreCase("!greet on")) {
				greetings = true;
				event.getBot().sendMessage(event.getChannel(), "Greeting activated");
			} else if (event.getMessage().equalsIgnoreCase("!greet off")) {
				greetings = false;
				event.getBot().sendMessage(event.getChannel(), "Greeting deactivated");
			}
		}

	}

	private void capes(MessageEvent event) {
		if (event.getChannel().isOp(event.getUser())) {
			if (event.getMessage().equalsIgnoreCase("!capes reset")) {
				this.capes = 0;
				event.getBot().sendMessage(event.getChannel(), "Cape Counter reset!");
			} else if (event.getMessage().equalsIgnoreCase("!capes +")) {
				capes++;
				event.getBot().sendMessage(event.getChannel(), event.getChannel().getName().substring(1) + " lost " + capes + " Capes in this Run!");
			}

			else if (event.getMessage().equalsIgnoreCase("!capes -")) {
				if (capes != 0) {
					capes--;
					event.getBot().sendMessage(event.getChannel(), event.getChannel().getName().substring(1) + " lost " + capes + " Capes in this Run!");
				} else {
					event.getBot().sendMessage(event.getChannel(), "-1 Capes? that doesnt make any sense, lets stop at 0 Kappa");
				}
			} else {
				event.getBot().sendMessage(event.getChannel(),
						event.getChannel().getName().substring(1) + " lost " + capes + " Capes in this Run! To increase or decrease number type !capes [+/-]");
			}
		} else {
			event.getBot().sendMessage(event.getChannel(), event.getChannel().getName().substring(1) + " lost " + capes + " Capes in this Run!");
		}
	}

	private void soldiers(MessageEvent event) {
		if (event.getChannel().isOp(event.getUser())) {
			if (event.getMessage().equalsIgnoreCase("!soldiers reset")) {
				this.soldiers = 0;
				event.getBot().sendMessage(event.getChannel(), "Soldier Counter reset!");
			} else if (event.getMessage().equalsIgnoreCase("!soldiers +")) {
				soldiers++;
				event.getBot().sendMessage(event.getChannel(), event.getChannel().getName().substring(1) + " lost " + soldiers + " Soldiers in this Run!");
			}

			else if (event.getMessage().equalsIgnoreCase("!soldiers -")) {
				if (soldiers != 0) {
					soldiers--;
					event.getBot().sendMessage(event.getChannel(), event.getChannel().getName().substring(1) + " lost " + soldiers + " Soldiers in this Run!");
				} else {
					event.getBot().sendMessage(event.getChannel(), "-1 soldiers? that doesnt make any sense, lets stop at 0 Kappa");
				}
			} else {
				event.getBot().sendMessage(event.getChannel(),
						event.getChannel().getName().substring(1) + " lost " + soldiers + " Soldiers in this Run! To increase or decrease number type !soldiers [+/-]");
			}
		} else {
			event.getBot().sendMessage(event.getChannel(), event.getChannel().getName().substring(1) + " lost " + soldiers + " Soldiers in this Run!");
		}
	}

	private void bonks(MessageEvent event) {
		if (event.getChannel().isOp(event.getUser())) {
			if (event.getMessage().equalsIgnoreCase("!bonks reset")) {
				this.bonks = 0;
				event.getBot().sendMessage(event.getChannel(), "BONK Counter reset!");
			} else if (event.getMessage().equalsIgnoreCase("!bonks +")) {
				bonks++;
				event.getBot().sendMessage(event.getChannel(), event.getChannel().getName().substring(1) + " BONKed " + bonks + " times in this Run!");
			}

			else if (event.getMessage().equalsIgnoreCase("!bonks -")) {
				if (bonks != 0) {
					bonks--;
					event.getBot().sendMessage(event.getChannel(), event.getChannel().getName().substring(1) + " BONKed " + bonks + " times in this Run!");
				} else {
					event.getBot().sendMessage(event.getChannel(), "-1 BONKs? that doesnt make any sense, lets stop at 0 Kappa");
				}
			} else {
				event.getBot().sendMessage(event.getChannel(),
						event.getChannel().getName().substring(1) + " BONKed " + bonks + " times in this Run! To increase or decrease number type !bonks [+/-]");
			}
		} else {
			event.getBot().sendMessage(event.getChannel(), event.getChannel().getName().substring(1) + " BONKed " + bonks + " times in this Run!");
		}
	}

	private void orbcount(MessageEvent event) {
		if (event.getMessage().equalsIgnoreCase("!orbcount")) {
			event.getBot().sendMessage(event.getChannel(),
					event.getChannel().getName().substring(1) + " got " + orbsgot + " Orbs this session and failed it " + orbsfailed + " times!");
		}
		if (event.getChannel().isOp(event.getUser())) {
			if (event.getMessage().equalsIgnoreCase("!orbreset")) {
				this.orbsfailed = 0;
				this.orbsgot = 0;
				event.getBot().sendMessage(event.getChannel(), "Orb Counter Counter reset!");
			} else if (event.getMessage().equalsIgnoreCase("!orbgot")) {
				orbsgot++;
				event.getBot().sendMessage(event.getChannel(),
						event.getChannel().getName().substring(1) + " got " + orbsgot + " Orbs this session and failed it " + orbsfailed + " times!");
			} else if (event.getMessage().equalsIgnoreCase("!orbfailed")) {
				orbsfailed++;
				event.getBot().sendMessage(event.getChannel(),
						event.getChannel().getName().substring(1) + " got " + orbsgot + " Orbs this session and failed it " + orbsfailed + " times!");

			} else {
				event.getBot().sendMessage(
						event.getChannel(),
						event.getChannel().getName().substring(1) + " got " + orbsgot + " Orbs this session and failed it " + orbsfailed
								+ " times! To increase it use [!orbfailed/!orbgot] to reset it use [!orbreset]");
			}
		} else {
			event.getBot().sendMessage(event.getChannel(),
					event.getChannel().getName().substring(1) + " got " + orbsgot + " Orbs this session and failed it " + orbsfailed + " times!");
		}
	}

	

	

	

	

}

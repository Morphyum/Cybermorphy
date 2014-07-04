package de.morphyum.cybermorphy;

import java.util.ArrayList;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;

public class CyberMorphy extends ListenerAdapter {
	int soldiers = 0;
	int capes = 0;
	int death = 0;
	int bonks = 0;
	int cloudsgot = 0;
	int cloudsfailed = 0;
	boolean greetings = false;
	String streamerName = "nonamesetyet";
	String advertisement = "No advertisement set yet, use !advertise set [MESSAGE] to set one";
	String greeting = "Hi (NICK), Welcome to the stream! <3";
	String welcomeBack = "Welcome back (NICK)! <3";
	String mhroom = "Join us playing on \"Free World 03\", \"Lobby 3\", Room \"Cesslehunt\". Password is \"lol\". Happy hunting ;-)";
	String version = "3.11.3";
	ArrayList<String> viewers = new ArrayList<String>();
	ArrayList<Command> commands = new ArrayList<Command>();

	public void onJoin(JoinEvent event) throws Exception {
		if (event.getUser().getNick().contentEquals("cybermorphy")) {
			event.getBot().sendMessage(event.getChannel(), "Yay! I'm back, type !help to get to know me.");
			if (streamerName.contentEquals("nonamesetyet")) {
				streamerName = event.getChannel().getName().substring(1);
			}

		} else if (event.getUser().getNick().contentEquals("morphyum")) {
			event.getBot().sendMessage(event.getChannel(), "My Creator is back, good time to praise him or make requests :P");

		} else if (greetings) {
			if (viewers.contains(event.getUser().getNick())) {
				event.getBot().sendMessage(event.getChannel(), welcomeBack.replace("(NICK)", event.getUser().getNick()));
			} else {
				event.getBot().sendMessage(event.getChannel(), greeting.replace("(NICK)", event.getUser().getNick()));
				viewers.add(event.getUser().getNick());
				HELPER.newViewer(event.getChannel().getName(), event.getUser().getNick());
			}
		}
	}

	public void onMessage(MessageEvent event) throws Exception {
		if (event.getMessage().equalsIgnoreCase("!bestmanever")) {
			event.getBot().sendMessage(event.getChannel(), "Its Bloody the man of the man!");

		} else if (((event.getMessage().toLowerCase()).contains("!setname")) && (event.getChannel().isOp(event.getUser()))) {
			streamerName = event.getMessage().substring(9);
			event.getBot().sendMessage(event.getChannel(), "Ok i will call you " + streamerName + " from now on :)");
			HELPER.saveAllSettings(event.getChannel().getName(), this);
		}

		else if (((event.getMessage().toLowerCase()).equalsIgnoreCase("!goldenwho?")) || ((event.getMessage().toLowerCase()).contains("who is golden"))
				|| ((event.getMessage().toLowerCase()).contains("who is go1den"))) {
			event.getBot().sendMessage(event.getChannel(), "That's Golden: http://imgur.com/JsdLAXc");
		}

		else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!anna")) {
			event.getBot().sendMessage(event.getChannel(), "Anna can be found here: http://www.project610.com/anna");
		}

		else if ((event.getMessage().toLowerCase()).contains("!mhroom")) {
			event.getBot().sendMessage(event.getChannel(), getMhroom(event));
		}

		else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!quote")) {
			event.getBot().sendMessage(event.getChannel(), HELPER.randomQuote());
		}

		else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!casaflip")) {
			event.getBot().sendMessage(event.getChannel(),
					"\u0028" + "\uFF89" + "\u0CA5" + "\u76CA" + "\u0CA5" + "\uFF09" + "\uFF89" + " " + "\u253B" + "\u2501" + "\u253B");

		}

		else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!truman")) {
			event.getBot().sendMessage(event.getChannel(), "SMW world champion crowned at MLG Raleigh 1999, loves maple sitory");
		}

		else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!dano")) {
			event.getBot().sendMessage(event.getChannel(), "god, dano");
		}

		else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!door")) {
			event.getBot().sendMessage(event.getChannel(), "Fuck you door! FinalBoss!!");
		}

		else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!categories")) {
			event.getBot().sendMessage(event.getChannel(), HELPER.showSMWCats());
		}

		else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!leaderboard")) {
			event.getBot().sendMessage(event.getChannel(), "You can find the SMW leaderboard here: http://deanyd.net/smw/index.php?title=Leaderboards");
		}

		else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!booring")) {
			event.getBot().sendMessage(event.getChannel(), " \"wow, nice boo ring shitbag\" xpaco5 (2013)");
		}

		else if ((event.getMessage().toLowerCase()).contains("teh urn")) {
			event.getBot().sendMessage(event.getChannel(), "It's tEh URN!!");
		}

		else if ((event.getMessage().toLowerCase()).contains("potato")) {
			event.getBot().sendMessage(event.getChannel(), "POTATO! :3 <3");
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

		else if ((event.getMessage().toLowerCase()).contains("!osu")) {
			event.getBot().sendMessage(event.getChannel(), HELPER.getOsuStats(event.getMessage().toLowerCase().substring(5)));
		}

		else if ((event.getMessage().toLowerCase()).contains("http://www.youtube.com/watch?v=")
				|| (event.getMessage().toLowerCase()).contains("https://www.youtube.com/watch?v=")) {

			String[] texte = event.getMessage().split(" ");
			for (int i = 0; i < texte.length; i++) {
				if (texte[i].toLowerCase().contains("http://www.youtube.com/watch?v=") || texte[i].toLowerCase().contains("https://www.youtube.com/watch?v=")) {
					String link = texte[i];
					event.getBot().sendMessage(event.getChannel(), HELPER.getYoutube(link));
				}
			}
		}

		else if ((event.getMessage().toLowerCase()).contains("!goto") && event.getUser().getNick().equalsIgnoreCase("morphyum")) {
			String[] message = event.getMessage().toLowerCase().split(" ");
			String channel = message[1];
			if (Main.isChannelWithoutCyber(channel))
				Main.newBot(channel);
		}

		else if ((event.getMessage().toLowerCase()).contains("!announce") && event.getUser().getNick().equalsIgnoreCase("morphyum")) {
			String message = event.getMessage().substring(10);
			Main.announce(message);
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
			event.getBot().sendMessage(event.getChannel(), capes(event));
		}

		else if ((event.getMessage().toLowerCase()).contains("!death")) {
			event.getBot().sendMessage(event.getChannel(), death(event));
		}

		else if ((event.getMessage().toLowerCase()).contains("!soldier")) {
			event.getBot().sendMessage(event.getChannel(), soldiers(event));
		}

		else if ((event.getMessage().toLowerCase()).contains("!bonk")) {
			event.getBot().sendMessage(event.getChannel(), bonks(event));
		}

		else if (event.getMessage().equalsIgnoreCase("!race")) {
			event.getBot().sendMessage(event.getChannel(), "To see all contestent of a race go to http://speedrunslive.com/races/ !");
		}

		else if (event.getMessage().equalsIgnoreCase("!help")) {
			event.getBot().sendMessage(event.getChannel(),
					"This is version " + version + " of CyberMorphy if you dont know this version yet check http://www.twitch.tv/cybermorphy for new commands");

		} else if (event.getMessage().toLowerCase().contains("!srlstandings")) {
			if (event.getMessage().equalsIgnoreCase("!srlstandings")) {
				event.getBot().sendMessage(event.getChannel(), HELPER.srlStandings());
				event.getBot().sendMessage(event.getChannel(), "The Rest of the Leaderboard can be found here: http://speedrunslive.com/races/game/#!/smw/1");
			} else {
				event.getBot().sendMessage(event.getChannel(), HELPER.srlStandingsSearch(event.getMessage().toLowerCase().substring(14)));
			}
		}

		else if (event.getMessage().equalsIgnoreCase("!join")) {
			if (Main.isChannelWithoutCyber(event.getUser().getNick())) {
				Main.bots.add(Main.newBot(event.getUser().getNick()));
				event.getBot().sendMessage(event.getChannel(), "I Cloned myself to " + event.getUser().getNick());
			}
		}

		else if (event.getMessage().toLowerCase().contains("!advertise")) {
			if (event.getMessage().equalsIgnoreCase("!advertise")) {
				event.getBot().sendMessage(event.getChannel(), advertisement);
			} else if ((event.getMessage().toLowerCase().contains("!advertise set")) && (event.getChannel().isOp(event.getUser()))) {
				advertisement = event.getMessage().substring(15);
				HELPER.srlStandingsSearch("advertisement set to: " + advertisement);
				HELPER.saveAllSettings(event.getChannel().getName(), this);
			}
		}

		else if (event.getMessage().equalsIgnoreCase("!orb")) {
			event.getBot().sendMessage(event.getChannel(), "OrbOrbOrbOrbOrb! http://deanyd.net/smw/index.php?title=Orb");
		}

		else if (event.getMessage().toLowerCase().contains("!cloud")) {
			if (event.getMessage().equalsIgnoreCase("!cloud")) {
				event.getBot().sendMessage(event.getChannel(), "http://deanyd.net/smw/index.php?title=Cloud");
			} else {
				event.getBot().sendMessage(event.getChannel(), cloudcount(event));
			}
		}

		else if (event.getMessage().toLowerCase().contains("!greet")) {
			event.getBot().sendMessage(event.getChannel(), greeting(event));
		}

		else if (((event.getMessage().toLowerCase()).contains("!request")) && (event.getChannel().isOp(event.getUser()))) {
			HELPER.sendMail(event.getUser().getNick() + ": " + event.getMessage().substring(9));
			event.getBot().sendMessage(event.getChannel(), "Thanks for your request!");
		}

		else if (event.getMessage().toLowerCase().equalsIgnoreCase("!leave")) {
			if (event.getChannel().isOp(event.getUser()) || event.getUser().getNick().equalsIgnoreCase("morphyum")) {
				event.getBot().sendMessage(event.getChannel(), "Fine i leave BibleThump");
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				HELPER.deleteChannel(event.getChannel().getName());
				Thread.sleep(3000);
				event.getBot().shutdown(true);

			}
		} else if (event.getMessage().toLowerCase().equalsIgnoreCase("!commands")) {
			String commandlist = "";
			for (int i = 0; i < commands.size(); i++) {
				commandlist += commands.get(i).getHead() + " | ";
			}
			event.getBot().sendMessage(event.getChannel(), commandlist);

		} else if (event.getMessage().toLowerCase().contains("-!")) {
			if (event.getChannel().isOp(event.getUser())) {
				String command = event.getMessage().replace("-", "");
				for (int i = 0; i < commands.size(); i++) {
					if (commands.get(i).getHead().contentEquals(command)) {
						commands.remove(i);
						HELPER.deleteCommand(command, event.getChannel().getName());
						event.getBot().sendMessage(event.getChannel(), "Command " + command + " deleted");
						break;
					}

				}
			}
		} else if (event.getMessage().toLowerCase().contains("+!")) {
			if (event.getChannel().isOp(event.getUser())) {
				String[] commandparts = event.getMessage().split(" ");
				String body = "";
				for (int i = 1; i < commandparts.length; i++) {
					body += commandparts[i] + " ";
				}
				if (checkPrivateCommands(commandparts[0].replace("+", "")).contentEquals("")) {
					commands.add(new Command(commandparts[0].replace("+", ""), body));
					HELPER.addCommand(event.getMessage().substring(1), event.getChannel().getName());
					event.getBot().sendMessage(event.getChannel(), "Command " + commandparts[0].replace("+", "") + " added");
				} else {
					event.getBot().sendMessage(event.getChannel(), "Command " + commandparts[0].replace("+", "") + " is already on the Commandlist");
				}
			}

		} else {
			String command = checkPrivateCommands(event.getMessage());
			if (!command.contentEquals("")) {
				event.getBot().sendMessage(event.getChannel(), command);
			}
		}

		Thread.sleep(3000);
	}

	private String checkPrivateCommands(String message) {
		System.out.println(message);
		for (int i = 0; i < commands.size(); i++) {
			if (message.toLowerCase().equalsIgnoreCase(commands.get(i).getHead())) {
				return commands.get(i).getBody();
			}
		}
		return "";
	}

	private String getMhroom(MessageEvent event) {
		if (event.getMessage().contains("!mhroom set")) {
			mhroom = event.getMessage().substring(12);
			HELPER.saveAllSettings(event.getChannel().getName(), this);
			return "New room will be: " + mhroom;
		} else {
			return mhroom;
		}
	}

	private String greeting(MessageEvent event) {

		if (event.getChannel().isOp(event.getUser())) {
			if (event.getMessage().equalsIgnoreCase("!greet on")) {
				greetings = true;
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return "Greeting activated";
			} else if (event.getMessage().equalsIgnoreCase("!greet off")) {
				greetings = false;
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return "Greeting deactivated";
			} else if (event.getMessage().contains("!greet set")) {
				greeting = event.getMessage().substring(11);
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return "New greeting will be: " + greeting;

			} else if (event.getMessage().contains("!greet welcome")) {
				welcomeBack = event.getMessage().substring(15);
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return "New welcome back message will be: " + welcomeBack;
			} else {
				return "Wrong Syntax please use !greet [ on/off/set [MESSAGE]/welcome [MESSAGE] ]";
			}
		} else {
			return "Only Mods can (de)activate or set greetings Kappa";
		}
	}

	private String capes(MessageEvent event) {
		if (event.getChannel().isOp(event.getUser())) {
			if (event.getMessage().equalsIgnoreCase("!capes reset")) {
				this.capes = 0;
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return "Cape counter reset!";
			} else if (event.getMessage().equalsIgnoreCase("!capes +")) {
				capes++;
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return streamerName + " lost " + capes + " Capes in this Run!";
			} else if (event.getMessage().equalsIgnoreCase("!capes -")) {
				if (capes != 0) {
					capes--;
					HELPER.saveAllSettings(event.getChannel().getName(), this);
					return streamerName + " lost " + capes + " Capes in this Run!";
				} else {
					return "-1 Capes? that doesnt make any sense, lets stop at 0 Kappa";
				}
			} else {
				return streamerName + " lost " + capes + " Capes in this Run! To increase or decrease number type !capes [+/-]";
			}
		} else {
			return streamerName + " lost " + capes + " Capes in this Run!";
		}
	}

	private String death(MessageEvent event) {
		if (event.getChannel().isOp(event.getUser())) {
			if (event.getMessage().equalsIgnoreCase("!death reset")) {
				this.death = 0;
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return "Death counter reset!";
			} else if (event.getMessage().equalsIgnoreCase("!death +")) {
				death++;
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return streamerName + " died " + death + " times till now!";
			} else if (event.getMessage().equalsIgnoreCase("!death -")) {
				if (death != 0) {
					death--;
					HELPER.saveAllSettings(event.getChannel().getName(), this);
					return streamerName + " died " + death + " times till now!";
				} else {
					return "-1 deaths? that doesnt make any sense, lets stop at 0 Kappa";
				}
			} else {
				return streamerName + " died " + death + " times till now! To increase or decrease number type !death [+/-]";
			}
		} else {
			return streamerName + " died " + death + " times till now!";
		}
	}

	private String soldiers(MessageEvent event) {
		if (event.getChannel().isOp(event.getUser())) {
			if (event.getMessage().equalsIgnoreCase("!soldiers reset")) {
				this.soldiers = 0;
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return "Soldier Counter reset!";
			} else if (event.getMessage().equalsIgnoreCase("!soldiers +")) {
				soldiers++;
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return streamerName + " lost " + soldiers + " Soldiers in this Run!";
			} else if (event.getMessage().equalsIgnoreCase("!soldiers -")) {
				if (soldiers != 0) {
					soldiers--;
					HELPER.saveAllSettings(event.getChannel().getName(), this);
					return streamerName + " lost " + soldiers + " Soldiers in this Run!";
				} else {
					return "-1 soldiers? that doesnt make any sense, lets stop at 0 Kappa";
				}
			} else {
				return streamerName + " lost " + soldiers + " Soldiers in this Run! To increase or decrease number type !soldiers [+/-]";
			}
		} else {
			return streamerName + " lost " + soldiers + " Soldiers in this Run!";
		}
	}

	private String bonks(MessageEvent event) {
		if (event.getChannel().isOp(event.getUser())) {
			if (event.getMessage().equalsIgnoreCase("!bonks reset")) {
				this.bonks = 0;
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return "BONK Counter reset!";
			} else if (event.getMessage().equalsIgnoreCase("!bonks +")) {
				bonks++;
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return streamerName + " BONKed " + bonks + " times in this Run!";
			}

			else if (event.getMessage().equalsIgnoreCase("!bonks -")) {
				if (bonks != 0) {
					bonks--;
					HELPER.saveAllSettings(event.getChannel().getName(), this);
					return streamerName + " BONKed " + bonks + " times in this Run!";
				} else {
					return "-1 BONKs? that doesnt make any sense, lets stop at 0 Kappa";
				}
			} else {
				return " BONKed " + bonks + " times in this Run! To increase or decrease number type !bonks [+/-]";
			}
		} else {
			return streamerName + " BONKed " + bonks + " times in this Run!";
		}
	}

	private String cloudcount(MessageEvent event) {
		if (event.getMessage().equalsIgnoreCase("!cloudcount")) {
			return streamerName + " got " + cloudsgot + " clouds this session and failed it " + cloudsfailed + " times!";
		}
		if (event.getChannel().isOp(event.getUser())) {
			if (event.getMessage().equalsIgnoreCase("!cloudreset")) {
				this.cloudsfailed = 0;
				this.cloudsgot = 0;
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return "Cloudcounter reset!";
			} else if (event.getMessage().equalsIgnoreCase("!cloudgot")) {
				cloudsgot++;
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return streamerName + " got " + cloudsgot + " clouds this session and failed it " + cloudsfailed + " times!";
			} else if (event.getMessage().equalsIgnoreCase("!cloudfailed")) {
				cloudsfailed++;
				HELPER.saveAllSettings(event.getChannel().getName(), this);
				return streamerName + " got " + cloudsgot + " clouds this session and failed it " + cloudsfailed + " times!";

			} else {
				return streamerName + " got " + cloudsgot + " clouds this session and failed it " + cloudsfailed
						+ " times! To increase it use [!cloudfailed/!cloudgot] to reset it use [!cloudreset]";
			}
		} else {
			return streamerName + " got " + cloudsgot + " clouds this session and failed it " + cloudsfailed + " times!";
		}
	}
}

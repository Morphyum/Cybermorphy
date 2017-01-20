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
	boolean modonly = false;
	String streamerName = "nonamesetyet";
	String advertisement = "No advertisement set yet, use !advertise set [MESSAGE] to set one";
	String greeting = "Hi (NICK), Welcome to the stream! <3";
	String welcomeBack = "Welcome back (NICK)! <3";
	String mhroom = "Join us playing on \"Free World 03\", \"Lobby 3\", Room \"Cesslehunt\". Password is \"lol\". Happy hunting ;-)";
	String version = "4.0-SNAPSHOT";
	ArrayList<String> viewers = new ArrayList<String>();
	ArrayList<Command> commands = new ArrayList<Command>();
	ArrayList<Command> modcommands = new ArrayList<Command>();;

	public void onJoin(JoinEvent event) throws Exception {
		if (event.getChannel().getUsers().size() >= 100 && modonly == false) {
			modonly = true;
			event.getChannel().send().action("Cybermorphy commands set to mod only, to stop potential abuse in High Viewer Streams.");
		}
		if (event.getChannel().getUsers().size() < 100 && modonly == true) {
			modonly = false;
			event.getChannel().send().action("Cybermorphy commands set to all, enjoy.");
		}
		if (event.getUser().getNick().contentEquals("cybermorphy")) {
			event.getChannel().send().action("Yay! I'm back, type !help to get to know me.");
			if (streamerName.contentEquals("nonamesetyet")) {
				streamerName = event.getChannel().getName().substring(1);
			}

		} else if (event.getUser().getNick().contentEquals("morphyum")) {
			event.getChannel().send().action("My Creator is back, good time to praise him or make requests :P");

		} else if (greetings) {
			if (viewers.contains(event.getUser().getNick())) {
				event.getChannel().send().action(welcomeBack.replace("(NICK)", event.getUser().getNick()));
			} else {
				event.getChannel().send().action(greeting.replace("(NICK)", event.getUser().getNick()));
				viewers.add(event.getUser().getNick());
				HELPER.newViewer(event.getChannel().getName(), event.getUser().getNick());
			}
		}
	}

	public void onMessage(MessageEvent event) throws Exception {
		if (modonly == false || event.getChannel().isOp(event.getUser())) {
			if (event.getMessage().equalsIgnoreCase("!bestmanever")) {
				event.getChannel().send().action("Its Bloody the man of the man!");

			} else if (((event.getMessage().toLowerCase()).startsWith("!setname")) && (event.getChannel().isOp(event.getUser()))) {
				streamerName = event.getMessage().substring(9);
				event.getChannel().send().action("Ok i will call you " + streamerName + " from now on :)");
				HELPER.saveAllSettings(event.getChannel().getName(), this);
			}

			else if (((event.getMessage().toLowerCase()).equalsIgnoreCase("!goldenwho?")) || ((event.getMessage().toLowerCase()).contains("who is golden"))
					|| ((event.getMessage().toLowerCase()).contains("who is go1den"))) {
				event.getChannel().send().action("That's Golden: http://imgur.com/JsdLAXc");
			}

			else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!anna")) {
				event.getChannel().send().action("Anna can be found here: http://www.project610.com/anna");
			}

			else if ((event.getMessage().toLowerCase()).startsWith("!mhroom")) {
				event.getChannel().send().action(getMhroom(event));
			}

			else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!quote")) {
				event.getChannel().send().action(HELPER.randomQuote());
			}

			else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!casaflip")) {
				event.getChannel().send().action("\u0028" + "\uFF89" + "\u0CA5" + "\u76CA" + "\u0CA5" + "\uFF09" + "\uFF89" + " " + "\u253B" + "\u2501" + "\u253B");

			}

			else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!truman")) {
				event.getChannel().send().action("SMW world champion crowned at MLG Raleigh 1999, loves maple sitory");
			}

			else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!dano")) {
				event.getChannel().send().action("god, dano");
			}

			else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!door")) {
				event.getChannel().send().action("Fuck you door! FinalBoss!!");
			}

			else if ((event.getMessage().toLowerCase()).startsWith("!categories")) {
				event.getChannel().send().action(HELPER.showCategories(event.getMessage().toLowerCase().substring(12)));
			}

			else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!leaderboard")) {
				event.getChannel().send().action("You can find the SMW leaderboard here: http://www.speedrun.com/smw");
			}

			else if ((event.getMessage().toLowerCase()).equalsIgnoreCase("!booring")) {
				event.getChannel().send().action(" \"wow, nice boo ring shitbag\" xpaco5 (2013)");
			}

			else if ((event.getMessage().toLowerCase()).contains("teh urn")) {
				event.getChannel().send().action("It's tEh URN!!");
			}

			else if ((event.getMessage().toLowerCase()).contains("potato")) {
				event.getChannel().send().action("POTATO! :3 <3");
			}

			else if ((event.getMessage().toLowerCase()).contains("what is this")) {
				event.getChannel().send().action("That's called a Stream, open your eyes? o.O");
			}

			else if ((event.getMessage().toLowerCase()).contains("what it is?")) {
				event.getChannel().send().action("That's what it do!");
			}

			else if (event.getMessage().equalsIgnoreCase("!smwwiki")) {
				event.getChannel().send().action("http://www.smwwiki.com");
			}

			else if (event.getMessage().equalsIgnoreCase("!japanese")) {
				event.getChannel().send().action("The japanese version has less signs for the text, which makes it 20.8 seconds faster over the whole run.");
			}

			else if (event.getMessage().equalsIgnoreCase("!wingdupe")) {
				event.getChannel().send().action("Blocks can be duplicated to the side, upwards, or upwards-diagonally by throwing a item at it while Mario is close to it.");
				Thread.sleep(3000);
				event.getChannel().send().action(
						"When turn blocks are duplicated over half of a dragon coin and Mario collects the remaining half, the duplicated turn block will change into a key/wings/balloon/shell block based on horizontal position. And the Wings end the Level if collected with Yoshi!");
			}

			else if ((event.getMessage().toLowerCase()).contains("where am i")) {
				event.getChannel().send().action("In a chat, ofcourse.");
			}

			else if (event.getMessage().equalsIgnoreCase("!yoshi")) {
				event.getChannel().send().action("A Moment of Silence please for yoshi, that died to make this run possible!");
			}

			else if ((event.getMessage().toLowerCase()).startsWith("!wr")) {
				String[] message = event.getMessage().toLowerCase().split(" ");
				event.getChannel().send().action(HELPER.getWR(message[message.length - 2], message[message.length - 1]));
			}

			else if ((event.getMessage().toLowerCase()).startsWith("!osu")) {
				event.getChannel().send().action(HELPER.getOsuStats(event.getMessage().toLowerCase().substring(5)));
			}

			else if ((event.getMessage().toLowerCase()).contains("http://www.youtube.com/watch?v=")
					|| (event.getMessage().toLowerCase()).contains("https://www.youtube.com/watch?v=")) {

				String[] texte = event.getMessage().split(" ");
				for (int i = 0; i < texte.length; i++) {
					if (texte[i].toLowerCase().contains("http://www.youtube.com/watch?v=") || texte[i].toLowerCase().contains("https://www.youtube.com/watch?v=")) {
						String link = texte[i];
						event.getChannel().send().action(HELPER.getYoutube(link));
					}
				}
			}

			else if ((event.getMessage().toLowerCase()).startsWith("!goto") && event.getUser().getNick().equalsIgnoreCase("morphyum")) {
				String[] message = event.getMessage().toLowerCase().split(" ");
				String channel = message[1];
				if (Main.isChannelWithoutCyber(channel))
					Main.newBot(channel);
			}

			else if ((event.getMessage().toLowerCase()).startsWith("!announce") && event.getUser().getNick().equalsIgnoreCase("morphyum")) {
				String message = event.getMessage().substring(10);
				Main.announce(message);
			}

			else if ((event.getMessage().toLowerCase()).startsWith("!pb")) {
				String[] message = event.getMessage().toLowerCase().split(" ");
				event.getChannel().send().action(HELPER.getPB(message[message.length - 3], message[message.length - 2], message[message.length - 1]));
			}

			else if (event.getMessage().equalsIgnoreCase("!king")) {
				event.getChannel().send().action("Oh, the King has been transformed ! Please find the Magic Wand so we can change him back.");
			}

			else if ((event.getMessage().toLowerCase()).startsWith("!cape")) {
				event.getChannel().send().action(capes(event));
			}

			else if ((event.getMessage().toLowerCase()).startsWith("!death")) {
				event.getChannel().send().action(death(event));
			}

			else if ((event.getMessage().toLowerCase()).startsWith("!soldier")) {
				event.getChannel().send().action(soldiers(event));
			}

			else if ((event.getMessage().toLowerCase()).startsWith("!bonk")) {
				event.getChannel().send().action(bonks(event));
			}

			else if (event.getMessage().equalsIgnoreCase("!race")) {
				event.getChannel().send().action("To see all contestent of a race go to http://speedrunslive.com/races/ !");
			}

			else if (event.getMessage().equalsIgnoreCase("!help")) {
				event.getChannel().send()
						.action("This is version " + version + " of CyberMorphy if you dont know this version yet check http://www.twitch.tv/cybermorphy for new commands");

			} else if (event.getMessage().toLowerCase().startsWith("!srlstandings")) {
				if (event.getMessage().equalsIgnoreCase("!srlstandings")) {
					event.getChannel().send().action(HELPER.srlStandings());
					event.getChannel().send().action("The Rest of the Leaderboard can be found here: http://speedrunslive.com/races/game/#!/smw/1");
				} else {
					event.getChannel().send().action(HELPER.srlStandingsSearch(event.getMessage().toLowerCase().substring(14)));
				}
			}

			else if (event.getMessage().equalsIgnoreCase("!join")) {
				if (Main.isChannelWithoutCyber(event.getUser().getNick())) {
					Main.bots.add(Main.newBot(event.getUser().getNick()));
					event.getChannel().send().action("I cloned myself for " + event.getUser().getNick());
				}
			}

			else if (event.getMessage().toLowerCase().startsWith("!advertise")) {
				if (event.getMessage().equalsIgnoreCase("!advertise")) {
					event.getChannel().send().action(advertisement);
				} else if ((event.getMessage().toLowerCase().contains("!advertise set")) && (event.getChannel().isOp(event.getUser()))) {
					advertisement = event.getMessage().substring(15);
					HELPER.srlStandingsSearch("advertisement set to: " + advertisement);
					HELPER.saveAllSettings(event.getChannel().getName(), this);
				}
			}

			else if (event.getMessage().equalsIgnoreCase("!orb")) {
				event.getChannel().send().action("OrbOrbOrbOrbOrb! http://deanyd.net/smw/index.php?title=Orb");
			}

			else if (event.getMessage().toLowerCase().startsWith("!cloud")) {
				if (event.getMessage().equalsIgnoreCase("!cloud")) {
					event.getChannel().send().action("http://deanyd.net/smw/index.php?title=Cloud");
				} else {
					event.getChannel().send().action(cloudcount(event));
				}
			}

			else if (event.getMessage().toLowerCase().startsWith("!greet")) {
				event.getChannel().send().action(greeting(event));
			}

			else if (((event.getMessage().toLowerCase()).startsWith("!request")) && (event.getChannel().isOp(event.getUser()))) {
				HELPER.sendMail(event.getUser().getNick() + ": " + event.getMessage().substring(9));
				event.getChannel().send().action("Thanks for your request!");
			}

			else if (event.getMessage().toLowerCase().equalsIgnoreCase("!leave")) {
				if (event.getChannel().isOp(event.getUser()) || event.getUser().getNick().equalsIgnoreCase("morphyum")) {
					event.getChannel().send().action("Fine i leave BibleThump");
					HELPER.saveAllSettings(event.getChannel().getName(), this);
					HELPER.deleteChannel(event.getChannel().getName());
					Thread.sleep(3000);
					event.getBot().close();

				}
			} else if (event.getMessage().toLowerCase().equalsIgnoreCase("!commands")) {
				String commandlist = "";
				for (int i = 0; i < commands.size(); i++) {
					commandlist += commands.get(i).getHead() + " | ";
				}
				event.getChannel().send().action(commandlist);

			} else if (event.getMessage().toLowerCase().startsWith("-!")) {
				if (event.getChannel().isOp(event.getUser())) {
					if (event.getMessage().toLowerCase().startsWith("--!")) {
						String modcommand = event.getMessage().replace("--", "");
						for (int i = 0; i < commands.size(); i++) {
							if (modcommands.get(i).getHead().contentEquals(modcommand)) {
								modcommands.remove(i);
								HELPER.deleteModCommand(modcommand, event.getChannel().getName());
								event.getChannel().send().action("Mod Command " + modcommand + " deleted");
								break;
							}
						}
					} else {
						String command = event.getMessage().replace("-", "");
						for (int i = 0; i < commands.size(); i++) {
							if (commands.get(i).getHead().contentEquals(command)) {
								commands.remove(i);
								HELPER.deleteCommand(command, event.getChannel().getName());
								event.getChannel().send().action("Command " + command + " deleted");
								break;
							}
						}
					}
				}
			} else if (event.getMessage().toLowerCase().startsWith("+!")) {
				if (event.getChannel().isOp(event.getUser())) {
					if (event.getMessage().toLowerCase().startsWith("++!")) {
						String[] commandparts = event.getMessage().split(" ");
						String body = "";
						for (int i = 1; i < commandparts.length; i++) {
							body += commandparts[i] + " ";
						}
						if (checkModCommands(commandparts[0].replace("++", "")).contentEquals("")) {
							modcommands.add(new Command(commandparts[0].replace("++", ""), body));
							HELPER.addModCommand(event.getMessage().substring(2), event.getChannel().getName());
							event.getChannel().send().action("Mod Command " + commandparts[0].replace("++", "") + " added");
						} else {
							event.getChannel().send().action("Mod Command " + commandparts[0].replace("++", "") + " is already on the Commandlist");
						}
					} else {
						String[] commandparts = event.getMessage().split(" ");
						String body = "";
						for (int i = 1; i < commandparts.length; i++) {
							body += commandparts[i] + " ";
						}
						if (checkPrivateCommands(commandparts[0].replace("+", "")).contentEquals("")) {
							commands.add(new Command(commandparts[0].replace("+", ""), body));
							HELPER.addCommand(event.getMessage().substring(1), event.getChannel().getName());
							event.getChannel().send().action("Command " + commandparts[0].replace("+", "") + " added");
						} else {
							event.getChannel().send().action("Command " + commandparts[0].replace("+", "") + " is already on the Commandlist");
						}
					}
				}

			} else {
				if (event.getChannel().isOp(event.getUser())) {
					String modcommand = checkModCommands(event.getMessage());
					if (!modcommand.contentEquals("")) {
						event.getChannel().send().action(modcommand);
					} else {
						String command = checkPrivateCommands(event.getMessage());
						if (!command.contentEquals("")) {
							event.getChannel().send().action(command);
						}
					}
				} else {
					String command = checkPrivateCommands(event.getMessage());
					if (!command.contentEquals("")) {
						event.getChannel().send().action(command);
					}
				}

			}

			Thread.sleep(3000);
		}
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

	private String checkModCommands(String message) {
		System.out.println(message);
		for (int i = 0; i < modcommands.size(); i++) {
			if (message.toLowerCase().equalsIgnoreCase(modcommands.get(i).getHead())) {
				return modcommands.get(i).getBody();
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

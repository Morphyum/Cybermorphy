package de.morphyum.cybermorphy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HELPER {
	public static String getYoutube(String link) {
		String id;
		if (link.substring(4, 5).toLowerCase().equals("s")) {
			id = link.substring(32);
		} else {
			id = link.substring(31);
		}
		String video = getHTML("https://www.googleapis.com/youtube/v3/videos?part=snippet&id=" + id + "&key=AIzaSyDOqZtT01vX5FyWTPzalHIfq-wbOXIju2w");
		JSONObject jsonobject = new JSONObject(video);
		JSONArray items = jsonobject.getJSONArray("items");
		jsonobject = items.getJSONObject(0).getJSONObject("snippet");
		String response = "Oh thanks! A link to the video: " + jsonobject.getString("title") + " uploaded by " + jsonobject.getString("channelTitle");
		return response;

	}

	public static String randomQuote() {
		String raw = getHTML("http://pastebin.com/raw.php?i=q3HLmbf6");
		String[] quotes = raw.split("-");
		Random randGen = new Random();
		int randNum = randGen.nextInt(quotes.length);
		return quotes[randNum];
	}

	public static String srlStandings() {
		String leaderboard = getHTML("http://api.speedrunslive.com:81/leaderboard/smw");
		JSONObject jsonobj = new JSONObject(leaderboard);
		JSONArray jsonarray = jsonobj.getJSONArray("leaders");
		String leader = "";
		for (int i = 0; i < 10; i++) {
			leader += "#" + (i + 1) + " " + jsonarray.getJSONObject(i).getString("name") + " " + jsonarray.getJSONObject(i).getInt("trueskill") + " --- ";
		}
		return leader;
	}

	public static String srlStandingsSearch(String name) {
		String leaderboard = getHTML("http://api.speedrunslive.com:81/leaderboard/smw");
		JSONObject jsonobj = new JSONObject(leaderboard);
		JSONArray jsonarray = jsonobj.getJSONArray("leaders");
		Boolean match = false;
		String standings = null;
		for (int i = 0; i < jsonarray.length(); i++) {
			if (jsonarray.getJSONObject(i).getString("name").equalsIgnoreCase(name)) {

				standings = name + " is currently ranked " + "#" + jsonarray.getJSONObject(i).getInt("rank") + " with "
						+ jsonarray.getJSONObject(i).getInt("trueskill") + " Points";
				match = true;
			}
		}
		if (!match) {
			standings = name + " is not ranked yet.";
		}
		return standings;
	}

	public static String getHTML(String urlToRead) {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		try {

			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String showCategories(String game) {
		String categories = getHTML("http://www.speedrun.com/api/v1/games/" + game + "/categories");
		JSONObject jsonobject2 = new JSONObject(categories);
		JSONArray sections = jsonobject2.getJSONArray("data");
		String catList = "";
		for (int h = 0; h < sections.length(); h++) {
			catList += sections.getJSONObject(h).getString("weblink").replace("http://www.speedrun.com/", "").replace(game + "#", "").toLowerCase() + " | ";
		}
		return catList;
	}

	public static String getWR(String game, String category) {
		category = category.toLowerCase();
		String wrvideo = "no video";
		String wrJson = getHTML("http://www.speedrun.com/api/v1/leaderboards/" + game + "/category/" + category + "?top=1");
		JSONObject jsonobject = new JSONObject(wrJson);
		jsonobject = jsonobject.getJSONObject("data");
		JSONArray runs = jsonobject.getJSONArray("runs");
		JSONObject run = runs.getJSONObject(0).getJSONObject("run");
		if (!run.isNull("videos")) {
			JSONObject videos = run.getJSONObject("videos");
			JSONArray links = videos.getJSONArray("links");
			wrvideo = links.getJSONObject(0).getString("uri");
		}
		Duration time = Duration.parse(run.getJSONObject("times").getString("primary"));
		String runnerUri = run.getJSONArray("players").getJSONObject(0).getString("uri");
		String runnerPage = getHTML(runnerUri);
		jsonobject = new JSONObject(runnerPage);
		jsonobject = jsonobject.getJSONObject("data");
		String name = jsonobject.getJSONObject("names").getString("international");

		return ("The World Record for the " +  game + " " + category + " category is " + formatDuration(time) + " by " + name + ", videolink: " + wrvideo);
	}

	public static String formatDuration(Duration duration) {
		long seconds = duration.getSeconds();
		long absSeconds = Math.abs(seconds);
		String positive = String.format("%d:%02d:%02d", absSeconds / 3600, (absSeconds % 3600) / 60, absSeconds % 60);
		return seconds < 0 ? "-" + positive : positive;
	}

	public static ArrayList<String> readChannels() {
		String path = System.getProperty("user.dir") + "/settings/";
		ArrayList<String> channels = new ArrayList<String>();
		try {
			File file = new File(path + "channels.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				channels.add(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("No channels found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return channels;
	}

	public static boolean deleteChannel(String channel) {
		channel = channel.substring(1);
		ArrayList<String> channels = readChannels();
		boolean found = false;
		for (int i = 0; i < channels.size(); i++) {
			System.out.println(channels.get(i));
			if (channels.get(i).equalsIgnoreCase(channel)) {
				channels.remove(i);
				found = true;
				break;
			}
		}
		if (found) {
			String path = System.getProperty("user.dir") + "/settings/";
			File file = new File(path + "channels.txt");
			try {
				if (file.exists()) {
					file.delete();
					file.createNewFile();
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				for (int i = 0; i < channels.size(); i++) {
					bw.write(channels.get(i));
					bw.newLine();
					bw.flush();
				}

				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	public static boolean newChannel(String channel) {
		ArrayList<String> channels = readChannels();
		boolean found = false;
		for (int i = 0; i < channels.size(); i++) {
			if (channels.get(i).contentEquals(channel)) {
				found = true;
				break;
			}
		}
		if (!found) {
			String path = System.getProperty("user.dir") + "/settings/";
			File file = new File(path + "channels.txt");
			try {
				if (!file.exists()) {
					new File(path).mkdirs();
					file.createNewFile();
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				bw.write(channel);
				bw.newLine();
				bw.flush();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	public static CyberMorphy loadSettings(String channel, CyberMorphy bot) {
		String path = System.getProperty("user.dir") + "/settings/" + channel;
		String fileText = null;
		try {
			Scanner scanner = new Scanner(new File(path + "/settings.json"));
			scanner.useDelimiter("\\A");
			fileText = scanner.next();
			scanner.close();
			JSONObject save = new JSONObject(fileText);
			bot.soldiers = save.getInt("soldiers");
			bot.capes = save.getInt("capes");
			bot.death = save.getInt("death");
			bot.bonks = save.getInt("bonks");
			bot.cloudsgot = save.getInt("orbsgot");
			bot.cloudsfailed = save.getInt("orbsfailed");
			bot.greetings = save.getBoolean("greetings");
			bot.streamerName = save.getString("streamername");
			bot.advertisement = save.getString("advertisement");
			bot.greeting = save.getString("greeting");
			bot.welcomeBack = save.getString("welcomeback");
			bot.mhroom = save.getString("mhroom");
			bot.period =save.getInt("period");
		} catch (FileNotFoundException e) {
			System.out.println("No Settings found for: " + channel);
		} catch (JSONException e) {
			System.out.println("Missing JSON value for: " + channel);
		}
		bot.viewers = readViewers(channel);
		bot.commands = readCommands(channel);
		bot.modcommands = readModCommands(channel);
		return bot;
	}

	public static boolean saveAllSettings(String channel, CyberMorphy bot) {
		JSONObject save = new JSONObject();
		save.put("soldiers", bot.soldiers);
		save.put("capes", bot.capes);
		save.put("death", bot.death);
		save.put("bonks", bot.bonks);
		save.put("orbsgot", bot.cloudsgot);
		save.put("orbsfailed", bot.cloudsfailed);
		save.put("greetings", bot.greetings);
		save.put("streamername", bot.streamerName);
		save.put("advertisement", bot.advertisement);
		save.put("greeting", bot.greeting);
		save.put("welcomeback", bot.welcomeBack);
		save.put("mhroom", bot.mhroom);
		save.put("period", bot.period);
		String path = System.getProperty("user.dir") + "/settings/" + channel.substring(1) + "/";
		File file = new File(path, "settings.json");
		String content = save.toString();
		try {
			if (!file.exists()) {
				new File(path).mkdirs();
				System.out.println(file.getAbsolutePath());
				file.createNewFile();
			}
			FileOutputStream fop = new FileOutputStream(file);
			byte[] contentInBytes = content.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	private static ArrayList<String> readViewers(String channel) {
		String path = System.getProperty("user.dir") + "/settings/" + channel + "/";
		ArrayList<String> viewers = new ArrayList<String>();
		try {
			File file = new File(path + "viewers.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				viewers.add(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("No Viewers found for: " + channel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return viewers;
	}

	public static boolean newViewer(String channel, String viewer) {
		String path = System.getProperty("user.dir") + "/settings/" + channel.substring(1) + "/";
		File file = new File(path + "viewers.txt");
		try {
			if (!file.exists()) {
				new File(path).mkdirs();
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			bw.write(viewer);
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public static String getPB(String game, String category, String name) {

		int ranking = 0;
		String pbvideo = "no video";
		Duration time = null;

		String pbJson = getHTML("http://www.speedrun.com/api/v1/users/" + name + "/personal-bests");
		JSONObject jsonobject2 = new JSONObject(pbJson);
		JSONArray pbs = jsonobject2.getJSONArray("data");

		String gameJson = getHTML("http://www.speedrun.com/api/v1/games/" + game);
		jsonobject2 = new JSONObject(gameJson);
		String gameId = jsonobject2.getJSONObject("data").getString("id");

		String categoryJson = getHTML("http://www.speedrun.com/api/v1/games/" + game + "/categories");
		jsonobject2 = new JSONObject(categoryJson);
		JSONArray categories = jsonobject2.getJSONArray("data");
		String categoryId = null;
		for (int i = 0; i < categories.length(); i++) {
			jsonobject2 = categories.getJSONObject(i);
			if (jsonobject2.getString("weblink").replace("http://www.speedrun.com/", "").replace(game + "#", "").equalsIgnoreCase(category)){
				categoryId = jsonobject2.getString("id");
				break;
			}

		}

		for (int i = 0; i < pbs.length(); i++) {
			JSONObject run = pbs.getJSONObject(i);
			if (run.getJSONObject("run").getString("game").equalsIgnoreCase(gameId)
					&& run.getJSONObject("run").getString("category").equalsIgnoreCase(categoryId)) {
				ranking = run.getInt("place");
				time = Duration.parse(run.getJSONObject("run").getJSONObject("times").getString("primary"));
				if (!run.getJSONObject("run").isNull("videos")) {
					pbvideo = run.getJSONObject("run").getJSONObject("videos").getJSONArray("links").getJSONObject(0).getString("uri");
				}
			}
		}

		return (name + " is currently ranked #" + ranking + ", on the " + game + " " + category + " Leaderboard with a time of " + formatDuration(time)
				+ ", videolink: " + pbvideo);

	}

	public static void sendMail(String userMessage) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("themorphyum", "nudelsuppe");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("themorphyum@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("themorphyum@gmail.com"));
			message.setSubject("CyberMorphy Request");
			message.setText(userMessage);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getOsuStats(String osuName) {

		String stats = getHTML("https://osu.ppy.sh/api/get_user?k=61fbda7cf146a604a7eb3629c0f52fcfc10a7d7b&u=" + osuName);
		// JSONArray jarray = new JSONArray(stats);
		// JSONObject jobject = new JSONObject(jarray.getJSONObject(0));
		JSONObject jobject = new JSONObject(stats);

		return osuName + "'s stats are Playcount: " + jobject.getString("playcount") + " Ranked Score: " + jobject.getString("ranked_score") + " Rank: "
				+ jobject.getString("pp_rank") + " Level: " + jobject.getString("level") + " PP Points: " + jobject.getString("pp_raw") + " Accuracy: "
				+ jobject.getString("accuracy");

	}

	public static Boolean addCommand(String command, String channel) {
		String[] commandparts = command.split(" ");
		ArrayList<Command> commands = readCommands(channel);
		boolean found = false;
		for (int i = 0; i < commands.size(); i++) {
			if (commands.get(i).getHead().contentEquals(commandparts[0])) {
				found = true;
				break;
			}
		}
		if (!found) {
			String path = System.getProperty("user.dir") + "/settings/" + channel.substring(1) + "/";
			File file = new File(path + "commands.txt");
			try {
				if (!file.exists()) {
					new File(path).mkdirs();
					file.createNewFile();
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				bw.write(commandparts[0]);
				bw.newLine();
				for (int i = 1; i < commandparts.length; i++) {
					bw.write(commandparts[i] + " ");
				}
				bw.newLine();
				bw.flush();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}

	public static Boolean addModCommand(String command, String channel) {
		String[] commandparts = command.split(" ");
		ArrayList<Command> modcommands = readModCommands(channel);
		boolean found = false;
		for (int i = 0; i < modcommands.size(); i++) {
			if (modcommands.get(i).getHead().contentEquals(commandparts[0])) {
				found = true;
				break;
			}
		}
		if (!found) {
			String path = System.getProperty("user.dir") + "/settings/" + channel.substring(1) + "/";
			File file = new File(path + "modcommands.txt");
			try {
				if (!file.exists()) {
					new File(path).mkdirs();
					file.createNewFile();
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				bw.write(commandparts[0]);
				bw.newLine();
				for (int i = 1; i < commandparts.length; i++) {
					bw.write(commandparts[i] + " ");
				}
				bw.newLine();
				bw.flush();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}

	public static ArrayList<Command> readCommands(String channel) {
		String path = System.getProperty("user.dir") + "/settings/" + channel + "/";
		ArrayList<Command> commands = new ArrayList<Command>();
		try {
			File file = new File(path + "commands.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String head;
			while ((head = br.readLine()) != null) {
				String body = br.readLine();
				commands.add(new Command(head, body));
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("No Commands found for: " + channel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commands;
	}

	public static ArrayList<Command> readModCommands(String channel) {
		String path = System.getProperty("user.dir") + "/settings/" + channel + "/";
		ArrayList<Command> modcommands = new ArrayList<Command>();
		try {
			File file = new File(path + "modcommands.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String head;
			while ((head = br.readLine()) != null) {
				String body = br.readLine();
				modcommands.add(new Command(head, body));
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("No Mod Commands found for: " + channel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modcommands;
	}

	public static Boolean deleteCommand(String command, String channel) {
		channel = channel.substring(1);
		ArrayList<Command> commands = readCommands(channel);
		boolean found = false;
		for (int i = 0; i < commands.size(); i++) {
			if (commands.get(i).getHead().equalsIgnoreCase(command)) {
				commands.remove(i);
				found = true;
				break;
			}
		}
		if (found) {
			String path = System.getProperty("user.dir") + "/settings/" + channel + "/";
			File file = new File(path + "commands.txt");
			try {
				if (file.exists()) {
					file.delete();
					file.createNewFile();
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				for (int i = 0; i < commands.size(); i++) {
					bw.write(commands.get(i).getHead());
					bw.newLine();
					bw.write(commands.get(i).getBody());
					bw.newLine();
					bw.flush();
				}

				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;

	}

	public static Boolean deleteModCommand(String command, String channel) {
		channel = channel.substring(1);
		ArrayList<Command> modcommands = readModCommands(channel);
		boolean found = false;
		for (int i = 0; i < modcommands.size(); i++) {
			if (modcommands.get(i).getHead().equalsIgnoreCase(command)) {
				modcommands.remove(i);
				found = true;
				break;
			}
		}
		if (found) {
			String path = System.getProperty("user.dir") + "/settings/" + channel + "/";
			File file = new File(path + "modcommands.txt");
			try {
				if (file.exists()) {
					file.delete();
					file.createNewFile();
				}
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				for (int i = 0; i < modcommands.size(); i++) {
					bw.write(modcommands.get(i).getHead());
					bw.newLine();
					bw.write(modcommands.get(i).getBody());
					bw.newLine();
					bw.flush();
				}

				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;

	}
}

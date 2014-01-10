package de.morphyum.cybermorphy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONArray;
import org.json.JSONObject;
import org.pircbotx.hooks.events.MessageEvent;

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
				
				standings = name + " is currently ranked " + "#" + jsonarray.getJSONObject(i).getInt("rank") + " with " + jsonarray.getJSONObject(i).getInt("trueskill") + " Points";
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

	public static String showSMWCats() {
		String categories = getHTML("http://www.deanyd.net/smw/api.php?action=parse&page=leaderboards&format=json&prop=sections");
		JSONObject jsonobject2 = new JSONObject(categories);
		jsonobject2 = jsonobject2.getJSONObject(("parse"));
		JSONArray sections = jsonobject2.getJSONArray("sections");
		String catList = "";
		for (int h = 0; h < sections.length(); h++) {
			catList += sections.getJSONObject(h).getString("line").toLowerCase() + " | ";
		}
		return catList;
	}

	public static String getWR(String category) {
		category = category.toLowerCase();

		String categories = getHTML("http://www.deanyd.net/smw/api.php?action=parse&page=leaderboards&format=json&prop=sections");
		JSONObject jsonobject2 = new JSONObject(categories);
		jsonobject2 = jsonobject2.getJSONObject(("parse"));
		JSONArray sections = jsonobject2.getJSONArray("sections");
		boolean catfound = false;
		for (int h = 0; h < sections.length(); h++) {
			if (sections.getJSONObject(h).getString("line").toLowerCase().contentEquals(category)) {
				catfound = true;
				String section = sections.getJSONObject(h).getString("index");
				String wikitext = getHTML("http://www.deanyd.net/smw/api.php?action=parse&page=leaderboards&format=json&section=" + section);
				JSONObject jsonobject = new JSONObject(wikitext);
				jsonobject = jsonobject.getJSONObject(("parse"));
				jsonobject = jsonobject.getJSONObject(("text"));
				wikitext = jsonobject.getString("*");
				String[] wrtime = wikitext.split("<td>");
				String[] wrname = wrtime[1].split(">");

				return ("The World Record for " + category + " is " + wrtime[2].replace("</td>", "").trim() + " by " + wrname[1].replace("</a", "").trim());
			}
		}
		if (!catfound) {
			return ("Category " + category + " was not found");
		}
		catfound = false;
		return "Error";
	}

	public static String getPB(String category, String name) {

		name = name.toLowerCase();

		category = category.toLowerCase();

		String categories = getHTML("http://www.deanyd.net/smw/api.php?action=parse&page=leaderboards&format=json&prop=sections");
		JSONObject jsonobject2 = new JSONObject(categories);
		jsonobject2 = jsonobject2.getJSONObject(("parse"));
		JSONArray sections = jsonobject2.getJSONArray("sections");
		boolean catfound = false;
		for (int h = 0; h < sections.length(); h++) {
			if (sections.getJSONObject(h).getString("line").toLowerCase().contentEquals(category)) {
				catfound = true;
				String section = sections.getJSONObject(h).getString("index");
				String wikitext = getHTML("http://www.deanyd.net/smw/api.php?action=parse&page=leaderboards&format=json&section=" + section);
				JSONObject jsonobject = new JSONObject(wikitext);
				jsonobject = jsonobject.getJSONObject(("parse"));
				jsonobject = jsonobject.getJSONObject(("text"));
				wikitext = jsonobject.getString("*");
				String[] pbtext = wikitext.split("<td>");
				int ranking = -1;
				boolean playerfound = false;
				for (int i = 0; i < pbtext.length; i++) {
					if (pbtext[i].contains("title")) {
						String[] pbhelp = pbtext[i].split(">");
						ranking++;

						if (pbhelp[1].replace("</a", "").trim().toLowerCase().contentEquals(name)) {
							playerfound = true;
							return (pbhelp[1].replace("</a", "").trim() + " is currently ranked #" + ranking + " on the " + category + " Leaderboard with a time of " + pbtext[i + 1]
									.replace("</td>", "").trim());
						}
					}

				}
				if (!playerfound) {
					return (name + " was not found on the leaderboard for the category " + category);
				}
				playerfound = false;
				break;
			}
		}
		if (!catfound) {
			return ("Category " + category + " was not found");
		}
		return "Error";

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
}

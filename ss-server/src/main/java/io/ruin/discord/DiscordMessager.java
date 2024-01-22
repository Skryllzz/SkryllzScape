package io.ruin.discord;

import io.ruin.discord.embed.EmbedAuthor;
import io.ruin.discord.webhook.WebhookClient;
import io.ruin.discord.webhook.WebhookClientBuilder;
import org.json.simple.JSONObject;

import java.awt.*;
import java.net.URI;

public class DiscordMessager extends JSONObject {
	final static String staffhook         = "https://discord.com/api/webhooks/1198747439604170852/EFt2FkDmKSp-A7dlze3Lx-StENP3W2uTneeLzQNhYZls7RL4Y-rHaYbngZRXzFl8YR2-";
	final static String broadcastHook     = "https://discord.com/api/webhooks/1198744350964850759/6FnOFGrqmFVgEDbECCDZvvETlNvip6tQ69dVXtDLEqMIdJB4vnj8usF68nvT3rua0Xyy";

	final static String onlinestatushook = "https://discord.com/api/webhooks/1198798320932495431/Lm2TXTkij9Lip-fhKpxqLqXjZhm6d-CKGzooywkum_DnfIphCFj12dFRwkWD-BdkwEWa";


	static void fireWebhook(String message, String title, String channel) {
		try {
			WebhookClient client = new WebhookClientBuilder().withURI(new URI(channel)).build(); // Create the webhook client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					.withTitle(title) // The title of the embed element
					.withURL("http://localhost.xyz/") // The URL of the embed element
					.withColor(Color.GREEN)
					.withAuthor(new EmbedAuthor("SkryllzScape", "http://localhost.xyz/", null))
					.withDescription(message)
					.build();

			client.sendPayload(embed);

			DiscordMessage discordMessage = new DiscordMessage.Builder(Misc.stripIngameFormat(message)) // The content of the message
					.withUsername(title) // Override the username of the bot
					.build(); // Build the message

			client.sendPayload(discordMessage);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.out.println("Didnt work");
		}
	}

	public static void sendBroadcastMessage(String msg) {
		fireWebhook(msg, "Announcement", broadcastHook);
	}

	public static void sendOnlineStatus(String msg) {
		fireWebhook(msg, "Server", onlinestatushook);
	}

	public static void sendStaffMessage(String msg) {
		fireWebhook(msg, "Staff-Alert", staffhook);
	}

}

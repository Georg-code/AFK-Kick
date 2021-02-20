package ch.derhohi.main;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Session {
	private Player p;

	private Location lastLoc;

	private int afkMinutes;

	public Session(Player p) {
		this.p = p;
		this.lastLoc = p.getLocation();
		this.afkMinutes = 0;
	}

	public void resetMinute() {
		if (this.p != null && this.p.isOnline())
			if (!this.p.hasPermission("afkkick.ignore")) {
				this.afkMinutes = 0;
				this.lastLoc = this.p.getLocation();
			}
	}

	public void increaseMinute() {
		int kick_warn_delay = Main.getInstance().getConfig().getInt("Config.kick_warn_delay");
		int kick_delay = Main.getInstance().getConfig().getInt("Config.kick_delay");
		if (this.p != null && this.p.isOnline())
			if (!this.p.hasPermission("afkkick.ignore"))
				if (this.lastLoc.getWorld().equals(this.p.getWorld()) && this.lastLoc.distanceSquared(this.p.getLocation()) < 4.0D) {
					this.afkMinutes++;
					this.lastLoc = this.p.getLocation();
					if (this.afkMinutes == kick_warn_delay) {
						Boolean sound_on_get_warn = Boolean.valueOf(Main.getInstance().getConfig().getString("Config.sound_on_get_warn"));

						if (sound_on_get_warn == true) {
							this.p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10,3);
						} else {

						}
						this.p.sendMessage("" + Main.getInstance().getConfig().getString("Config.prefix") + " " + Main.getInstance().getConfig().getString("Config.kick_warn_msg"));
						TextComponent tc = new TextComponent();
						tc.setText("" + Main.getInstance().getConfig().getString("Config.kick_warn_msg_afk"));
						tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/notafk"));
						p.spigot().sendMessage(tc);
					} else if(this.afkMinutes >= kick_delay) {
						kickPlayer();
					}
				} else {
					this.afkMinutes = 0;
					this.lastLoc = this.p.getLocation();
				}
	}

	private void kickPlayer() {
		if (!this.p.hasPermission("afkkick.ignore"))
			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Main.getInstance(), new Runnable() {
				public void run() {
					Session.this.p.kickPlayer("" + Main.getInstance().getConfig().getString("Config.prefix") + " " + Main.getInstance().getConfig().getString("Config.kick_msg"));
				}
			},  20L);
	}


	public Player getPlayer() {
		return this.p;
	}
}
package ch.derhohi.main;

import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements Listener {
    public ArrayList<Session> list = new ArrayList<>();

    public static Main instance;

    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(this, (Plugin)this);
        System.out.println("AFKKick loaded!");
        getRunnable().runTaskTimer((Plugin)this, 0L, 1200L);
        getCommand("notafk").setExecutor(new Command());
        getCommand("afkreload").setExecutor(new Command());
        loadConfig();

    }

    public void onDisable() {}

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Session newSession = new Session(p);
        this.list.add(newSession);
    }
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

            Main main = Main.getInstance();
            for (int i = 0; i < main.list.size(); i++) {
                if (p.getUniqueId() == main.list.get(i).getPlayer().getUniqueId()) {
                    main.list.get(i).resetMinute();
                    break;
                }
            }

    }

    @EventHandler
    public void onSneak (PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();

        Main main = Main.getInstance();
        for (int i = 0; i < main.list.size(); i++) {
            if (p.getUniqueId() == main.list.get(i).getPlayer().getUniqueId()) {
                main.list.get(i).resetMinute();
                break;
            }
        }

    }
    @EventHandler
    public void oDrop (PlayerDropItemEvent e) {
        Player p = e.getPlayer();

        Main main = Main.getInstance();
        for (int i = 0; i < main.list.size(); i++) {
            if (p.getUniqueId() == main.list.get(i).getPlayer().getUniqueId()) {
                main.list.get(i).resetMinute();
                break;
            }
        }

    }

    public static Main getInstance() {
        return instance;
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        for (int i = 0; i < this.list.size(); i++) {
            Session session = this.list.get(i);
            if (session.getPlayer().getName().equalsIgnoreCase(p.getName()))
                this.list.remove(session);
        }
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();

    }
    public BukkitRunnable getRunnable() {
        return new BukkitRunnable() {
            public void run() {
                for (int i = 0; i < Main.this.list.size(); i++) {
                    Session session = Main.this.list.get(i);
                    session.increaseMinute();
                }

            }
        };
    }


}

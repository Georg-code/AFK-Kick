package ch.derhohi.main;

import org.bukkit.Sound;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String s, String[] args) {
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("notafk")) {
            boolean sond_on_notafk = Boolean.parseBoolean(Main.getInstance().getConfig().getString("Config.sond_on_notafk"));
            if (sond_on_notafk == true) {
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10,3);
            } else {

            }
            Main main = Main.getInstance();
            for (int i = 0; i < main.list.size(); i++) {
                if (p.getUniqueId() == main.list.get(i).getPlayer().getUniqueId()) {
                    main.list.get(i).resetMinute();
                    break;
                }
            }
            p.sendMessage("§7" + Main.getInstance().getConfig().getString("Config.prefix") + " " + Main.getInstance().getConfig().getString("Config.kick_not_afk_msg"));

            return true;
        } else if (cmd.getName().equalsIgnoreCase("afkreload")) {
            if (p.hasPermission("afk.reload")) {
                Main.getInstance().reloadConfig();
                String config_values = Main.getInstance().getConfig().getString("Config.config_values");
                String config_reload = Main.getInstance().getConfig().getString("Config.config_reload");
                p.sendMessage("" + config_values);
                String prefix = Main.getInstance().getConfig().getString("Config.prefix");
                String kick_msg = Main.getInstance().getConfig().getString("Config.kick_msg");
                Integer kick_warn_delay = Main.getInstance().getConfig().getInt("Config.kick_warn_delay");
                String kick_warn_msg = Main.getInstance().getConfig().getString("Config.kick_warn_msg");
                String kick_warn_msg_afk = Main.getInstance().getConfig().getString("Config.kick_warn_msg_afk");
                Integer kick_delay = Main.getInstance().getConfig().getInt("Config.kick_delay");
                String sound_on_get_warn = Main.getInstance().getConfig().getString("Config.sound_on_get_warn");
                String sound_on_notafk = Main.getInstance().getConfig().getString("Config.sond_on_notafk");
                String config_noperm = Main.getInstance().getConfig().getString("Config.config_noperm");
                String notafkmsg = Main.getInstance().getConfig().getString("Config.kick_not_afk_msg");
                p.sendMessage("§7---------------------------------");
                p.sendMessage("§3Prefix: §7" + prefix);
                p.sendMessage("§3Kickmsg: §7" + kick_msg);
                p.sendMessage("§3Kick warn delay: §7" + kick_warn_delay);
                p.sendMessage("§3Kick warn message: §7" + kick_warn_msg);
                p.sendMessage("§3Not AFK msg: §7" + kick_warn_msg_afk);
                p.sendMessage("§3Not AFK confirmed: " + notafkmsg);
                p.sendMessage("§3Kick Delay: §7" + kick_delay);
                p.sendMessage("§3Sound on warn: §7" + sound_on_get_warn);
                p.sendMessage("§3Sound on confirm: §7" + sound_on_notafk);
                p.sendMessage("§3No permisson: §7" + config_noperm);
                p.sendMessage("§7---------------------------------");
                p.sendMessage("");
                p.sendMessage("" + config_reload);






            } else {
                p.sendMessage("§7" + Main.getInstance().getConfig().getString("Config.config_noperm"));
            }
        }


            return true;
            }







        }




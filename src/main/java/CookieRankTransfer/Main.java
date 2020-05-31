package main.java.CookieRankTransfer;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.util.Arrays;
import java.util.List;

public class Main extends PluginBase implements Listener {

    List<String> vips;
    List<String> ultras;

    @Override
    public void onEnable() {
        Config config = this.getConfig();

        // Loading all usernames from config
        String allVips = config.getString("vips");
        vips = Arrays.asList(allVips.split(","));

        String allUltra = config.getString("ultras");
        ultras = Arrays.asList(allUltra.split(","));

        this.getServer().getLogger().info("VIPs loaded : " + vips.size());
        this.getServer().getLogger().info("Ultras loaded : " + ultras.size());



        // Registering the listeners
        this.getServer().getPluginManager().registerEvents(this, this);


    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if(vips.contains(event.getPlayer().getName()) && !event.getPlayer().hasPermission("group.vip")){
            this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "lp user " + event.getPlayer().getName()  + " parent add vip");
            event.getPlayer().sendMessage(TextFormat.GREEN + "> Welcome to Cookie Build V2! Your rank has automatically been transferred. Have fun!");
        }

        if(ultras.contains(event.getPlayer().getName()) && !event.getPlayer().hasPermission("group.legend")){
            this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "lp user " + event.getPlayer().getName()  + " parent add legend");
            event.getPlayer().sendMessage(TextFormat.GREEN + "> Welcome to Cookie Build V2! Your rank has automatically been transferred. Have fun!");
        }
    }


}

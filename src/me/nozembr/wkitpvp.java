//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.nozembr;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

public class wkitpvp extends JavaPlugin implements Listener {
    public static FileConfiguration cf;
    
    public void onLoad(){
        cf = getConfig();
        cf.options().copyDefaults(true);
        saveConfig();
    }

private static wkitpvp instance;
    public FileConfiguration msgConfig;
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        msgConfig = get("messages.yml", new File(getDataFolder(), "messages.yml"));
        PluginDescriptionFile pdfFile = this.getDescription();
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "[KitPvPwarpsN]" + ChatColor.GREEN + " ACTIVE," + ChatColor.GREEN + " Version: " + pdfFile.getVersion());
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "[KitPvPwarpsN]" + ChatColor.RED + " DISABLE");
    }
    private static File file;

    private static FileConfiguration config;

    public FileConfiguration get(String resourceName, File out) {
        try{
            InputStream in = getResource(resourceName);
            InputStreamReader inReader = new InputStreamReader(in);

            if (!out.exists()){

                getDataFolder().mkdir();
                out.createNewFile();
            }
            FileConfiguration file = YamlConfiguration.loadConfiguration(out);

            if(in !=null){
                file.setDefaults(YamlConfiguration.loadConfiguration(inReader));
                file.options().copyDefaults(true);
                file.save(out);} return file;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
        public void reload () {
            super.reloadConfig();
        }




    public static wkitpvp getPlugin() {
        return (wkitpvp)getPlugin((Class) wkitpvp.class);
    }

    public static ArrayList<String> teleportkitpvp = new ArrayList();
    public static Permission perms = null;
    public static String prefix;
    public wkitpvp() {
    }

    private ItemStack getItem(ItemStack item,String name, String ... lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',name));

        List<String> lores = new ArrayList<>();
        for (String s : lore){
            lores.add(ChatColor.translateAlternateColorCodes('&',s));
        }
        meta.setLore(lores);

        item.setItemMeta(meta);

        return item;
    }

    @EventHandler
    public void cliqueinventario(InventoryClickEvent e){

        if(!e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("menu-name")))) {
            return;
        }
        Player player = (Player) e.getWhoClicked();
        int slot = e.getSlot();
        if (slot == 0){
            player.performCommand("fps");
        }
        if (slot == 1){
            player.performCommand("lava");
        }
        if (slot == 2){
            player.performCommand("knockback");
        }
        if (slot == 8){
            player.performCommand("leave");
        }


        e.setCancelled(true);
    }


    public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {
        //MENU
        if (command.getName().equalsIgnoreCase("wkmenu")) {
            BukkitScheduler scheduler;
            scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(this, new Runnable() {
                public void run() {
                    Player p = (Player) sender;

                    Inventory inv = Bukkit.createInventory(p, 9 * 1, ChatColor.translateAlternateColorCodes('&', msgConfig.getString("menu-name")));

                    inv.setItem(0, getItem(new ItemStack(Material.DIAMOND_SWORD), ChatColor.translateAlternateColorCodes('&', msgConfig.getString("fps-menu")), ChatColor.translateAlternateColorCodes('&', msgConfig.getString("fps-lore"))));
                    inv.setItem(1, getItem(new ItemStack(Material.LAVA_BUCKET), ChatColor.translateAlternateColorCodes('&', msgConfig.getString("lava-menu")), ChatColor.translateAlternateColorCodes('&', msgConfig.getString("lava-lore"))));
                    inv.setItem(2, getItem(new ItemStack(Material.STICK), ChatColor.translateAlternateColorCodes('&', msgConfig.getString("knock-menu")), ChatColor.translateAlternateColorCodes('&', msgConfig.getString("knock-lore"))));
                    inv.setItem(8, getItem(new ItemStack(Material.REDSTONE_BLOCK), ChatColor.translateAlternateColorCodes('&', msgConfig.getString("leave-menu")), ChatColor.translateAlternateColorCodes('&', msgConfig.getString("leave-lore"))));

                    p.openInventory(inv);
                }});
        }
        if (sender instanceof Player && !(sender instanceof ConsoleCommandSender)) {
            final Player p = (Player) sender;
            msgConfig = get("messages.yml", new File(getDataFolder(), "messages.yml"));
            if (command.getName().equalsIgnoreCase("wk")) {
                if (args.length == 0) {
                    sender.sendMessage("§b------- KitpvpWarpsN Plugin ---------");
                    sender.sendMessage(" ");
                    sender.sendMessage("§b- /leave: §aExit from warps.");
                    sender.sendMessage(" ");
                    sender.sendMessage("- §b/fps: §aGo to warp fps.");
                    sender.sendMessage(" ");
                    sender.sendMessage("- §b/lava: §aGo to warp lavachallenge.");
                    sender.sendMessage(" ");
                    sender.sendMessage("- §b/knockback: §aGo to warp knockback.");
                    sender.sendMessage(" ");
                    sender.sendMessage("§b- /wkadmin: §cSee admin commands.");
                    sender.sendMessage(" ");
                    sender.sendMessage("§b- /wkmenu: §cOpen Menu.");
                    sender.sendMessage(" ");
                    sender.sendMessage("§b------------------------------");
                } else {
                    BukkitScheduler scheduler;
                    if (args[0].equalsIgnoreCase("reload")) {
                        if (p.hasPermission("KitPvPwarpsN.reload")) {
                            reload();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("reloaded")));
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("Permission")));
                        }
                    }
                }
            }
            BukkitScheduler scheduler;
            if (command.getName().equalsIgnoreCase("fps")) {
                if (this.getConfig().contains("fps")) {
                    teleportkitpvp.add(p.getName());
                    scheduler = Bukkit.getServer().getScheduler();
                    scheduler.scheduleSyncDelayedTask(this, new Runnable() {
                        public void run() {
                            msgConfig = get("messages.yml", new File(getDataFolder(), "messages.yml"));
                            Player p1 = (Player) sender;
                            World w = Bukkit.getServer().getWorld(wkitpvp.this.getConfig().getString("fps.world"));
                            double x = wkitpvp.this.getConfig().getDouble("fps.x");
                            double y = wkitpvp.this.getConfig().getDouble("fps.y");
                            double z = wkitpvp.this.getConfig().getDouble("fps.z");
                            Location lobby = new Location(w, x, y, z);
                            lobby.setPitch((float) wkitpvp.this.getConfig().getDouble("fps.pitch"));
                            lobby.setYaw((float) wkitpvp.this.getConfig().getDouble("fps.yaw"));
                            p1.teleport(lobby);
                            teleportkitpvp.remove(p.getName());
                            p1.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("fps-teleport")));
                            p1.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("leave-msg")));
                            ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
                            ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
                            ItemMeta sopameta = sopa.getItemMeta();
                            sopameta.setDisplayName(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("soup-inventory")));
                            sopa.setItemMeta(sopameta);
                            ItemStack helmet = new ItemStack(Material.IRON_HELMET);
                            ItemStack armor = new ItemStack(Material.IRON_CHESTPLATE);
                            ItemStack legs = new ItemStack(Material.IRON_LEGGINGS);
                            ItemStack boots = new ItemStack(Material.IRON_BOOTS);
                            new ItemStack(Material.AIR);
                            p.getEquipment().setHelmet(helmet);
                            p.getEquipment().setChestplate(armor);
                            p.getEquipment().setLeggings(legs);
                            p.getEquipment().setBoots(boots);
                            p.getInventory().clear();

                            for (int i = 1; i < 2; ++i) {
                                p.getInventory().addItem(new ItemStack[]{espada});
                            }
                            for (int i = 2; i < 37; ++i) {
                                p.getInventory().addItem(new ItemStack[]{sopa});
                            }

                        }
                    });
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("fps-not-set")));
                }
            } else if (command.getName().equalsIgnoreCase("lava")) {
                if (this.getConfig().contains("lava")) {
                    teleportkitpvp.add(p.getName());
                    scheduler = Bukkit.getServer().getScheduler();
                    scheduler.scheduleSyncDelayedTask(this, new Runnable() {
                        public void run() {
                            Player p1 = (Player) sender;
                            World w = Bukkit.getServer().getWorld(wkitpvp.this.getConfig().getString("lava.world"));
                            double x = wkitpvp.this.getConfig().getDouble("lava.x");
                            double y = wkitpvp.this.getConfig().getDouble("lava.y");
                            double z = wkitpvp.this.getConfig().getDouble("lava.z");
                            Location lobby = new Location(w, x, y, z);
                            lobby.setPitch((float) wkitpvp.this.getConfig().getDouble("lava.pitch"));
                            lobby.setYaw((float) wkitpvp.this.getConfig().getDouble("lava.yaw"));
                            p1.teleport(lobby);
                            teleportkitpvp.remove(p.getName());
                            p1.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("lava-teleport")));
                            p1.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("leave-msg")));
                            ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
                            ItemMeta sopameta = sopa.getItemMeta();
                            sopameta.setDisplayName(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("soup-inventory")));
                            sopa.setItemMeta(sopameta);
                            ItemStack helmet = new ItemStack(Material.AIR);
                            ItemStack armor = new ItemStack(Material.AIR);
                            ItemStack legs = new ItemStack(Material.AIR);
                            ItemStack boots = new ItemStack(Material.AIR);
                            new ItemStack(Material.AIR);
                            p.getEquipment().setHelmet(helmet);
                            p.getEquipment().setChestplate(armor);
                            p.getEquipment().setLeggings(legs);
                            p.getEquipment().setBoots(boots);
                            p.getInventory().clear();

                            for (int i = 1; i < 37; ++i) {
                                p.getInventory().addItem(new ItemStack[]{sopa});
                            }

                        }
                    });
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("lava-not-set")));
                }
            } else if (command.getName().equalsIgnoreCase("knockback")) {
                if (this.getConfig().contains("knockback")) {
                    teleportkitpvp.add(p.getName());
                    scheduler = Bukkit.getServer().getScheduler();
                    scheduler.scheduleSyncDelayedTask(this, new Runnable() {
                        public void run() {
                            Player p1 = (Player) sender;
                            World w = Bukkit.getServer().getWorld(wkitpvp.this.getConfig().getString("knockback.world"));
                            double x = wkitpvp.this.getConfig().getDouble("knockback.x");
                            double y = wkitpvp.this.getConfig().getDouble("knockback.y");
                            double z = wkitpvp.this.getConfig().getDouble("knockback.z");
                            Location lobby = new Location(w, x, y, z);
                            lobby.setPitch((float) wkitpvp.this.getConfig().getDouble("knockback.pitch"));
                            lobby.setYaw((float) wkitpvp.this.getConfig().getDouble("knockback.yaw"));
                            p1.teleport(lobby);
                            wkitpvp.teleportkitpvp.remove(p.getName());
                            p1.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("knockback-teleport")));
                            p1.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("leave-msg")));
                            ItemStack vareta = new ItemStack(Material.STICK, 1);
                            ItemMeta varetaMeta = vareta.getItemMeta();
                            varetaMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
                            vareta.setItemMeta(varetaMeta);
                            ItemStack helmet = new ItemStack(Material.AIR);
                            ItemStack armor = new ItemStack(Material.AIR);
                            ItemStack legs = new ItemStack(Material.AIR);
                            ItemStack boots = new ItemStack(Material.AIR);
                            new ItemStack(Material.AIR);
                            p.getEquipment().setHelmet(helmet);
                            p.getEquipment().setChestplate(armor);
                            p.getEquipment().setLeggings(legs);
                            p.getEquipment().setBoots(boots);
                            p.getInventory().clear();

                            for (int i = 1; i < 2; ++i) {
                                p.getInventory().addItem(new ItemStack[]{vareta});
                            }

                        }
                    });
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("knockback-not-set")));
                }
            }
            if (command.getName().equalsIgnoreCase("leave")) {
                if (this.getConfig().contains("leave")) {
                    teleportkitpvp.add(p.getName());
                    scheduler = Bukkit.getServer().getScheduler();
                    scheduler.scheduleSyncDelayedTask(this, new Runnable() {
                        public void run() {
                            Player p1 = (Player) sender;
                            World w = Bukkit.getServer().getWorld(wkitpvp.this.getConfig().getString("leave.world"));
                            double x = wkitpvp.this.getConfig().getDouble("leave.x");
                            double y = wkitpvp.this.getConfig().getDouble("leave.y");
                            double z = wkitpvp.this.getConfig().getDouble("leave.z");
                            Location lobby = new Location(w, x, y, z);
                            lobby.setPitch((float) wkitpvp.this.getConfig().getDouble("leave.pitch"));
                            lobby.setYaw((float) wkitpvp.this.getConfig().getDouble("leave.yaw"));
                            p1.teleport(lobby);
                            teleportkitpvp.remove(p.getName());
                            p1.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("leave-teleport")));
                            p.getInventory().clear();


                        }
                    });
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("lava-not-set")));
                }
            } else if (command.getName().equalsIgnoreCase("wkadmin")) {
                if (p.hasPermission("KitPvPwarpsN.set")) {
                    if (args.length == 0) {
                        p.getPlayer().sendMessage("§c------- KitpvpWarpsN Admin -----------");
                        p.getPlayer().sendMessage(" ");
                        p.getPlayer().sendMessage("§b- /wkset leave: §cSet exit from warps.");
                        p.getPlayer().sendMessage(" ");
                        p.getPlayer().sendMessage("§b- /wkset fps: §cSet warp fps.");
                        p.getPlayer().sendMessage(" ");
                        p.getPlayer().sendMessage("§b- /wkset lavachallenge: §cSet warp lavachallenge.");
                        p.getPlayer().sendMessage(" ");
                        p.getPlayer().sendMessage("§b- /wkset knockback: §cSet warp knockback.");
                        p.getPlayer().sendMessage(" ");
                        p.getPlayer().sendMessage("§b- /wk reload: §cReload messages.");
                        p.getPlayer().sendMessage(" ");
                        p.getPlayer().sendMessage("§c------------------------------");
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("Permission")));
                }


                //WK SET
            } else if (command.getName().equalsIgnoreCase("wkset")) {
                if (p.hasPermission("KitPvPwarpsN.set")) {
                    if (args.length == 0) {
                        p.getPlayer().sendMessage(ChatColor.RED + "Use : fps, lavachallenge e leave");
                    } else if (args[0].equalsIgnoreCase("fps")) {
                        this.getConfig().set("fps.x", p.getLocation().getX());
                        this.getConfig().set("fps.y", p.getLocation().getY());
                        this.getConfig().set("fps.z", p.getLocation().getZ());
                        this.getConfig().set("fps.pitch", p.getLocation().getPitch());
                        this.getConfig().set("fps.yaw", p.getLocation().getYaw());
                        this.getConfig().set("fps.world", p.getLocation().getWorld().getName());
                        this.saveConfig();
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("fps-set")));
                    } else if (args[0].equalsIgnoreCase("lavachallenge")) {
                        this.getConfig().set("lava.x", p.getLocation().getX());
                        this.getConfig().set("lava.y", p.getLocation().getY());
                        this.getConfig().set("lava.z", p.getLocation().getZ());
                        this.getConfig().set("lava.pitch", p.getLocation().getPitch());
                        this.getConfig().set("lava.yaw", p.getLocation().getYaw());
                        this.getConfig().set("lava.world", p.getLocation().getWorld().getName());
                        this.saveConfig();
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("lava-set")));
                    } else if (args[0].equalsIgnoreCase("knockback")) {
                        this.getConfig().set("knockback.x", p.getLocation().getX());
                        this.getConfig().set("knockback.y", p.getLocation().getY());
                        this.getConfig().set("knockback.z", p.getLocation().getZ());
                        this.getConfig().set("knockback.pitch", p.getLocation().getPitch());
                        this.getConfig().set("knockback.yaw", p.getLocation().getYaw());
                        this.getConfig().set("knockback.world", p.getLocation().getWorld().getName());
                        this.saveConfig();
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("knockback-set")));
                    } else if (args[0].equalsIgnoreCase("leave")) {
                        this.getConfig().set("leave.x", p.getLocation().getX());
                        this.getConfig().set("leave.y", p.getLocation().getY());
                        this.getConfig().set("leave.z", p.getLocation().getZ());
                        this.getConfig().set("leave.pitch", p.getLocation().getPitch());
                        this.getConfig().set("leave.yaw", p.getLocation().getYaw());
                        this.getConfig().set("leave.world", p.getLocation().getWorld().getName());
                        this.saveConfig();
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("leave-set")));
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("Permission")));
                }
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', msgConfig.getString("console-cmd")));
        }
        return true;
    }
}


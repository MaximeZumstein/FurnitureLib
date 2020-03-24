package de.Ste3et_C0st.FurnitureLib.main;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.RegisteredServiceProvider;

public class PermissionHandler {

    public boolean VaultInstalled = false;
    private net.milkbowl.vault.permission.Permission permission = null;

    public PermissionHandler() {
        if (Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            VaultInstalled = true;
            setupPermissions();
        }
    }

    public boolean hasPerm(CommandSender sender, String str) {
        if (sender == null) return true;
        if (str == null || str.isEmpty()) return true;
        if (sender.isOp()) return true;
        str = str.toLowerCase();
        if (!VaultInstalled) {
            if (sender.hasPermission("furniture.admin")) return true;
            return sender.hasPermission(str);
        } else {
            if (permission.has(sender, "furniture.admin")) return true;
            return permission.has(sender, str);
        }
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<net.milkbowl.vault.permission.Permission> permissionProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    public static boolean registerPermission(String name) {
		return registerPermission(name, PermissionDefault.TRUE);
	}
	
	public static boolean registerPermission(String name, PermissionDefault defaultState) {
		try {
			Bukkit.getPluginManager().addPermission(new Permission(name, defaultState));
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}

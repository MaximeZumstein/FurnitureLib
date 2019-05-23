package de.Ste3et_C0st.FurnitureLib.Command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import de.Ste3et_C0st.FurnitureLib.Crafting.Project;
import de.Ste3et_C0st.FurnitureLib.main.FurnitureLib;

public class TabCompleterHandler implements TabCompleter {
	private FurnitureLib lib;
	private boolean b = true;

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(sender instanceof Player){
			if(cmd!=null&&cmd.getName().equalsIgnoreCase("furniture")){
				if(args.length==1){
					String s = args[0];
					return getTabCompleter(sender, s);
				}
				if(args.length==2){
					String s = args[1];
					
				}
			}
		}
		return null;
	}

	private List<String> getTabCompleter(CommandSender sender, String s){
		List<String> strAL = new ArrayList<String>();
		command.commands.stream()
			.filter(cmd -> cmd.getSubCommand().toLowerCase().contains(s.toLowerCase()))
			.filter(cmd -> sender.hasPermission(cmd.getFormatedPerms()))
			.forEach(cmd -> strAL.add(cmd.getSubCommand()));
		return strAL;
	}
	
	private List<String> getProjectPlugins(List<String> s){
		for(Project pro : lib.getFurnitureManager().getProjects()){
			if(!s.contains(pro.getPlugin().getName())){
				s.add(pro.getPlugin().getName());
			}
		}
		return s;
	}
	
	private List<String> getProjectNames(){
		List<String> projectName = new ArrayList<String>();
		for(Project pro : lib.getFurnitureManager().getProjects()){
			if(!projectName.contains(pro.getName())){
				projectName.add(pro.getName());
			}
		}
		return projectName;
	}
	
	private List<String> getModels(){
		List<String> projectName = new ArrayList<String>();
		for(Project pro : lib.getFurnitureManager().getProjects()){
			if(!projectName.contains(pro.getName())){
				if(pro.isEditorProject()){
					projectName.add(pro.getName());
				}
			}
		}
		return projectName;
	}
}

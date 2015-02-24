package dk.muj.derius.api;

import org.bukkit.command.CommandSender;

public interface Req extends com.massivecraft.massivecore.cmd.req.Req
{
	// -------------------------------------------- //
	// ABSTRACT
	// -------------------------------------------- //
	
	// SKill requirements
	public abstract boolean apply(CommandSender sender, Skill skill);
	public abstract  String createErrorMessage(CommandSender sender, Skill skill);
	
	// Ability requirements
	public abstract  boolean apply(CommandSender sender, Ability ability);
	public abstract  String createErrorMessage(CommandSender sender, Ability ability);
	
	// Verboose level
	public abstract VerboseLevel getVerboseLevel();

}

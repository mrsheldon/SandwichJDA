/**
 * @author MrSheldon
 */

package utils;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public abstract class SandwichCommand extends Command {
    public String usage = "";
    public int ID;

    protected void execute(CommandEvent event) {
    }
}

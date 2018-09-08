/**
 * @author MrSheldon
 */

package commands.fun;

import utils.SandwichCommand;
import com.jagrosh.jdautilities.command.CommandEvent;

public class RollCommand extends SandwichCommand {
    public RollCommand() {
        this.name = "roll";
        this.help = "Roll the sandwich dice";
        this.usage = "!roll";
        this.category = new Category("Fun");
        this.guildOnly = true;
        this.ID = 43;
    }
    protected void execute(CommandEvent event) {
        event.reply(String.valueOf(((int)(Math.random()*6)+1)));
    }
}

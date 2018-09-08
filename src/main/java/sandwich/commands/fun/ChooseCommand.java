/**
 * @author MrSheldon
 */

package commands.fun;

import utils.SandwichCommand;
import com.jagrosh.jdautilities.command.CommandEvent;

public class ChooseCommand extends SandwichCommand {
    public ChooseCommand() {
        this.name = "choose";
        this.help = "Chooses a random item out of a list";
        this.usage = "!choose 1 2 3 4";
        this.category = new Category("Fun");
        this.guildOnly = true;
        this.ID = 41;
    }
    protected void execute(CommandEvent event) {
        if (event.getArgs().equals("")) {
            event.reply("Correct use: " + this.usage);
        } else {
            event.reply(event.getArgs().split("\\s+")[(int)(Math.random()*event.getArgs().split("\\s+").length)]);
        }
    }
}

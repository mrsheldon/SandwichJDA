/**
 * @author MrSheldon
 */

package commands.fun;

import utils.SandwichCommand;
import com.jagrosh.jdautilities.command.CommandEvent;

public class SayCommand extends SandwichCommand {
    public SayCommand() {
        this.name = "say";
        this.help = "Make the bot say something";
        this.usage = "!say Helloooo";
        this.category = new Category("Fun");
        this.guildOnly = true;
        this.aliases = new String[]{"echo"};
        this.ID = 44;
    }
    protected void execute(CommandEvent event) {
        event.reply(event.getArgs().equals("") ? "Message cannot be empty!" : event.getArgs());
    }
}

/**
 * @author MrSheldon
 */

package commands.general;

import utils.SandwichCommand;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.JDA;

public class PingCommand extends SandwichCommand {
    public PingCommand() {
        this.name = "ping";
        this.help = "It will return pong! :O";
        this.usage = "!ping";
        this.category = new Category("General");
        this.guildOnly = true;
        this.ID = 13;
    }
    protected void execute(CommandEvent event) {
        JDA jda = event.getJDA();
        event.getChannel().sendMessage("Pong! `" + jda.getPing() + "ms`").complete();
    }
}
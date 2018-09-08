/**
 * @author MrSheldon
 */

package commands.fun;

import utils.SandwichCommand;
import com.jagrosh.jdautilities.command.CommandEvent;

public class RandomCommand extends SandwichCommand {
    public RandomCommand() {
        this.name = "random";
        this.help = "Generates a random number";
        this.usage = "!random 1 10";
        this.category = new Category("Fun");
        this.guildOnly = true;
        this.ID = 42;
    }
    protected void execute(CommandEvent event) {
        if (event.getArgs().equals("")) {
            event.reply(String.valueOf(((int)(Math.random()*20000000))-10000000));
        } else if (event.getArgs().split("\\s+").length==2) {
            try {
                int start = Integer.parseInt(event.getArgs().split("\\s+")[0]);
                int end = Integer.parseInt(event.getArgs().split("\\s+")[1]);
                int diff = Math.abs(start-end);
                event.reply(String.valueOf(((int)(Math.random()*diff))+start));
            } catch (Exception ignored) {}
        } else {
            event.reply("Correct use: " + this.usage);
        }
    }
}

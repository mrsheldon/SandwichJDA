/**
 * @author MrSheldon
 */

package commands.fun;

import com.jagrosh.jdautilities.command.CommandEvent;
import utils.SandwichCommand;
import java.util.Random;

public class EightballCommand extends SandwichCommand {
    public EightballCommand() {
        this.name = "8ball";
        this.help = "Ask the magic 8ball a question";
        this.usage = "!8ball am I kewl?";
        this.category = new Category("Fun");
        this.guildOnly = true;
        this.ID = 45;
    }
    protected void execute(CommandEvent event) {
        String[] answers = {
                "Absolutly!",
                "Yes, sure.",
                "For sure, dude!",
                "Maybe...",
                "Nah...",
                "No, really, no..."
        };
        Integer randInt = new Random().nextInt(6);
        if (event.getArgs() == "") {
            event.reply("Please ask a question");
            return;
        }
        event.reply(answers[randInt]);
    }
}

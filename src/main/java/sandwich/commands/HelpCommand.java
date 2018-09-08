/**
 * @author MrSheldon
 */

package commands;

import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import utils.Config;
import utils.SandwichCommand;
import com.jagrosh.jdautilities.command.Command;
import java.util.function.Consumer;

public class HelpCommand implements Consumer<CommandEvent> {

    public void accept(CommandEvent event) {

        if (event.getArgs().equals("") || event.getArgs().equalsIgnoreCase("all")) {
            EmbedBuilder builder = new EmbedBuilder().setTitle("I am a sandwich!");
            String output = "Use " + Config.getPrefix() + "help <commandname> for details";
            String currentCategory = "";
            for (Command comm : event.getClient().getCommands()) {
                String cat = comm.getCategory().getName();
                if (currentCategory != cat) {
                    output += "\n**" + cat +"**\n";
                    currentCategory = cat;
                }
                output += "`" + comm.getName() + "`, ";
            }
            builder.setDescription(output);
            builder.setThumbnail(event.getSelfUser().getEffectiveAvatarUrl());
            builder.addField("Invite Me!", "[Click Me!](https://discordapp.com/api/oauth2/authorize?client_id=" + Config.BOTID + "&permissions=8&scope=bot)", true);
            builder.addField("Support Server", event.getClient().getServerInvite(), false);
            builder.setFooter(event.getClient().getCommands().size() + " commands", event.getJDA().getSelfUser().getAvatarUrl());
            event.reply(builder.build());
            return;
        } else {
            for (Command comm : event.getClient().getCommands()) {
                SandwichCommand command = (SandwichCommand) comm;
                if (command.getName().equalsIgnoreCase(event.getArgs())) {
                    EmbedBuilder builder = new EmbedBuilder()
                            .setTitle("Command " + command.getName() + ":")
                            .setDescription(command.getHelp())
                            .addField("Category", command.getCategory().getName(), true)
                            .addField("Usage", command.usage, true)
                            .setFooter("Command ID: " + command.ID, null);
                    event.reply(builder.build());
                    return;
                }
            }
            event.reply("The " + event.getArgs() + " command wasn't found, try !help");
            return;
        }
    }
}


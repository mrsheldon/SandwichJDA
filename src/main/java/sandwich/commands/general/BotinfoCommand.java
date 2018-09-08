/**
 * @author MrSheldon
 */

package commands.general;

import utils.SandwichCommand;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import utils.Config;

public class BotinfoCommand extends SandwichCommand {
    public BotinfoCommand() {
        this.name = "botinfo";
        this.help = "Gives some useful bot statistics";
        this.usage = "!botinfo";
        this.category = new Category("General");
        this.guildOnly = true;
        this.aliases = new String[]{"stats"};
        this.ID = 11;
    }
    protected void execute(CommandEvent event) {
        JDA jda = event.getJDA();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setThumbnail(jda.getSelfUser().getAvatarUrl());
        embed.addField("Bot Name", jda.getSelfUser().getName(), true);
        embed.addField("Bot Prefix", Config.getPrefix(), true);
        embed.addField("Version", Config.VERSION, true);
        embed.addField("Developer", "MrSheldon#0001", true);
        embed.addField("Server Count", String.valueOf(jda.getGuilds().size()), true);
        embed.addField("User Count", String.valueOf(jda.getUsers().size()), true);
        event.getChannel().sendMessage(embed.build()).queue();
    }
}
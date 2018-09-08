/**
 * @author MrSheldon
 */

package commands.information;

import utils.SandwichCommand;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;

import java.time.format.DateTimeFormatter;

public class ServerinfoCommand extends SandwichCommand {
    public ServerinfoCommand() {
        this.name = "serverinfo";
        this.help = "Display relevant server information";
        this.usage = "!serverinfo";
        this.category = new Category("Information");
        this.guildOnly = true;
        this.aliases = new String[]{"guildinfo"};
        this.ID = 21;
    }

    protected void execute(CommandEvent event) {
        Guild guild = event.getGuild();
        String creationDate = guild.getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("Serverinfo", guild.getIconUrl());
        embed.setThumbnail(guild.getIconUrl());
        embed.addField("Name:", guild.getName(), true);
        embed.addField("ID:", guild.getId(), true);
        embed.addField("Owner:", guild.getOwner().getEffectiveName(), true);
        embed.addField("Region:", guild.getRegion().getEmoji() + " " + guild.getRegion().getName(), true);
        embed.addField("Member Count:", String.valueOf(guild.getMembers().size()), true);
        embed.addField("Verification Level:", guild.getVerificationLevel().name(), true);
        embed.addField("Channel Count:", String.valueOf(guild.getTextChannels().size()), true);
        embed.addField("Role Count:", String.valueOf(guild.getRoles().size()), true);
        embed.addField("Creation Date:", creationDate, false);
        event.getChannel().sendMessage(embed.build()).queue();
    }
}
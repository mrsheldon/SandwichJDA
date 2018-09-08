/**
 * @author MrSheldon
 */

package commands.information;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import utils.SandwichCommand;

import java.util.List;

public class AvatarCommand extends SandwichCommand {
    public AvatarCommand() {
        this.name = "avatar";
        this.help = "Fetches a user's avatar.";
        this.usage = "!avatar <@user>";
        this.category = new Command.Category("Information");
        this.guildOnly = true;
        this.ID = 23;
    }
    protected void execute(CommandEvent event) {
        List<User> Members = event.getMessage().getMentionedUsers();
        Member mem;
        if (Members.size()==0) {
            mem = event.getMember();
        } else {
            mem = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
        }
        EmbedBuilder embed = new EmbedBuilder().setImage(mem.getUser().getAvatarUrl());
        event.getChannel().sendMessage(embed.build()).queue();
    }
}

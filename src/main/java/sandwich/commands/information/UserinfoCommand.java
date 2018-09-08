/**
 * @author MrSheldon
 */

package commands.information;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.Role;
import utils.SandwichCommand;

import java.util.List;
import java.time.format.DateTimeFormatter;

public class UserinfoCommand extends SandwichCommand {
    public UserinfoCommand() {
        this.name = "userinfo";
        this.help = "Display relevant user information";
        this.usage = "!userinfo <@user>";
        this.category = new Command.Category("Information");
        this.guildOnly = true;
        this.ID = 22;
    }
    protected void execute(CommandEvent event) {
        List<User> Members = event.getMessage().getMentionedUsers();
        Member mem;
        if (Members.size()==0) {
            mem = event.getMember();
        } else {
            mem = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
        }
        String game;
        try {
            game = mem.getGame().getName();
        } catch (Exception e) {
            game = "None";
        }
        String roles = "";
        for ( Role r : mem.getRoles() ) {
            roles += r.getName() + ", ";
        }
        if (roles.length() > 0)
            roles = roles.substring(0, roles.length()-2);
        else
            roles = "No roles on this server.";
        String joinDate = mem.getJoinDate().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        String creationDate = mem.getUser().getCreationTime().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(mem.getUser().getName() + "#" + mem.getUser().getDiscriminator());
        embed.setThumbnail(mem.getUser().getAvatarUrl());
        embed.addField("Name:", mem.getUser().getName() + "#" + mem.getUser().getDiscriminator(), true);
        embed.addField("ID:", mem.getUser().getId(), true);
        embed.addField("Status:", mem.getOnlineStatus().getKey(), true);
        embed.addField("Game:", game, true);
        embed.addField("Join Date:", joinDate, false);
        embed.addField("Creation Date:", creationDate, false);
        embed.addField("Roles:", roles, false);
        event.getChannel().sendMessage(embed.build()).queue();
    }
}

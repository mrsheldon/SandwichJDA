/**
 * @author MrSheldon
 */

package commands.moderation;

import utils.SandwichCommand;
import net.dv8tion.jda.core.Permission;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.User;
import java.util.List;
import net.dv8tion.jda.core.entities.Member;

public class KickCommand extends SandwichCommand {
    public KickCommand() {
        this.name = "kick";
        this.help = "Kick a user from the server";
        this.usage = "!kick @user";
        this.category = new Category("Moderation");
        this.guildOnly = true;
        this.userPermissions = new Permission[]{Permission.KICK_MEMBERS};
        this.botPermissions = new Permission[]{Permission.KICK_MEMBERS};
        this.ID = 31;
    }
    protected void execute(CommandEvent event) {
        if (!event.getMember().hasPermission(Permission.MANAGE_SERVER)) {
            event.reply("You are not a moderator.");
        }
        List<User> Users = event.getMessage().getMentionedUsers();
        User User;
        if (Users.size()==0) {
            User = event.getJDA().getUserById(event.getArgs());
            if (User==null) {
                event.reply("No users was mentioned");
                return;
            }
        } else {
            User = Users.get(0);
        }
        if (event.getGuild().getMember(User).hasPermission(Permission.MANAGE_SERVER)) {
            event.reply("This user is a moderator, you can't use this command on him.");
            return;
        }
        Member FinalUser = event.getGuild().getMember(User);
        event.reply(FinalUser.getAsMention() + " has been kicked.");
        event.getGuild().getController().kick(FinalUser).queue();
    }
}

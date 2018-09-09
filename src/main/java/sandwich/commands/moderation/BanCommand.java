/**
 * @author MrSheldon
 */

package commands.moderation;

import utils.SandwichCommand;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

public class BanCommand extends SandwichCommand {
    public BanCommand() {
        this.name = "ban";
        this.help = "ban a user from the server";
        this.usage = "!ban @user";
        this.category = new Category("Moderation");
        this.guildOnly = true;
        this.userPermissions = new Permission[]{Permission.BAN_MEMBERS};
        this.botPermissions = new Permission[]{Permission.BAN_MEMBERS};
        this.ID = 32;
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
        event.getGuild().getController().ban(FinalUser, 0, "Ban by " + event.getMessage().getAuthor().getName() + "#" + event.getMessage().getAuthor().getDiscriminator()).queue();
        event.reply(FinalUser.getAsMention() + " has been banned.");
    }
}

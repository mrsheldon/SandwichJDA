/**
 * @author MrSheldon
 */

package commands.moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import utils.SandwichCommand;

import java.util.List;

public class UnmuteCommand extends SandwichCommand {
    public UnmuteCommand() {
        this.name = "unmute";
        this.help = "Unmute a user";
        this.usage = "!unmute @user";
        this.category = new Command.Category("Moderation");
        this.guildOnly = true;
        this.ID = 33;
        this.userPermissions = new Permission[]{Permission.KICK_MEMBERS};
        this.botPermissions = new Permission[]{Permission.KICK_MEMBERS};
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
        Role mutedrole = null;
        for(Role role : event.getGuild().getRoles())
            if(role.getName().equalsIgnoreCase("muted"))
            {
                mutedrole = role;
                break;
            }
        if(mutedrole==null)
        {
            event.reply("No \"Muted\" role exists! Please add and setup up a \"Muted\" role.");
            return;
        }
        Member FinalUser = event.getGuild().getMember(User);
        try {
            event.getGuild().getController().removeRolesFromMember(FinalUser, mutedrole).queue();
            event.reply(FinalUser.getAsMention() + " has been unmuted.");
            return;
        } catch(Exception e) {
            event.reply("Failed to unmute " + FinalUser.getAsMention());
            return;
        }
    }
}
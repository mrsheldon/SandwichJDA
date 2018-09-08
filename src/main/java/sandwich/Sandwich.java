/**
 * @author MrSheldon
 */

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import commands.moderation.*;
import commands.information.*;
import commands.general.*;
import commands.fun.*;
import commands.currency.*;
import commands.*;
import utils.Config;

public class Sandwich {
    public static void main(String[] args) throws Exception {
        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setGame(Game.playing(Config.GAME));
        builder.setPrefix(Config.getPrefix());
        builder.setAlternativePrefix("<@"+Config.BOTID+"> ");
        builder.setOwnerId(Config.OWNERID);
        builder.setServerInvite(Config.SERVERINVITE);
        builder.addCommands(
                // General Commands
                new PingCommand(),
                new BotinfoCommand(),
                // Information Commands
                new ServerinfoCommand(),
                new UserinfoCommand(),
                new AvatarCommand(),
                // Moderation Commands
                new KickCommand(),
                new BanCommand(),
                // Currency Commands
                new BalanceCommand(),
                // Fun Commands
                new ChooseCommand(),
                new RollCommand(),
                new RandomCommand(),
                new SayCommand(),
                new EightballCommand()
        );
        builder.setHelpConsumer(new HelpCommand());
        CommandClient client = builder.build();
        JDABuilder bot = new JDABuilder(AccountType.BOT);
        bot.setToken(Config.TOKEN);
        bot.setGame(Game.playing(Config.GAME));
        bot.addEventListener(client);
        bot.buildBlocking();
    }
}
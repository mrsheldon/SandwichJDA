/**
 * @author MrSheldon
 */

package commands.currency;

import utils.SandwichCommand;
import com.jagrosh.jdautilities.command.CommandEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.Config;

public class BalanceCommand extends SandwichCommand {
    public BalanceCommand() {
        this.name = "balance";
        this.help = "Shows the amount of money you have";
        this.usage = "!balance";
        this.category = new Category("Currency");
        this.guildOnly = true;
        this.aliases = new String[]{"bal", "money"};
        this.ID = 51;
    }
        @Override
        protected void execute(CommandEvent event) {
            Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + Config.DB);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE id = " + event.getMessage().getAuthor().getId());
            while(rs.next()) {
                String balance = rs.getString("balance");
                event.getChannel().sendMessage("$" + balance).queue();
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if(connection != null)
                    connection.close();
            } catch(SQLException e) {
                System.err.println(e);
            }
        }
    }
}

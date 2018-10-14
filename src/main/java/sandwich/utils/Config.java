/**
 * @author MrSheldon
 */

package utils;

public class Config {
    public static final boolean isBeta = true;
    public static final String VERSION = "1.0.0";
    public static final String BETA_PREFIX = "!";
    public static final String NORMAL_PREFIX = "s!";
    public static final String TOKEN = "";
    public static final String BOTID = "479049776814358531";
    public static final String OWNERID = "231733082804322304";
    public static final String SERVERINVITE = "https://discord.gg/76PAmCv";
    public static final String GAME = getPrefix() + "help";
    public synchronized static String getPrefix() {
        if(!Config.isBeta) return NORMAL_PREFIX;
        else return BETA_PREFIX;
    }
}

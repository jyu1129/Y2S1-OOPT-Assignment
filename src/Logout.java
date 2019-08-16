import java.text.SimpleDateFormat;
import java.util.Date;

public class Logout {
    private String username;

    public Logout() {
        this.username = "";
    }

    // Logout time
    public String currentTime() {
        Date now = new Date();

        SimpleDateFormat formatTime = new SimpleDateFormat("hh.mm aa");

        String loginTime = formatTime.format(now);

        return loginTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

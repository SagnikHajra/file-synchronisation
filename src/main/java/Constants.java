import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final int SERVER_TCP_PORT = 6000;
    public static final int SERVER_UDP_PORT_CLIENT_ONE = 11111;
    public static final int SERVER_UDP_PORT_CLIENT_TWO = 11112;
    public static final String[][] LOCAL_DIRS = {
                                                {// user facing dirs
                                                        "C:\\Users\\Sagnik Hajra\\Desktop\\file-sync-testing\\server_files",
                                                        "C:\\Users\\Sagnik Hajra\\Desktop\\file-sync-testing\\client_one_files",
                                                        "C:\\Users\\Sagnik Hajra\\Desktop\\file-sync-testing\\client_two_files"
                                                },
                                                { // backup dirs. Needed to compare files with user facing dirs for changes
                                                        "C:\\Users\\Sagnik Hajra\\Desktop\\file-sync-testing\\backup\\server",
                                                        "C:\\Users\\Sagnik Hajra\\Desktop\\file-sync-testing\\client_one",
                                                        "C:\\Users\\Sagnik Hajra\\Desktop\\file-sync-testing\\client_two"
                                                }
                                            };

    public static final String CRLF = "\r\n";

}

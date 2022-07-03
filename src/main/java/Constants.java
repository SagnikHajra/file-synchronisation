import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final int SERVER_TCP_PORT = 6000;
    public static final int SERVER_UDP_PORT_CLIENT_ONE = 11111;
    public static final int SERVER_UDP_PORT_CLIENT_TWO = 11112;
    public static final String[][] LOCAL_DIRS = {
                                                {// user facing dirs
                                                        "G:\\My Drive\\UTA\\Sem5\\ASE\\file-sync\\server_files",
                                                        "G:\\My Drive\\UTA\\Sem5\\ASE\\file-sync\\client_one_files",
                                                        "G:\\My Drive\\UTA\\Sem5\\ASE\\file-sync\\client_two_files"
                                                },
                                                { // backup dirs. Needed to compare files with user facing dirs for changes
                                                        "G:\\My Drive\\UTA\\Sem5\\ASE\\file-sync\\backup\\server",
                                                        "G:\\My Drive\\UTA\\Sem5\\ASE\\file-sync\\backup\\client_one",
                                                        "G:\\My Drive\\UTA\\Sem5\\ASE\\file-sync\\backup\\client_two"
                                                }
                                            };

    public static final String CRLF = "\r\n";

}

import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class CIDRBlockOverlapFinder {
    private String[] ips;
    private static final String IPV4_REGEX ="^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\"+".(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."+"(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."+"(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\/"+"(2[0-4]|[01]?[0-9]?)$";

    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);

    public CIDRBlockOverlapFinder(String[] ips){
        this.ips = ips;
    }
    public String[][] getIpsByBlock(){
        
    }
    public void display(){
        if(ips != null){
            for(int i=0; i<ips.length ;i++){
                System.out.print(ips[i]);
            }
        }
    }
    private boolean validateIp(String ip){
        if(ip == null){
            return false;
        }
        Matcher m = IPv4_PATTERN.matcher(ip);
        return m.matches();
    }
}

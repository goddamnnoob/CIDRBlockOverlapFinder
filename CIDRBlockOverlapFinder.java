import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class CIDRBlockOverlapFinder {
    private String[] ips;
    private static final String IPV4_REGEX ="^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\/([0-9]{1,2})$";
    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);

    public CIDRBlockOverlapFinder(String[] ips){
        this.ips = ips;
    }
    public String[][] getIpsByBlock(){
        int[] uqi = new int[ips.length];
    }
    private boolean finOverlap(String ip1,String ip2){
    } 
    private int[] extractor(String ip){
        int[] ex = new int[4];
        Matcher m = IPv4_PATTERN.matcher(ip);
        if (m.matches()) {
            ex[0] = Integer.parseInt(m.group(1));
            ex[1] = Integer.parseInt(m.group(2));
            ex[2] = Integer.parseInt(m.group(3));
            ex[3] = Integer.parseInt(m.group(4));
        }
        return ex;
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

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
        for(int i=0;i<ips.length-1;i++){
            Boolean t = false;
            for(int j=0;j<uqi.length;j++){
                if(ips[i] == uqi[j]){
                    t = true;
                }
            }
            if(t== false){
                
            }
        }
    }
    private boolean findOverlap(int[] ip1,int[] ip2){
        int mask1 = ip1[5];
        int mask2 = ip2[5];
        int mask1fulloctets = mask1/8;
        int mask2fulloctets = mask2/8;
        int mask1remoctet = mask1 % 8;
        int mask2remoctet = mask2 % 8;
        int[] lip1 = new int[5];
        int[] lip2 = new int[5];
        for(int i=0;i<mask1fulloctets;i++){
            lip1[i] = 255 & ip1[i];
        }
        for(int i=0;i<mask2fulloctets;i++){
            lip2[i] = 255 & ip2[i];
        }
        String t1 = "";
        for(int i=0;i<mask1remoctet;i++){
            t1 += "1";
        }
        String t2 = "";
        for(int i=0;i<mask1remoctet;i++){
            t2 += "1";
        }
        int hs1 = 8 - mask1remoctet;
        int hs2 = 8 - mask2remoctet;
        for(int i=0;i<hs1;i++){
            t1 += "0";
        }
        for(int i=0; i<hs2;i++){
            t2 += "0";
        }
        int a1 = Integer.parseInt(t1,2) & lip1[mask1fulloctets];
        int a2 = Integer.parseInt(t2,2) & lip2[mask2fulloctets ];
        char[] doc1 = Integer.toBinaryString(a1).toCharArray();
        char[] doc2 = Integer.toBinaryString(a2).toCharArray();
        for(int i=8-hs1-1;i<8;i++){
            doc1[i] = "1";
        }
        for(int i=8-hs2-1;i<8;i++){
            doc2[i] = "1";
        }
        lip1[mask1fulloctets +1] = Integer.parseInt(String.valueOf(doc1),2);
        lip2[mask2fulloctets +1] = Integer.parseInt(String.valueOf(doc2),2);
        for(int i=mask1fulloctets+1;i<4;i++){
            if(lip1[i-1]){
                lip1[i-1] = 255;
            }
        }
        for(int i=mask2fulloctets+1;i<4;i++){
            if(lip2[i-1]){
                lip2[i-1] = 255;
            }
        }
        if(mask1<mask2){
            if(a1 <= lip2[mask2fulloctets] && lip2[mask2fulloctets] <= lip1[mask1fulloctets] ){
                Boolean temp = true;
                for(int i=0;i<mask1fulloctets;i++){
                    if(lip1[i] != lip2[i]){
                        temp = false; 
                    }
                }
                return temp;
            }
            else{
                if(a2 <= lip1[mask1fulloctets] && lip1[mask1fulloctets] <= lip2[mask2fulloctets] ){
                    Boolean temp = true;
                    for(int i=0;i<mask2fulloctets;i++){
                        if(lip1[i] != lip2[i]){
                            temp = false; 
                        }
                    }
                    return temp;
                }
            }
        }

    } 
    private int[] extractor(String ip){
        int[] ex = new int[4];
        Matcher m = IPv4_PATTERN.matcher(ip);
        if (m.matches()) {
            ex[0] = Integer.parseInt(m.group(1));
            ex[1] = Integer.parseInt(m.group(2));
            ex[2] = Integer.parseInt(m.group(3));
            ex[3] = Integer.parseInt(m.group(4));
            ex[5] = Integer.parseInt(m.group(5));
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

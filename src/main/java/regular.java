import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eli9 on 3/31/2017.
 */
public class regular {
    public static void getResult(){
        String strAndNum = "ReferenceId=201703302050";
        String regex = "\\d*";//"[^0-9]"; //"(\\d+)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(strAndNum);
        if(m.find()) {
            System.out.println(m.group(0).trim());
        }
    }

    public static void main(String[] args) {
        getResult();
    }
}

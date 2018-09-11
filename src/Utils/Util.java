package Utils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Util {


    /*
     *  获取用户头像
     */
    public static String getAvatarUrl(String qq) throws Exception{
        String url = "https://ptlogin2.qq.com/getface?&imgtype=1&uin=" + qq;
        String res = readFileByUrl(url);
        String pattern = "&k=.+&s";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(res);
        if (m.find()){
            return "https://q1.qlogo.cn/g?b=qq" + m.group(0) + "=100";
        }else{
            return null;
        }
    }

    /**
     * 获取指定URL的内容
     */
    private static String readFileByUrl(String urlStr) throws Exception{
            //创建一个URL实例
            URL url = new URL(urlStr);
            //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is,"utf-8");
            //为字符输入流添加缓冲
            BufferedReader br = new BufferedReader(isr);
            return br.readLine();
    }

}

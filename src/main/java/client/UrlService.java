package client;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class UrlService {
    /*2.
    public int countInUrl(string httpUrl, String word) <- policzenie ile razy znajduje sie slowo word w zrodle strony httpUrl
    + tessty jednostkowe
     */
    public int countInUrl(String httpUrl, String word) {
        int result = 0;
        try {
            InputStreamReader inR = new InputStreamReader(ProtocolControl.setInputStream(httpUrl));
            BufferedReader in = new BufferedReader(inR);
            result = in.lines().map(l -> countSelectedWord(l, word)).reduce(Integer::sum).orElse(0);
            in.close();
        } catch (NullPointerException | IOException e) {
            return 0;
        }
        return result;
    }

    private int countSelectedWord(String source, String word) {
        int counter = 0;
        String tmp = "";
        if (source.toLowerCase().contains(word.toLowerCase())) {
            counter++;
            tmp = source.toLowerCase().replaceFirst(word.toLowerCase(), "");
            counter += countSelectedWord(tmp, word);
        }
        return counter;
    }

}

class ProtocolControl {

    public static InputStream setInputStream(String httpUrl) {
        InputStream inStr;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection conn = getHttpContent(url);
            String encoding = conn.getContentEncoding();
            // create the appropriate stream wrapper based on the encoding type
            if (encoding != null && encoding.equalsIgnoreCase("gzip")) {
                inStr = new GZIPInputStream(conn.getInputStream());
            } else if (encoding != null && encoding.equalsIgnoreCase("deflate")) {
                inStr = new InflaterInputStream(conn.getInputStream(),
                        new Inflater(true));
            } else {
                inStr = conn.getInputStream();
            }
        } catch (IOException e) {
            return null;
        }
        return inStr;
    }

    private static HttpURLConnection getHttpContent(URL url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(true);
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            return conn;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

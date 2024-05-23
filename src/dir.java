import java.net.http.HttpClient;


public static void main(String [ ] args) {
String[] strArray2 = {"zacm00re","B","poiwutopiuqrt"}; 
}

public static void dir(String url, String[] wordlist, ) {
    int TIMEOUT_DURATION = 2;

    HttpClient client = HttpClient.newBuilder()
        .version(Version.HTTP_1_1)
        .followRedirects(Redirect.NORMAL)
        .connectTimeout(Duration.ofSeconds(TIMEOUT_DURATION))
        .build();

    for (int word = 0; i < word.length; ++i) {
        String requestUrl = url + "/" + wordlist[word];
        System.out.println(requestUrl);
        var res = client.send(get(uri), BodyHandlers.ofString());
        System.out.println(res.statusCode());
    }
}
package crawler;

import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;

public class Crawler<T> {

    private String URLaddress;

    private final ReadableByteChannel channel;

    private final URL url;

    private final ByteBuffer buffer;

    private final StringBuilder rawData;

    private final CrawlerFilter<T> filter;

    public String getURLaddress() {
        return URLaddress;
    }

    public String getRawData() throws IOException {
        if (rawData.length() == 0) {
            downLoadData();
        }
        return rawData.toString();
    }

    public Crawler(String URLaddress, CrawlerFilter<T> filter) throws IOException {
        this.URLaddress = URLaddress;
        url = new URL(this.URLaddress);
        channel = Channels.newChannel(url.openStream());
        buffer = ByteBuffer.allocate(10240);
        rawData = new StringBuilder();
        this.filter = filter;
        downLoadData();
    }

    private void downLoadData() throws IOException {
        buffer.clear();
        int len = 0;
        String strBuffer = null;
        while ((len = channel.read(buffer)) != -1) {
            strBuffer = new String(Arrays.copyOf(buffer.array(), len));
            rawData.append(strBuffer);
            buffer.clear();
        }
    }

    public T getData() throws IOException {
        return filter.filterRule(rawData.toString());
    }

}

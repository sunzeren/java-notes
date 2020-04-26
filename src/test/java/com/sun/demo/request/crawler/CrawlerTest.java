package com.sun.demo.request.crawler;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @Author: ZeRen.
 * @Date: 2020/4/24 14:21
 * 爬取微信公众号到本地
 */
public class CrawlerTest {


    // 爬取资源所在的基础目录
    private static final String FILE_PATH_PREFIX = "C:\\Users\\admin\\Desktop\\crawler\\";
    // 对应基础目录,下的子分类,用于区分每个爬取的文章
    private static String FILE_PATH_DIR = null;

    public static void main(String[] args) {

        String[] links = new String[]{
                "https://mp.weixin.qq.com/s/8Oo8PhijFxdjEYepZcNejw",
                "https://mp.weixin.qq.com/s/3uCQj0-WBXENZyr1mQ1mZQ",
                "https://mp.weixin.qq.com/s/DFrqILXsVa8C8iXmNJWJfg",
                "https://mp.weixin.qq.com/s/2Z7XhZ_BD0khQblIVoCvsw",
                "https://mp.weixin.qq.com/s/ejr8sNE-CTDHHdPDW9Ed2g",
        };

        for (int i = 0, linksLength = links.length; i < linksLength; i++) {
            String link = links[i];
            final CrawlerTask task = new CrawlerTask();
            task.setId(String.valueOf(i + 1));
            task.setLink(link);
            crawling(task);
        }
    }


    private static void crawling(CrawlerTask crawlerTask) {
        try {
            Document document = Jsoup.connect(crawlerTask.getLink()).get();

            Elements style = document.getElementsByTag("style");
            Element articleTitle = document.getElementById("activity-name");
            Element source = document.getElementById("js_name");
            Element content = document.getElementById("js_content");
            // 初始化生成文件目录
            initFileDir(articleTitle);

            if (content != null) {
                Elements imgElements = content.getElementsByTag("img");
                imgElements.forEach(img -> {
                    // 此处替换图片为本地,以避免直接访问微信图片时会被拦截,导致图片无法加载
                    String imgUrl = saveFile(img.attr("data-src"), String.valueOf(System.currentTimeMillis()) + ".png");
                    img.attr("data-src", imgUrl);
                });

                String contentData = content.html().replaceAll("%3A", ":").replaceAll("%2F", "/").replaceAll("%3F", "?").replaceAll("%3D", ".").replaceAll("data-(.*?=)", "$1");
                crawlerTask.setContent(contentData);
            }
            if (articleTitle != null) {
                crawlerTask.setTitle(articleTitle.text());
            }
            if (source != null) {
                crawlerTask.setSource(source.text());
            }

            String fileName = null;
            String styles = style.stream().map(Element::data).collect(Collectors.joining());
            if (StringUtils.isNotEmpty(styles)) {
                int retryCount = 0;
                do {
                    fileName = saveFile(IOUtils.toInputStream(styles, StandardCharsets.UTF_8), crawlerTask.getId() + ".css");
                    retryCount++;
                } while (StringUtils.isEmpty(fileName) && retryCount <= 3);
            }
            crawlerTask.setStyle(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(crawlerTask.getContent());

        GenerateHtmlUtils.generate(crawlerTask.getContent(), FILE_PATH_PREFIX + FILE_PATH_DIR + "\\index.html", crawlerTask.getTitle());
    }

    private static void initFileDir(Element titleElement) {
        if (titleElement == null) {
            return;
        }
        final String nowDate = DateFormatUtils.format(new Date(), "yyyy-MMdd");
        final String title = titleElement.text();
        FILE_PATH_DIR = title + "(" + nowDate + ")";
    }

    private static String saveFile(InputStream inputStream, String fileName) {

        try {
            return writeToFile(inputStream, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String writeToFile(InputStream inputStream, String fileName) throws IOException {
        final File file = getDownloadFile(fileName);
        final FileOutputStream outputStream = new FileOutputStream(file);
        byte[] buff = new byte[1024 * 1000];
        while (inputStream.read(buff) != -1) {
            outputStream.write(buff);
        }
        outputStream.flush();
        return file.getAbsolutePath();
    }

    private static File getDownloadFile(String fileName) {
        final String fileDir = FILE_PATH_PREFIX + FILE_PATH_DIR;
        final File dir = new File(fileDir);
        if (dir.exists()) {
            dir.delete();
        } else {
            dir.mkdirs();
        }

        return new File(fileDir + "\\" + fileName);
    }

    private static String saveFile(String imageUrl, String fileName) {
        try {
            final URL url = new URL(imageUrl);
            final BufferedImage read = ImageIO.read(url);
            final File file = getDownloadFile(fileName);
            ImageIO.write(read, "png", file);
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

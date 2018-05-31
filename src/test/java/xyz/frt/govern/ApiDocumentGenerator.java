package xyz.frt.govern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

/**
 * @author phw
 * @date Created in 05-30-2018
 * @description
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ApiDocumentGenerator {

    /**
     * 项目路径
     */
    private static final String baseDir = System.getProperty("user.dir");
    /**
     * index.adoc文件路径
     */
    private static final String adocPath = "/src/main/asciidoc/index.adoc";
    /**
     * 生成的snippets文件路径
     */
    private static final String adocDir = "/target/generated-snippets";
    private static final String[] adocPreFiles = {
            "curl.request.adoc",
            "http-request.adoc",
            "http-response.adoc",
            "httpie-request.adoc",
            "request-body.adoc",
            "http-parameters.adoc",
            "response-body.adoc",
            "response-fields.adoc",
            "request-headers.adoc"
    };
    /**
     * 请求概要
     */
    private static final String HTTP_REQUEST_ADOC = "http-request.adoc";
    /**
     * 请求响应
     */
    private static final String HTTP_RESPONSE_ADOC = "http-response.adoc";
    /**
     * 请求体
     */
    private static final String REQUEST_BODY_ADOC = "request-body.adoc";
    /**
     * 请求参数说明
     */
    private static final String REQUEST_PARAMETERS_ADOC = "request-parameters.adoc";
    /**
     * 响应结果
     */
    private static final String RESPONSE_BODY_ADOC = "response-body.adoc";
    /**
     * 响应参数说明
     */
    private static final String RESPONSE_FIELDS_ADOC = "response-fields.adoc";
    /**
     * 请求头参数说明
     */
    private static final String REQUEST_HEADERS_ADOC = "request-headers.adoc";

    @Test
    public void generateDocuments() throws IOException {
        File adocDirFile = new File(baseDir + adocDir);

        String docTitle = "综治项目接口文档";
        String docNote = "综治项目接口文档";

        StringBuilder content = new StringBuilder();
        content.append("= ").append(docTitle)
                .append("\n")
                .append(":toc: left")
                .append("\n\n")
                .append(docNote)
                .append("\n\n");
        for (File dir : adocDirFile.listFiles()) {//遍历模块目录
            System.out.println("/" + dir.getName());
            content.append("== ").append(dir.getName()).append("\n\n");
            for (File dir1 : dir.listFiles()) {//遍历Api目录
                System.out.println("/" + dir.getName() + "/" + dir1.getName());
                content.append("=== ").append(dir1.getName()).append("\n\n");
                File[] files = dir1.listFiles();
                for (File adoc : files) {//遍历adoc文件
                    String adocFilePath = "/" + dir.getName() + "/" + dir1.getName() + "/" + adoc.getName();
                    System.out.println(adocFilePath);
                    if (adoc.getName().equals(REQUEST_HEADERS_ADOC)) {
                        content.append(".Request Header").append("\n");
                        content.append("include::{snippets}/")
                                .append(dir.getName())
                                .append("/").append(dir1.getName())
                                .append("/").append(adoc.getName())
                                .append("[]\n");
                    }
                    if (adoc.getName().equals(HTTP_REQUEST_ADOC)) {
                        content.append(".Http Request").append("\n");
                        content.append("include::{snippets}/")
                                .append(dir.getName())
                                .append("/").append(dir1.getName())
                                .append("/").append(adoc.getName())
                                .append("[]\n");
                    }
                    if (adoc.getName().equals(REQUEST_PARAMETERS_ADOC)) {
                        content.append(".Request Parameters").append("\n");
                        content.append("include::{snippets}/")
                                .append(dir.getName())
                                .append("/").append(dir1.getName())
                                .append("/").append(adoc.getName())
                                .append("[]\n");
                    }
                    if (adoc.getName().equals(HTTP_RESPONSE_ADOC)) {
                        content.append(".Http Response").append("\n");
                        content.append("include::{snippets}/")
                                .append(dir.getName())
                                .append("/").append(dir1.getName())
                                .append("/").append(adoc.getName())
                                .append("[]\n");
                    }
                    if (adoc.getName().equals(RESPONSE_FIELDS_ADOC)) {
                        content.append(".Response Fields").append("\n");
                        content.append("include::{snippets}/")
                                .append(dir.getName())
                                .append("/").append(dir1.getName())
                                .append("/").append(adoc.getName())
                                .append("[]\n");
                    }
                }
            }
        }

        File indexAdocFile = new File(baseDir + adocPath);

        if (indexAdocFile.exists()) {
            indexAdocFile.delete();
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(indexAdocFile)));
        writer.write(content.toString());
        writer.flush();
        writer.close();
        System.out.println("Document Generator Successful!");
    }


}

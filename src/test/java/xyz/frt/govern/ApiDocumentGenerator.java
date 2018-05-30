package xyz.frt.govern;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.frt.govern.common.BaseUtils;

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
            "response-fields.adoc"
    };
    /**
     * 请求概要
     */
    private static final String HTTP_REQUEST_ADOC = "http-request.adoc";
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

    //@Test
    public void generateDocuments() throws IOException {
        File adocDirFile = new File(baseDir + adocDir);
        File[] dirs = adocDirFile.listFiles();
        if (BaseUtils.isNullOrEmpty(dirs)) {
            return;
        }

        String docTitle = "综治项目接口文档";
        String docNote = "综治项目接口文档";

        StringBuilder content = new StringBuilder();
        content.append("= ").append(docTitle).append( "==")
                .append("\n\n").append(docNote).append("\n\n");
        for (File dir: dirs) {
            content.append("== ").append(dir.getName()).append(" ==").append("\n\n");
            content.append(".Request\n");
            content.append("include::{snippets}/").append(dir.getName()).append("/").append("http-request.adoc[]\n");
            //content.append("include::{snippets}/").append(dir.getName()).append("/").append("curl-request.adoc[]\n");
            //content.append("include::{snippets}/").append(dir.getName()).append("/").append("httpie-request.adoc[]\n");
            content.append("include::{snippets}/").append(dir.getName()).append("/").append("request-body.adoc[]\n");
            content.append(".Response\n");
            content.append("include::{snippets}/").append(dir.getName()).append("/").append("http-response.adoc[]\n");
            content.append("include::{snippets}/").append(dir.getName()).append("/").append("response-body.adoc[]\n\n");
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

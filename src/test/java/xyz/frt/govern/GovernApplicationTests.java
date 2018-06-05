package xyz.frt.govern;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import xyz.frt.govern.common.AppConst;
import xyz.frt.govern.common.JsonResult;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GovernApplication.class)
public class GovernApplicationTests {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    protected WebApplicationContext context;

    private static final String MODULE = "Home/";

    protected static String TOKEN = null;

    private MockMvc mockMvc;

    protected FieldDescriptor[] basic_json_desc = new FieldDescriptor[] {
            fieldWithPath(AppConst.KEY_CODE).description("响应码"),
            fieldWithPath(AppConst.KEY_MSG).description("响应消息"),
            fieldWithPath(AppConst.KEY_DATA_MAP).description("响应数据集")
    };

    protected FieldDescriptor[] basic_entity_desc = new FieldDescriptor[]{
            fieldWithPath("dataMap.data").description("相应数据")
            ,fieldWithPath("dataMap.data.id").description("ID")
            ,fieldWithPath("dataMap.data.creator").description("创建人")
            ,fieldWithPath("dataMap.data.creatorId").description("创建人ID")
            ,fieldWithPath("dataMap.data.createTime").description("创建时间")
            ,fieldWithPath("dataMap.data.isEnable").description("是否启用")
            ,fieldWithPath("dataMap.data.remark").description("备注")
    };

    @Before
    public void initializing() throws Exception {
        mockMvc = buildMockMvc(context);
        buildSecurityManager(context);
        signIn();
    }

    protected MockMvc buildMockMvc(WebApplicationContext context) {
        return MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    private void buildSecurityManager(WebApplicationContext context) {
        SecurityManager securityManager = (SecurityManager) context.getBean("securityManager");
        SecurityUtils.setSecurityManager(securityManager);
    }

    @Test
    public void signIn() throws Exception {
        String username = "admin";
        String password = "admin";
        MvcResult result = mockMvc.perform(post("/sign-in")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param(AppConst.KEY_USERNAME, username)
                .param(AppConst.KEY_PASSWORD, password))
                .andExpect(status().isOk())
                .andDo(document("用户模块/登录",
                        requestParameters(
                                parameterWithName(AppConst.KEY_USERNAME).description("用户名"),
                                parameterWithName(AppConst.KEY_PASSWORD).description("密码")
                        ),
                        responseFields(
                                fieldWithPath(AppConst.KEY_CODE).description("响应码"),
                                fieldWithPath(AppConst.KEY_MSG).description("响应信息"),
                                fieldWithPath(AppConst.KEY_DATA_MAP).description("响应数据集"),
                                fieldWithPath(AppConst.KEY_DATA_MAP + AppConst.VALUE_DOTE + AppConst.KEY_TOKEN).description("用户令牌")
                        )))
                .andReturn();
        String response = result.getResponse().getContentAsString();
        JsonResult jsonResult = JSON.parseObject(response, JsonResult.class);
        TOKEN = (String) jsonResult.getDataMap().get(AppConst.KEY_TOKEN);
    }

    @Test
    public void home() throws Exception {
        mockMvc.perform(get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(MODULE + "home",
                        responseFields(
                                basic_json_desc
                        )));
    }

    @Test
    public void _401() throws Exception {
        mockMvc.perform(get("/401")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(MODULE + "401",
                        responseFields(
                                basic_json_desc
                        )));
    }

    @Test
    public void _403() throws Exception {
        mockMvc.perform(get("/403")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(MODULE + "403",
                        responseFields(
                                basic_json_desc
                        )));
    }

    @Test
    public void _404() throws Exception {
        mockMvc.perform(get("/404")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(MODULE + "404",
                        responseFields(
                                basic_json_desc
                        )));
    }

}

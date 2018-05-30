package xyz.frt.govern.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import xyz.frt.govern.GovernApplicationTests;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends GovernApplicationTests {

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = buildMockMvc(context);
    }

    @Test
    public void findByPrimaryKey() {
    }

    @Test
    public void signIn() {
    }

    @Test
    public void signUp() throws Exception {
        mockMvc.perform(post("/sign-up")
                .param("username", "MockMvc")
                .param("password", "mock666")
                .param("phone", "18977288714")
                .param("code", "font")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("sign-up",

                        requestParameters(
                                parameterWithName("username").description("用户名，必须"),
                                parameterWithName("password").description("密码，必须"),
                                parameterWithName("phone").description("联系电话"),
                                parameterWithName("code").description("验证码")
                        ),
                        responseFields(
                                fieldWithPath("code").description("200:成功, 401:认证失败, 403:拒绝访问, 404:请求不存在"),
                                fieldWithPath("msg").description("响应消息"),
                                fieldWithPath("dataMap").description("响应数据集")
                        )));


    }

    @Test
    public void signOut() {
    }

    @Test
    public void updatePass() {
    }

    @Test
    public void findPass() {
    }

    @Test
    public void findUsers() {
    }

    @Test
    public void findUsers1() {
    }

    @Test
    public void smsCode() {
    }
}
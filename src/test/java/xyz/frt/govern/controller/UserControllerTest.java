package xyz.frt.govern.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import xyz.frt.govern.GovernApplicationTests;
import xyz.frt.govern.common.AppConst;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends GovernApplicationTests {

    private static final String MODULE = "用户模块/";

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = buildMockMvc(context);
    }

    @Test
    public void findByPrimaryKey() throws Exception {
        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isOk())
                .andDo(document(MODULE + "根据ID加载用户",
                        pathParameters(
                                parameterWithName(AppConst.KEY_ID).description("用户ID")),
                        responseFields(
                                fieldWithPath("code").description("响应码"),
                                fieldWithPath("msg").description("响应消息"),
                                fieldWithPath("dataMap").description("响应数据集")
                        ))
                );
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
                .andDo(document(MODULE + "用户注册",

                        requestParameters(
                                parameterWithName("username").description("用户名，必须"),
                                parameterWithName("password").description("密码，必须"),
                                parameterWithName("phone").description("联系电话"),
                                parameterWithName("code").description("验证码")
                        ),
                        responseFields(
                                fieldWithPath("code").description("响应码"),
                                fieldWithPath("msg").description("响应消息"),
                                fieldWithPath("dataMap").description("响应数据集")
                        ))
                );


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
    public void findAllUsers() throws Exception {
        mockMvc.perform(get("/users").header(
                AppConst.KEY_AUTHORIZATION, TOKEN
        )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(MODULE + "获取所有用户",
                        requestHeaders(
                                headerWithName(AppConst.KEY_AUTHORIZATION).description("用户认证token")
                        )
                ));
    }

    @Test
    public void smsCode() {
    }
}
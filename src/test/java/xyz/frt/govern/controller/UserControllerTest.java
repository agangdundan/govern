package xyz.frt.govern.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import xyz.frt.govern.GovernApplicationTests;
import xyz.frt.govern.common.AppConst;
import xyz.frt.govern.common.BaseUtils;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends GovernApplicationTests {

    private static final String MODULE = "用户模块/";

    private MockMvc mockMvc;

    private FieldDescriptor[] user_desc = new FieldDescriptor[] {
            fieldWithPath(AppConst.KEY_DATA_MAP_DOTE_DATA + ".username").description("用户名")
            ,fieldWithPath(AppConst.KEY_DATA_MAP_DOTE_DATA + ".header").description("头像图片")
            ,fieldWithPath(AppConst.KEY_DATA_MAP_DOTE_DATA + ".trueName").description("真实姓名")
            ,fieldWithPath(AppConst.KEY_DATA_MAP_DOTE_DATA + ".password").description("用户密码")
            ,fieldWithPath(AppConst.KEY_DATA_MAP_DOTE_DATA + ".salt").description("盐值")
            ,fieldWithPath(AppConst.KEY_DATA_MAP_DOTE_DATA + ".phone").description("联系电话")
    };

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
                        responseFields(BaseUtils.mergeArrays(basic_json_desc, basic_entity_desc, user_desc)))
                );
    }

    @Test
    public void signUp() throws Exception {
        mockMvc.perform(post("/sign-up")
                .param(AppConst.KEY_USERNAME, "MockMvc")
                .param(AppConst.KEY_PASSWORD, "mock666")
                .param(AppConst.KEY_PHONE, "18977288714")
                .param(AppConst.KEY_CODE, "font")
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
                                basic_json_desc
                        ))
                );


    }

    @Test
    public void signOut() throws Exception {
        mockMvc.perform(get("/sign-out")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(MODULE + "用户注册",
                        responseFields(
                                basic_json_desc
                        ))
                );

    }

    @Test
    public void updatePass() throws Exception {
        mockMvc.perform(patch("/edit-pass")
                .header(AppConst.KEY_AUTHORIZATION, TOKEN)
                .param(AppConst.KEY_OLD_PASS, "admin")
                .param(AppConst.KEY_NEW_PASS, "admin")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(MODULE + "修改密码",

                        requestParameters(
                                parameterWithName(AppConst.KEY_OLD_PASS).description("原密码"),
                                parameterWithName(AppConst.KEY_NEW_PASS).description("新密码")
                        ),

                        responseFields(
                                basic_json_desc
                        ))
                );

    }

    @Test
    public void findPass() throws Exception {
        mockMvc.perform(get("/find-pass")
                .param(""));
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
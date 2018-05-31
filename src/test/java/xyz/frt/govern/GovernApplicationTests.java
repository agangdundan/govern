package xyz.frt.govern;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class GovernApplicationTests {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    protected WebApplicationContext context;

    private static final String MODULE = "Home/";

    protected static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ4eXouZnJ0IiwiaWF0IjoxNTI3NzUxMTI0LCJleHAiOjE1Mjc3NTQ3MjQsInVzZXJuYW1lIjoiYWRtaW4iLCJpZCI6IjEifQ.uMWaw7019hUdr9Ira35HFyiHEB3Cs_RPHhV_TOOGIIg";

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = buildMockMvc(context);
    }

    protected MockMvc buildMockMvc(WebApplicationContext context) {
        return MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void home() throws Exception {
        mockMvc.perform(get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(MODULE + "home"));
    }

    @Test
    public void _401() throws Exception {
        mockMvc.perform(get("/401")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(MODULE + "401"));
    }

    @Test
    public void _403() throws Exception {
        mockMvc.perform(get("/403")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(MODULE + "403"));
    }

    @Test
    public void _404() throws Exception {
        mockMvc.perform(get("/404")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document(MODULE + "404"));
    }

}

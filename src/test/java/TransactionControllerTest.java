import controller.TransactionController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import service.TransactionService;

import static controller.TransactionController.BASE_URL;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author shakhawat.hossain
 * @since 12/26/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TransactionController.class, TransactionService.class, MappingJackson2HttpMessageConverter.class})
public class TransactionControllerTest extends TransactionTest {

    private MockMvc mockMvc;

    @Autowired
    private TransactionController transactionController;

    @Autowired
    private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(this.transactionController)
                .setMessageConverters(this.jackson2HttpMessageConverter)
                .build();
    }

    @Test
    public void test_getTransaction() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/transaction/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.amount").exists())
                .andExpect(jsonPath("$.type").exists())
                .andExpect(jsonPath("$.parentId").doesNotExist())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.amount").value(100))
                .andExpect(jsonPath("$.type").value("shopping"));

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/transaction/4").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.amount").exists())
                .andExpect(jsonPath("$.type").exists())
                .andExpect(jsonPath("$.parentId").exists())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.amount").value(4000))
                .andExpect(jsonPath("$.type").value("car"))
                .andExpect(jsonPath("$.parentId").value(3)).andDo(print());
    }

    @Test
    public void test_getTransactionIds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/types/car").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3))).andDo(print());

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/types/discount").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1))).andDo(print());
    }

    @Test
    public void test_getTotalTransactionAmount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/sum/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sum").value(9799.5)).andDo(print());

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/sum/5").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sum").value(5000)).andDo(print());
    }

//    @Test
    public void test_addTransaction() throws Exception {
        String transactionJsonStr = "{\"amount\" : \"1200\", \"type\" : \"travelling\" }";
        String transactionJsonStr2 = "{\"amount\" : \"-100\", \"type\" : \"food\", \"parentId\" : \"12\" }";

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/transaction/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionJsonStr)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("ok")).andDo(print());

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/transaction/13")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionJsonStr2)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("ok")).andDo(print());
    }
}
package com.example.demo;

import com.example.demo.bean.Book;
import com.example.demo.controller.DemoController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * 速度比较慢
 * Created by wangjianhua3 on 2018/3/15.
 */
//JUnit类运行器，会为JUnit测试加载Spring应用程序上下文，并为测试类自动织入所需的Bean。
@RunWith(SpringJUnit4ClassRunner.class)
//能测单个注解类，对于request无法注入 //加载配置文件
@ContextConfiguration(classes = {DemoController.class})
//自动注解spring容器
@WebAppConfiguration
public class DemoTest {
    @Autowired
    private WebApplicationContext webContext;//自动注解spring容器
    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .build();
    }

    /**
     * success
     * @see MockMvcResultMatchers#model() #attributeExists(String)
     * @throws Exception
     */
    @Test
    public void homePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.request().attribute("username",Matchers.nullValue()));
    }

    @Test
    public void postBook() throws Exception {
        mockMvc.perform(post("/readingList")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "BOOK TITLE")
                .param("author", "BOOK AUTHOR")
                .param("isbn", "1234567890")
                .param("description", "DESCRIPTION"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/reading"));


        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setReader("craig");
        expectedBook.setTitle("BOOK TITLE");
        expectedBook.setAuthor("BOOK AUTHOR");
        expectedBook.setIsbn("1234567890");
        expectedBook.setDescription("DESCRIPTION");
        mockMvc.perform(get("/reading"))
                .andExpect(status().isOk())
                .andExpect(view().name("reading"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", hasSize(1)))
                .andExpect(model().attribute("books",
                        contains(samePropertyValuesAs(expectedBook))));
    }


    @Autowired
    private DemoController demoController;

    /**
     * request未注入
     */
    @Test
    public void testLoginUser() {
    }

}

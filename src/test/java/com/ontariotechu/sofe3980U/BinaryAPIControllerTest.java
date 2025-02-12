package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("10001"));
    }

    @Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    @Test
    public void addWithLeadingZeros() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "00011").param("operand2", "00001"))
                .andExpect(status().isOk())
                .andExpect(content().string("100"));
    }

    @Test
    public void addWithZeroOperand() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "0").param("operand2", "1011"))
                .andExpect(status().isOk())
                .andExpect(content().string("1011"));
    }

    @Test
    public void addJsonWithDifferentBinaryLengths() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "101").param("operand2", "10011"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(10011))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(11000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    @Test
    public void orOperation() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "1010").param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(content().string("1110"));
    }

    @Test
    public void orJsonOperation() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1", "1010").param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1100))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1110))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    @Test
    public void andOperation() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "1010").param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(content().string("1000"));
    }

    @Test
    public void andJsonOperation() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1", "1010").param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1100))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }

    @Test
    public void multiplyOperation() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "101").param("operand2", "11"))
                .andExpect(status().isOk())
                .andExpect(content().string("1111"));
    }

    @Test
    public void multiplyJsonOperation() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1", "101").param("operand2", "11"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(11))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }

    @Test
    public void orWithDifferentLengthOperands() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "101").param("operand2", "11011"))
                .andExpect(status().isOk())
                .andExpect(content().string("11111"));
    }

    @Test
    public void andWithDifferentLengthOperands() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "101").param("operand2", "11011"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    public void multiplyWithZero() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "1010").param("operand2", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    public void multiplyWithOne() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "1011").param("operand2", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("1011"));
    }
}

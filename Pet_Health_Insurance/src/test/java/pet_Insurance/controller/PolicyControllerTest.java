package pet_Insurance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pet_Insurance.controllers.PolicyController;
import pet_Insurance.dto.PolicyResponse;
import pet_Insurance.service.PolicyService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PolicyController.class)
class PolicyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PolicyService policyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void applyPolicy_returnsPolicyResponse() throws Exception {
        PolicyResponse response = new PolicyResponse("p123", 1200.0, false);
        when(policyService.applyPolicy("pet123")).thenReturn(response);

        mockMvc.perform(post("/api/policies/apply/pet123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value("p123"))
                .andExpect(jsonPath("$.premiumAmountInINR").value(1200.0))
                .andExpect(jsonPath("$.claimed").value(false));
    }

    @Test
    void claimPolicy_returnsPolicyResponse() throws Exception {
        PolicyResponse response = new PolicyResponse("p789", 1400.0, true);
        when(policyService.claimPolicy("policy789")).thenReturn(response);

        mockMvc.perform(post("/api/policies/claim/policy789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value("p789"))
                .andExpect(jsonPath("$.premiumAmountInINR").value(1400.0))
                .andExpect(jsonPath("$.claimed").value(true));
    }
}

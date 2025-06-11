package pet_Insurance.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PolicyResponseTest {

    @Test
    void testEquals_sameObject() {
        PolicyResponse p = new PolicyResponse("P1", 1000.0, false);
        assertThat(p).isEqualTo(p);
    }

    @Test
    void testEquals_equalObjects() {
        PolicyResponse p1 = new PolicyResponse("P1", 1000.0, false);
        PolicyResponse p2 = new PolicyResponse("P1", 1000.0, false);
        assertThat(p1).isEqualTo(p2);
    }

    @Test
    void testEquals_differentPolicyId() {
        PolicyResponse p1 = new PolicyResponse("P1", 1000.0, false);
        PolicyResponse p2 = new PolicyResponse("P2", 1000.0, false);
        assertThat(p1).isNotEqualTo(p2);
    }

    @Test
    void testEquals_null() {
        PolicyResponse p = new PolicyResponse("P1", 1000.0, false);
        assertThat(p.equals(null)).isFalse();
    }

    @Test
    void testEquals_differentClass() {
        PolicyResponse p = new PolicyResponse("P1", 1000.0, false);
        assertThat(p.equals("string")).isFalse();
    }

    @Test
    void testHashCode_consistency() {
        PolicyResponse p = new PolicyResponse("P1", 1000.0, false);
        int hash1 = p.hashCode();
        int hash2 = p.hashCode();
        assertThat(hash1).isEqualTo(hash2);
    }

    @Test
    void testNoArgsConstructorAndSettersGetters() {
        PolicyResponse response = new PolicyResponse();
        response.setPolicyId("POL123");
        response.setPremiumAmountInINR(1500.0);
        response.setClaimed(true);

        assertThat(response.getPolicyId()).isEqualTo("POL123");
        assertThat(response.getPremiumAmountInINR()).isEqualTo(1500.0);
        assertThat(response.isClaimed()).isTrue();
    }

    @Test
    void testAllArgsConstructor() {
        PolicyResponse response = new PolicyResponse("POL456", 2000.0, false);

        assertThat(response.getPolicyId()).isEqualTo("POL456");
        assertThat(response.getPremiumAmountInINR()).isEqualTo(2000.0);
        assertThat(response.isClaimed()).isFalse();
    }

    @Test
    void testToStringNotEmpty() {
        PolicyResponse response = new PolicyResponse("POL789", 1750.0, true);
        String toString = response.toString();

        assertThat(toString).isNotNull().isNotEmpty();
        assertThat(toString).contains("POL789");
    }

    @Test
    void testEqualsAndHashCode() {
        PolicyResponse r1 = new PolicyResponse("POL111", 1200.0, true);
        PolicyResponse r2 = new PolicyResponse("POL111", 1200.0, true);
        PolicyResponse r3 = new PolicyResponse("POL222", 1300.0, false);

        // Reflexive
        assertThat(r1).isEqualTo(r1);
        // Symmetric
        assertThat(r1).isEqualTo(r2);
        assertThat(r2).isEqualTo(r1);
        // Transitive
        PolicyResponse r4 = new PolicyResponse("POL111", 1200.0, true);
        assertThat(r1).isEqualTo(r4);
        assertThat(r2).isEqualTo(r4);
        // Not equal to null
        assertThat(r1).isNotEqualTo(null);
        // Not equal to other type
        assertThat(r1).isNotEqualTo("some string");
        // Not equal to different values
        assertThat(r1).isNotEqualTo(r3);

        // hashCode
        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
    }
}

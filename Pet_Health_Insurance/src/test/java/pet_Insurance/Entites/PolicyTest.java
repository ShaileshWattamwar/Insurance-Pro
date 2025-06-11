package pet_Insurance.Entites;

import org.junit.jupiter.api.Test;
import pet_Insurance.entities.Policy;

import static org.assertj.core.api.Assertions.assertThat;

public class PolicyTest {

    @Test
    void testAllArgsConstructor() {
        Policy policy = new Policy("1", "pet123", 2500.0, true);
        assertThat(policy.getId()).isEqualTo("1");
        assertThat(policy.getPetId()).isEqualTo("pet123");
        assertThat(policy.getPremiumAmountInINR()).isEqualTo(2500.0);
        assertThat(policy.isClaimed()).isTrue();
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        Policy policy = new Policy();
        policy.setId("2");
        policy.setPetId("pet456");
        policy.setPremiumAmountInINR(3200.0);
        policy.setClaimed(false);

        assertThat(policy.getId()).isEqualTo("2");
        assertThat(policy.getPetId()).isEqualTo("pet456");
        assertThat(policy.getPremiumAmountInINR()).isEqualTo(3200.0);
        assertThat(policy.isClaimed()).isFalse();
    }

    @Test
    void testBuilder() {
        Policy policy = Policy.builder()
                .id("3")
                .petId("pet789")
                .premiumAmountInINR(1500.0)
                .isClaimed(true)
                .build();

        assertThat(policy.getId()).isEqualTo("3");
        assertThat(policy.getPetId()).isEqualTo("pet789");
        assertThat(policy.getPremiumAmountInINR()).isEqualTo(1500.0);
        assertThat(policy.isClaimed()).isTrue();
    }

    @Test
    void testToBuilder() {
        Policy original = Policy.builder()
                .id("4")
                .petId("petX")
                .premiumAmountInINR(5000.0)
                .isClaimed(false)
                .build();

        Policy modified = original.toBuilder()
                .premiumAmountInINR(6000.0)
                .isClaimed(true)
                .build();

        assertThat(modified.getId()).isEqualTo("4");
        assertThat(modified.getPetId()).isEqualTo("petX");
        assertThat(modified.getPremiumAmountInINR()).isEqualTo(6000.0);
        assertThat(modified.isClaimed()).isTrue();
    }

    @Test
    void testEqualsAndHashCode() {
        Policy p1 = new Policy("5", "petZ", 4200.0, true);
        Policy p2 = new Policy("5", "petZ", 4200.0, true);
        Policy p3 = new Policy("6", "petA", 4200.0, false);

        assertThat(p1).isEqualTo(p2);
        assertThat(p1.hashCode()).isEqualTo(p2.hashCode());
        assertThat(p1).isNotEqualTo(p3);
    }

    @Test
    void testEquals_sameReference() {
        Policy policy = new Policy("1", "pet1", 2500.0, true);
        assertThat(policy.equals(policy)).isTrue();
    }

    @Test
    void testEquals_nullComparison() {
        Policy policy = new Policy("1", "pet1", 2500.0, true);
        assertThat(policy.equals(null)).isFalse();
    }

    @Test
    void testEquals_differentClass() {
        Policy policy = new Policy("1", "pet1", 2500.0, true);
        String notPolicy = "Not a policy";
        assertThat(policy.equals(notPolicy)).isFalse();
    }

    @Test
    void testEquals_partialDifference() {
        Policy base = new Policy("id", "petId", 1000.0, true);

        assertThat(base).isNotEqualTo(new Policy("DIFFERENT", "petId", 1000.0, true));
        assertThat(base).isNotEqualTo(new Policy("id", "DIFFERENT", 1000.0, true));
        assertThat(base).isNotEqualTo(new Policy("id", "petId", 999.0, true));
        assertThat(base).isNotEqualTo(new Policy("id", "petId", 1000.0, false));
    }

    @Test
    void testEquals_nullFields() {
        Policy p1 = new Policy(null, null, 0.0, false);
        Policy p2 = new Policy(null, null, 0.0, false);
        assertThat(p1).isEqualTo(p2);
        assertThat(p1.hashCode()).isEqualTo(p2.hashCode());
    }

    @Test
    void testCanEqual() {
        Policy p1 = new Policy("1", "pet1", 2000.0, true);
        Policy p2 = new Policy("1", "pet1", 2000.0, true); // Create equal policy
        assertThat(p1.equals(p2)).isTrue();
        assertThat(p1.equals(null)).isFalse();
        assertThat(p1.equals("string")).isFalse();
    }
    @Test
    void testHashCode_consistent() {
        Policy policy = new Policy("1", "pet1", 2000.0, false);
        assertThat(policy.hashCode()).isEqualTo(policy.hashCode());
    }

    @Test
    void testHashCode_differentObjects() {
        Policy policy1 = new Policy("1", "pet1", 2000.0, false);
        Policy policy2 = new Policy("2", "pet2", 2000.0, true);
        assertThat(policy1.hashCode()).isNotEqualTo(policy2.hashCode());
    }

    @Test
    void testToString() {
        Policy policy = new Policy("7", "petY", 2800.0, false);
        String output = policy.toString();

        assertThat(output).contains("7");
        assertThat(output).contains("petY");
        assertThat(output).contains("2800.0");
        assertThat(output).contains("false");
    }

    @Test
    void testPolicyBuilderToString() {
        Policy.PolicyBuilder builder = Policy.builder()
                .id("P123")
                .petId("PET456")
                .premiumAmountInINR(3000.0)
                .isClaimed(true);

        String builderStr = builder.toString();

        assertThat(builderStr).contains("id=P123");
        assertThat(builderStr).contains("petId=PET456");
        assertThat(builderStr).contains("premiumAmountInINR=3000.0");
        assertThat(builderStr).contains("isClaimed=true");
    }
}

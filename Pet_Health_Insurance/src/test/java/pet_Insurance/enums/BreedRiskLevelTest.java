package pet_Insurance.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BreedRiskLevelTest {

    @Test
    void testEnumValues() {
        BreedRiskLevel[] levels = BreedRiskLevel.values();
        assertArrayEquals(new BreedRiskLevel[]{BreedRiskLevel.LOW, BreedRiskLevel.MEDIUM, BreedRiskLevel.HIGH}, levels);
    }

    @Test
    void testValueOf() {
        assertEquals(BreedRiskLevel.LOW, BreedRiskLevel.valueOf("LOW"));
        assertEquals(BreedRiskLevel.MEDIUM, BreedRiskLevel.valueOf("MEDIUM"));
        assertEquals(BreedRiskLevel.HIGH, BreedRiskLevel.valueOf("HIGH"));
    }

    @Test
    void testInvalidValueOf() {
        assertThrows(IllegalArgumentException.class, () -> BreedRiskLevel.valueOf("INVALID"));
    }
}

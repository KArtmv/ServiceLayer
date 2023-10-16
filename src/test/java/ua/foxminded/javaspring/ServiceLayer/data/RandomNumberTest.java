package ua.foxminded.javaspring.ServiceLayer.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RandomNumberTest {

    @Mock
    private Random random;

    private RandomNumber randomNumber;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        randomNumber = new RandomNumber();
    }

    @Test
    void generateBetweenOneAnd_shouldReturnIntegerValue_whenPassedValidNumber() {
        int passNumber = 10;

        when(random.nextInt(anyInt())).thenReturn(1);

        int result = randomNumber.generateBetweenOneAnd(passNumber);

        assertThat((result > 0) && (result <= passNumber)).isTrue();

        verify(random).nextInt(anyInt());
    }

    @Test
    void generateBetweenOneAnd_shouldReturnIntegerValue_whenPassedNotValidNumber() {
        int passNumber = 0;

        when(random.nextInt(anyInt())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> randomNumber.generateBetweenOneAnd(passNumber));
    }
}

package ua.foxminded.javaspring.ServiceLayer.data;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomNumber {

    private Random random;

    public RandomNumber(Random random) {
        this.random = random;
    }

    public Integer generateBetweenOneAnd(int toNumber) {
        int randomNumber = 0;
        boolean isZero = false;
        while (!isZero) {
            randomNumber = random.nextInt(checkIsNumberValid(toNumber) + 1);
            if (randomNumber != 0) {
                isZero = true;
            }
        }
        return randomNumber;
    }

    private Integer checkIsNumberValid(int numberToCheck){
        if (numberToCheck > 0){
            return numberToCheck;
        } else {
            throw new IllegalArgumentException();
        }
    }
}

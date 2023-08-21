package ua.foxminded.javaspring.ServiceLayer.data;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomNumber {

	private Random random;

	public RandomNumber(Random random) {
		this.random = random;
	}

	public Integer generateBeetwenOneAnd(int toNumber) {
		int randomNumber = 0;
		boolean isZero = false;
		while (!isZero) {
			randomNumber = random.nextInt(toNumber + 1);
			if (randomNumber != 0) {
				isZero = true;
			}
		}
		return randomNumber;
	}
}

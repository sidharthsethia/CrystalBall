package com.crystalball;

import java.util.Random;

public class CrystalBall {
	public String[] mAnswers = { "100 % yes",
			"No way man!!",
			"May be",
			"It is certain",
			"It is decidedly so",
			"All signs say YES",
			"My reply is no",
			"I am highly doubtful",
			"The stars are not aligned",
			"Please sound a little pleasant",
			"Oh, I am not telling you that",
			"Concentrate and try again",
			"I am confused about that"};
	public String getAnswer(){
Random randomGenerator = new Random();
int randomNumber = randomGenerator.nextInt(mAnswers.length);
return mAnswers[randomNumber];
}
}
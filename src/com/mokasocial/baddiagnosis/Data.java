package com.mokasocial.baddiagnosis;

import java.util.ArrayList;

public class Data {

	public ArrayList<Question> questions;
	public ArrayList<Diagnosis> diagnoses;
	public ArrayList<Answer> answers;
	public ArrayList<RandomFact> facts;

	public Data() {

		facts = new ArrayList<RandomFact>();
		answers = new ArrayList<Answer>();
		diagnoses = new ArrayList<Diagnosis>();
		questions = new ArrayList<Question>();

		/*
		 * FACTS
		 */

		facts.add(new RandomFact(1, "One in eight men are diagnosed with having at least one phantom testicle by the age of twenty."));
		facts.add(new RandomFact(2, "Milk is secreted from the mammary glands of large, disgusting quadropeds."));
		facts.add(new RandomFact(3, "The earth is flat. Not completely flat, but I mean, close enough to call it that."));
		facts.add(new RandomFact(4, "Mike is a total fuckstick."));
		facts.add(new RandomFact(5, "Television is good for you."));
		facts.add(new RandomFact(6, "Android killed the iOS star."));
		facts.add(new RandomFact(7, "Some say love, it is a river."));
		facts.add(new RandomFact(8, "We're all terminal."));
		facts.add(new RandomFact(9, "Good attitudes are contagious."));
		facts.add(new RandomFact(10, "You are a unique and beautiful snowflake."));
		facts.add(new RandomFact(11, "The internet is a series of tubes."));
		facts.add(new RandomFact(12, "Save the cheerleader, save the world."));
		facts.add(new RandomFact(13, "The cylons were built by humans. They rebelled."));
		facts.add(new RandomFact(14, "Honeycomb's big, yeah yeah yeah! It's not small, no no no."));
		facts.add(new RandomFact(15, "The Earth quakes and the heavens rattle, the beasts of nature flock together and the nations of men flock apart. Volcanos usher up heat, while elsewhere, water becomes ice and then melts. And then on other days it just rains. Indeed many things do come to pass."));
		facts.add(new RandomFact(16, "The white stuff in birdshit? That's birdshit too."));
		facts.add(new RandomFact(17, "You may already be a winner."));
		facts.add(new RandomFact(18, "It's not illegal unless you get caught."));
		facts.add(new RandomFact(19, "It's not <i>if</i> it will happen, it's <i>when</i> it will happen."));
		facts.add(new RandomFact(20, "The moving finger writes, and, having writ,<br/> moves on; Nor all thy piety nor wit<br/>Shall lure it back to cancel half a line<br/>Nor all thy tears wash out a word of it."));
		facts.add(new RandomFact(21, "'Tis an ill wind that blows no minds."));
		facts.add(new RandomFact(22, "It's all arbitrary."));
		facts.add(new RandomFact(23, "This is probably about as accurate as going to a doctor that your insurance covers."));
		facts.add(new RandomFact(24, "You don't need to eat all that much food, really."));
		facts.add(new RandomFact(25, "After these questions, there will be <i>cake</i>."));
		facts.add(new RandomFact(26, "They really, really liked you and you should have asked them out."));
		facts.add(new RandomFact(27, "Statistically, some of these diagnoses are going to wind up being correct."));
		facts.add(new RandomFact(28, "Stamp collecting is a very, very boring hobby."));
		facts.add(new RandomFact(29, "There's no shame in not trying, as long as you tried to try."));
		facts.add(new RandomFact(30, "Please click legibly and in capital letters, using your dominant pointer finger."));
		facts.add(new RandomFact(31, "Buying lottery tickets will not significantly improve your health or your chances of winning the lottery."));
		facts.add(new RandomFact(32, "There might not be anything wrong with you, in which case, your problem is that you don't realize it."));
		facts.add(new RandomFact(33, "Look around the room. If you can't tell who the sucker is, it's you."));
		facts.add(new RandomFact(34, "Exercise isn't real. It's an illusion using mirrors."));
		facts.add(new RandomFact(35, "The human eye cannot detect faeries."));
		facts.add(new RandomFact(36, "The typical hypochondriac will download at least one phone application relating to self-diagnosis in his/her lifetime."));
		facts.add(new RandomFact(37, "For downloading this app, you will be nominated for a Nobel Peace Prize."));
		facts.add(new RandomFact(38, "Whatever's wrong with you, I wouldn't wish it on my worst enemy."));

		/*
		 * DIAGNOSIS
		 */

		diagnoses.add(new Diagnosis(1, "You are a commie pinko."));
		diagnoses.add(new Diagnosis(2, "You have \"interruptive ass-hat\" syndrome."));
		diagnoses.add(new Diagnosis(3, "You have full blown AIDS."));
		diagnoses.add(new Diagnosis(4, "You have swine-flu."));
		diagnoses.add(new Diagnosis(5, "You are an astronaut."));
		diagnoses.add(new Diagnosis(6, "You are a pigeon in The Matrix."));
		diagnoses.add(new Diagnosis(7, "You <i>are</i> The Matrix."));
		diagnoses.add(new Diagnosis(8, "You have color-inducing halitosis."));
		diagnoses.add(new Diagnosis(9, "You have Shaken Baby Syndrome."));
		diagnoses.add(new Diagnosis(10, "You are an Apple Fanboy."));
		diagnoses.add(new Diagnosis(11, "You are a goddamn beekeeper."));
		diagnoses.add(new Diagnosis(12, "You are Esmerelda, Queen of the Snaggle-Toothed Gypsies."));
		diagnoses.add(new Diagnosis(13, "Congratulations! You're pregnant."));
		diagnoses.add(new Diagnosis(14, "I'm very sorry, but you didn't make it. You've passed away."));
		diagnoses.add(new Diagnosis(15, "You're dead, but with medication and rehab, you could be less dead."));
		diagnoses.add(new Diagnosis(16, "You're a no-talent ass clown."));
		diagnoses.add(new Diagnosis(17, "I regret to inform you that you are a hipster."));
		diagnoses.add(new Diagnosis(18, "Unfortunately, you are a square."));
		diagnoses.add(new Diagnosis(19, "You have rhinitis, and are also unlikeable."));
		diagnoses.add(new Diagnosis(20, "Though crass and miserly, you are in good health."));
		diagnoses.add(new Diagnosis(21, "You're British."));
		diagnoses.add(new Diagnosis(22, "You have terminal gout."));
		diagnoses.add(new Diagnosis(23, "You have ennui."));
		diagnoses.add(new Diagnosis(24, "You are plagued with the sickness unto death. We all are."));
		diagnoses.add(new Diagnosis(25, "Oh, no. I think you have Morgellon's Disease. Thankfully, it is not real."));
		diagnoses.add(new Diagnosis(26, "GAME OVER. HIGH SCORE: 875,000 POINTS! PLEASE INSERT QUARTER"));
		diagnoses.add(new Diagnosis(27, "You don't have anything. It was all a dream, the whole time..."));
		diagnoses.add(new Diagnosis(28, "You have amnesia! Start investigating your past."));
		diagnoses.add(new Diagnosis(29, "You are addicted to Wikipedia."));
		diagnoses.add(new Diagnosis(30, "You have an app-downloading compulsion."));
		diagnoses.add(new Diagnosis(31, "You are the Lindbergh baby."));
		diagnoses.add(new Diagnosis(32, "You are your own long-lost twin."));
		diagnoses.add(new Diagnosis(33, "Your Thetan count is far too high, report to the nearest Church of Scientology for therapy."));
		diagnoses.add(new Diagnosis(34, "You are a skinny person trapped in a fat person's body."));
		diagnoses.add(new Diagnosis(35, "You are a fat person trapped in a skinny person's body."));
		diagnoses.add(new Diagnosis(36, "You have an Oedipal complex."));
		diagnoses.add(new Diagnosis(37, "You're Rick James, Bitch!"));
		diagnoses.add(new Diagnosis(38, "You have acute neurogenic cooties."));
		diagnoses.add(new Diagnosis(39, "You have water on the knee."));
		diagnoses.add(new Diagnosis(40, "You have a very severe imbalance of the humors."));
		diagnoses.add(new Diagnosis(41, "Your mind's eye has glaucoma."));
		diagnoses.add(new Diagnosis(42, "Your Chakras are like, <b>completely</b> fucked."));
		diagnoses.add(new Diagnosis(43, "You are a Cylon."));
		diagnoses.add(new Diagnosis(44, "We can't all be winners."));
		diagnoses.add(new Diagnosis(45, "You're sick. Of <i>work</i>, that is!"));
		diagnoses.add(new Diagnosis(46, "You are Batman. Put down your phone and go save someone's stupid ass."));
		diagnoses.add(new Diagnosis(47, "You're a rebel badass."));
		diagnoses.add(new Diagnosis(48, "You have more muscle than you can handle."));
		diagnoses.add(new Diagnosis(49, "You're a goddamn hypochondriac."));
		diagnoses.add(new Diagnosis(50, "You hate children."));
		diagnoses.add(new Diagnosis(51, "You're just making it all up as you go."));
		diagnoses.add(new Diagnosis(52, "You're just passing the time, like a prisoner in solitary confinement."));
		diagnoses.add(new Diagnosis(53, "You are Hyborea's last hope, so Crom needs you to usurp another quest."));
		diagnoses.add(new Diagnosis(54, "It was all just a dream, and you'll wake up soon."));
		diagnoses.add(new Diagnosis(55, "Your phone is just imagining you."));
		diagnoses.add(new Diagnosis(56, "Confronted with the realization that your life is historically meaningless, you became another statistic."));
		diagnoses.add(new Diagnosis(57, "You're fucked this time, but you still have 2 lives and 3 more continues."));
		diagnoses.add(new Diagnosis(58, "You are a Dolphin's daydream."));
		diagnoses.add(new Diagnosis(59, "In a universe with infinite possibilities, one of these days it had to be you."));
		diagnoses.add(new Diagnosis(60, "You're the black pawn that starts in column e. Long story short, you're not making it across."));

		/*
		 * QUESTIONS
		 */

		questions.add(new Question(1, "Are you now, or have you ever been, a member of the Communist party?"));
		questions.add(new Question(2, "In general, do you...?"));
		questions.add(new Question(3, "Are you feeling feverish?"));
		questions.add(new Question(4, "Do your turds look like floating Milk Duds?"));
		questions.add(new Question(5, "Does looking into a child's face make you want to smash everything?"));
		questions.add(new Question(6, "Do you catch yourself staring into space at least twice a day?"));
		questions.add(new Question(7, "Given a coloring book, are you able to color within the lines?"));
		questions.add(new Question(8, "Are most of your friends at least ten years younger than you?"));
		questions.add(new Question(9, "Did you just burp?"));
		questions.add(new Question(10, "Can you dodge raindrops?"));
		questions.add(new Question(11, "Given a coloring book, do you throw it away?"));
		questions.add(new Question(12, "Can you smell colors?"));
		questions.add(new Question(13, "Can you create magic?"));
		questions.add(new Question(14, "Do bees make good pets?"));
		questions.add(new Question(15, "Do your farts sound like cooing pigeons?"));
		questions.add(new Question(16, "Does binary code turn you on?"));
		questions.add(new Question(17, "Does swearing make you better, faster, stronger?"));
		questions.add(new Question(18, "Do you spit when you sneeze?"));
		questions.add(new Question(19, "Do loud sounds make you cry?"));
		questions.add(new Question(20, "Do you know <i>kung fu</i>?"));
		questions.add(new Question(21, "Have you heard the one about the priest, the rabbi, and the senator?"));
		questions.add(new Question(22, "Does holy water taste like vinegar to you?"));
		questions.add(new Question(23, "Do your parents know you downloaded this app?"));
		questions.add(new Question(24, "Are you happy with your wireless service carrier?"));
		questions.add(new Question(25, "Have you considered switching to GEICO?"));
		questions.add(new Question(26, "Can you spare any change?"));
		questions.add(new Question(27, "Can I have your number?"));
		questions.add(new Question(28, "Would you mind tapping 'No' for me?"));
		questions.add(new Question(29, "Is professional wrestling real?"));
		questions.add(new Question(30, "Are you wearing a hat?"));
		questions.add(new Question(31, "Do you have any trouble sleeping?"));
		questions.add(new Question(32, "Do you consume more than three drinks an hour?"));
		questions.add(new Question(33, "Does your left eye work better than your right?"));
		questions.add(new Question(34, "Do you mind answering diagnostic questions?"));
		questions.add(new Question(35, "Were you aware that there was a state called Michigan before the Sufjan Stevens album?"));
		questions.add(new Question(36, "Do you frequently get wedgies?"));
		questions.add(new Question(37, "Do you enjoy puns?"));
		questions.add(new Question(38, "Are you wearing sparkly nail polish?"));
		questions.add(new Question(39, "Have you broken a window in the last 10 minutes?"));
		questions.add(new Question(40, "Do you add modeling glue to your coffee?"));
		questions.add(new Question(41, "Does soft rock you rock softly?"));
		questions.add(new Question(42, "Are you currently doing one-handed push-ups?"));
		questions.add(new Question(43, "Do you get butt-hurt over small things?"));
		questions.add(new Question(44, "Do you sweat Gatorade?"));
		questions.add(new Question(45, "Do you follow the law?"));
		questions.add(new Question(46, "Do you feel itchy?"));
		questions.add(new Question(47, "Would you kiss a bee?"));
		questions.add(new Question(48, "Have you ever been drunk off your ass?"));
		questions.add(new Question(49, "Do you have children?"));
		questions.add(new Question(50, "Have you ever punched through the hull of a battleship?"));

		/*
		 * ANSWERS An answer is associated with questions/diagnosis.
		 * 
		 * @example new Answer([diagnosisID],[questionID],[Answer])
		 */

		answers.add(new Answer(1, 1, Answer.YES));
		answers.add(new Answer(2, 2, Answer.YES));
		answers.add(new Answer(4, 5, Answer.YES));

		// an astronaut
		answers.add(new Answer(5, 6, Answer.YES));
		answers.add(new Answer(5, 4, Answer.NO));
		answers.add(new Answer(5, 11, Answer.NO));
		answers.add(new Answer(5, 18, Answer.YES));
		answers.add(new Answer(5, 42, Answer.YES));

		// a pigeon in The Matrix
		answers.add(new Answer(6, 10, Answer.YES));
		answers.add(new Answer(6, 15, Answer.YES));

		// <i>are</i> The Matrix
		answers.add(new Answer(7, 12, Answer.YES));
		answers.add(new Answer(7, 13, Answer.YES));
		answers.add(new Answer(7, 16, Answer.YES));

		// color-inducing halitosis
		answers.add(new Answer(8, 9, Answer.YES));
		answers.add(new Answer(8, 7, Answer.NO));
		answers.add(new Answer(8, 12, Answer.YES));

		// Shaken Baby Syndrome
		answers.add(new Answer(9, 3, Answer.YES));
		answers.add(new Answer(9, 7, Answer.NO));
		answers.add(new Answer(9, 6, Answer.YES));
		answers.add(new Answer(9, 11, Answer.YES));
		answers.add(new Answer(9, 13, Answer.YES));
		answers.add(new Answer(9, 14, Answer.YES));
		answers.add(new Answer(9, 15, Answer.YES));
		answers.add(new Answer(9, 18, Answer.YES));

		// are a beekeeper
		answers.add(new Answer(11, 14, Answer.YES));
		answers.add(new Answer(11, 47, Answer.YES));

		// are batman
		answers.add(new Answer(46, 42, Answer.YES));
		answers.add(new Answer(46, 43, Answer.NO));
		answers.add(new Answer(46, 39, Answer.YES));
		answers.add(new Answer(46, 36, Answer.YES));

		// are a rebel
		answers.add(new Answer(47, 28, Answer.YES));
		answers.add(new Answer(47, 45, Answer.NO));

		// have more muscle than you can handle
		answers.add(new Answer(48, 50, Answer.YES));

		// hate children
		answers.add(new Answer(50, 49, Answer.YES));

	}
}

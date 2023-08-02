package com.mygdxof.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.Button;
import java.util.Random;

public class MyGdxGameOfficial extends ApplicationAdapter {
	//Инициализируем переменные
	SpriteBatch batch;  //Фон, для отображения спрайтов
	FreeTypeFontGenerator fontGenerator;  //Генерация фона текста
	FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;  //Параметры фонового текста
	FreeTypeFontGenerator fontGeneratorGold, fontGeneratorMedal, fontGeneratorStart;
	FreeTypeFontGenerator.FreeTypeFontParameter fontParameterGold, fontParameterGold1, fontParameterMedal, fontParameterStart;
	//Создаем текстуры заднего фона
	Texture background;
	Texture background2;
	Texture background3;
	Texture background4;
	//Создаем переменные с выбором случайного числа
	int choose = 1 + (int) ( Math.random() * 4 );
	int birdChoose = 1 + (int) (Math.random() * 3);
	int musicChoose = 1 + (int) (Math.random() * 3);
	//ShapeRenderer shapeRenderer;
	//Добавляем текстуры для игры
	Texture gameOver;
	Texture BestScore;
	Texture[] birds;
	Texture[] bluebirds;
	Texture[] redbirds;
	Texture tapStart;
	Texture New;
	Texture bronzeMedal;
	Texture silverMedal;
	Texture goldMedal;
	Texture platinumMedal;
	int flapState = 0;  //Переменная для взмаха крыльев
	float birdY = 0;  //Переменная с положением птицы относительно оси Y
	float velocity = 0;  //Скорость, с которой птичка будет падать
	Circle birdCircle;  //Создаем невидимую текстуру птицы, для нахождения коллизий
	int score = 15;  //Счет
	int scoringTube = 0;  //Ведущая труба
	//Создаем переменные для написания текста
	BitmapFont font;
	BitmapFont font1;
	BitmapFont congrats;
	BitmapFont newMedal;
	BitmapFont gameMade;
	BitmapFont continueRestart;
	BitmapFont DoNotStart;
	BitmapFont DoNotStart2;
	BitmapFont DoNotStart3;
	BitmapFont DoNotStart4;
	int gameState = 0;  //Игровой режим
	float gravity = 2;  //Гравитация
	//Добавляем текстуры верхней и нижней труб
	Texture TopTube;
	Texture BottomTube;
	float gap = 400;
	float maxTubeOffset;  //Максимальное отдаление трубы
	Random randomGenerator;  //Генератор случайный чисел
	float tubeVelocity = 4;  //Скорость появления труб
	int numberOfTubes = 4;  //Кол-во труб
	float[] tubeX = new float[numberOfTubes];  //Расположение трубы относительно оси X
	float[] tubeOffset = new float[numberOfTubes];  //Смещие труб
	float distanceBetweenTubes;  //Расстояние между трубами
	//Создаем невидимые текстуры для труб
	Rectangle[] topTubeRectangle;
	Rectangle[] bottomTubeRectangle;
	int maxScore = 0;  //Лучший счет
	int x = 0;
	//Добавляем переменные для музыки и звуков
	private Sound soundSwoosh;
	private Sound soundScore;
	private Sound soundHit;
	private Music musicBase;
	private Music musicBase2;
	private Music musicBase3;
	private Music musicForBronze;
	private Music musicForSilver;
	private Music musicForGold;
	private Music musicForPlatinum;
	//Логические переменные, для проверки условия
	boolean ch1 = true;
	boolean ch2 = true;
	boolean ch3 = true;
	boolean ch4 = true;
	int r = 0;

	@Override
	public void create () {

		//Создаем новое поле для отображения текстур
		batch = new SpriteBatch();
		//Добавляем текстуры заднего фона
		background = new Texture("bg.png");
		background2 = new Texture("city.png");
		background3 = new Texture("city2.png");
		background4 = new Texture("city3.png");
		//Надпись игра окончена
		gameOver = new Texture("gameover2.png");
		//Лучший счет
		BestScore = new Texture("bestscore.png");
		//Текстуры птиц, разных цветов
		birds = new Texture[2];
		bluebirds = new Texture[2];
		redbirds = new Texture[2];
		//shapeRenderer = new ShapeRenderer();
		birdCircle = new Circle();
		//Добавляем текстуры птиц по цветам в их группы
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");
		bluebirds[0] = new Texture("bluebird-upflap.png");
		bluebirds[1] = new Texture("bluebird-downflap.png");
		redbirds[0] = new Texture("redbird-upflap.png");
		redbirds[1] = new Texture("redbird-downflap.png");
		//Надпись перед запуском игры
		tapStart = new Texture("message.png");
		//надпись, которая будет отображаться при получении новых достижений
		New = new Texture("new.png");
		//Добавляем текстуры медалей
		bronzeMedal = new Texture("goldmedal.png");
		silverMedal = new Texture("silvermedal.png");
		goldMedal = new Texture("realgoldmedal.png");
		platinumMedal = new Texture("patinummedal.png");
		//Задаем параметры для отображения текста
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(5);
		font1 = new BitmapFont();
		font1.setColor(Color.CORAL);
		font1.getData().setScale(3);
		congrats = new BitmapFont();
		congrats.setColor(Color.GOLD);
		congrats.getData().setScale(6);
		newMedal = new BitmapFont();
		newMedal.setColor(Color.GOLD);
		newMedal.getData().setScale(4);
		gameMade = new BitmapFont();
		gameMade.setColor(Color.BLACK);
		gameMade.getData().setScale(4);
		continueRestart = new BitmapFont();
		DoNotStart = new BitmapFont();
		DoNotStart2 = new BitmapFont();
		DoNotStart3 = new BitmapFont();
		DoNotStart4 = new BitmapFont();
		//Добавляем текстуры труб
		TopTube = new Texture("toptube.png");
		BottomTube = new Texture("bottomtube.png");
		//Расчитываем смещение труб
		maxTubeOffset = Gdx.graphics.getHeight()/2 - gap/2 - 100;
		randomGenerator = new Random();
		//Дистанция между трубами
		distanceBetweenTubes = Gdx.graphics.getWidth() * 3/4;
		topTubeRectangle = new Rectangle[numberOfTubes];
		bottomTubeRectangle = new Rectangle[numberOfTubes];
		//Добавляем музыку и звуки
		soundSwoosh = Gdx.audio.newSound(Gdx.files.internal("sfx_swooshing.wav"));
		soundScore = Gdx.audio.newSound(Gdx.files.internal("twitter.mp3"));
		soundHit = Gdx.audio.newSound(Gdx.files.internal("sfx_hit.wav"));
		musicBase = Gdx.audio.newMusic(Gdx.files.internal("Fly Me To The Moon.mp3"));
		musicBase2 = Gdx.audio.newMusic(Gdx.files.internal("Build Me Up.mp3"));
		musicBase3 = Gdx.audio.newMusic(Gdx.files.internal("I Love You.mp3"));
		musicForBronze = Gdx.audio.newMusic(Gdx.files.internal("INDUSTRY BABY.mp3"));
		musicForSilver = Gdx.audio.newMusic(Gdx.files.internal("BEEN BALLIN.mp3"));
		musicForGold = Gdx.audio.newMusic(Gdx.files.internal("Travis Scott - SICKO MODE ft Drake.mp3"));
		musicForPlatinum = Gdx.audio.newMusic(Gdx.files.internal("dolla sign slime.mp3"));


		//Указываем тип текста и задаем его параметры
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("04B_19.TTF"));

        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 75;
        fontParameter.borderWidth = 5;
        fontParameter.color = Color.WHITE;
        fontParameter.borderColor = Color.BLACK;
        font = fontGenerator.generateFont(fontParameter);
		gameMade = fontGenerator.generateFont(fontParameter);

		fontGeneratorGold = new FreeTypeFontGenerator(Gdx.files.internal("04B_19.TTF"));
		fontParameterGold = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameterGold.size = 90;
		fontParameterGold.borderWidth = 5;
		fontParameterGold.color = Color.GOLD;
		fontParameterGold.borderColor = Color.WHITE;
		congrats = fontGeneratorGold.generateFont(fontParameterGold);

		fontParameterGold1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameterGold1.size = 50;
		fontParameterGold1.borderWidth = 3;
		fontParameterGold1.color = Color.GOLD;
		fontParameterGold1.borderColor = Color.WHITE;
		newMedal = fontGeneratorGold.generateFont(fontParameterGold1);

		fontGeneratorMedal = new FreeTypeFontGenerator(Gdx.files.internal("04B_19.TTF"));
		fontParameterMedal = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameterMedal.size = 55;
		fontParameterMedal.borderWidth = 3;
		fontParameterMedal.color = Color.CORAL;
		fontParameterMedal.borderColor = Color.ORANGE;
		font1 = fontGeneratorMedal.generateFont(fontParameterMedal);

		fontGeneratorStart = new FreeTypeFontGenerator(Gdx.files.internal("04B_19.TTF"));
		fontParameterStart = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameterStart.size = 70;
		fontParameterStart.borderWidth = 3;
		fontParameterStart.color = Color.RED;
		fontParameterStart.borderColor = Color.WHITE;
		continueRestart = fontGeneratorStart.generateFont(fontParameterStart);
		DoNotStart = fontGeneratorStart.generateFont(fontParameterStart);
		DoNotStart2 = fontGeneratorStart.generateFont(fontParameterStart);
		DoNotStart3 = fontGeneratorStart.generateFont(fontParameterStart);
		DoNotStart4 = fontGeneratorStart.generateFont(fontParameterStart);

		startGame();
	}


	public void startGame()
	{

		//Расчитываем положение птички относительно оси Y
		birdY = Gdx.graphics.getHeight() / 2 - birds[flapState].getHeight() / 2;
		//Создаем метод для генерации случайного числа труб
		for(int i = 0; i < numberOfTubes; i++)
		{
			tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
			tubeX[i] = Gdx.graphics.getWidth()/2 - TopTube.getWidth()/2 + Gdx.graphics.getWidth() + i * distanceBetweenTubes;
			topTubeRectangle[i] = new Rectangle();
			bottomTubeRectangle[i] = new Rectangle();

		}
	}

	@Override
	public void render () {
		batch.begin();
		//Создаем метод для случайного выбора заднего фона, при каждом новом запуске игры
		if(choose == 1) {
			batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		} if(choose == 2) {
			batch.draw(background2, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}if(choose == 3){
			batch.draw(background3, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}if(choose == 4){
			batch.draw(background4, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
		else
		{
			Gdx.app.log("Error", String.valueOf("Error"));
		}
		if (gameState == 1) {
			//Выводим на экран надписть счета
			font.draw(batch, String.valueOf("Score: " + score), 380, 1925);

			//Создаем метод для подсчета счета игрока
			if(tubeX[scoringTube] < Gdx.graphics.getWidth()/2)
			{
				Gdx.app.log("Score", String.valueOf(score));

				score++;
				//Воспроизводим звук удара
				long id = soundScore.play(1.0f);
				soundScore.setPitch(id,1);
				soundScore.setLooping(id,false);

				if(scoringTube < numberOfTubes - 1)
				{
					scoringTube++;
				}
				else
				{
					scoringTube = 0;
				}
			}

			//Созадем метод для осуществления взамаха крыльев птицы
			if (Gdx.input.justTouched())
			{
				velocity = -30;
				//Воспроизводим звук взмаха крыльев
				long id = soundSwoosh.play(1.0f);
				soundSwoosh.setPitch(id,2);
				soundSwoosh.setLooping(id, false);

			}

			//Создаем метод для генерации труб
			for(int i = 0; i < numberOfTubes; i++)
			{
				if(tubeX[i] < -TopTube.getWidth())
				{
					tubeX[i] += numberOfTubes * distanceBetweenTubes;
					tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
				}
				else
				{
					tubeX[i] = tubeX[i] - tubeVelocity;

				}
				batch.draw(TopTube, tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i] / 2);
				batch.draw(BottomTube, tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - BottomTube.getHeight() + tubeOffset[i] / 2);

				topTubeRectangle[i] = new Rectangle(tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i] / 2, TopTube.getWidth(), TopTube.getHeight());
				bottomTubeRectangle[i] = new Rectangle(tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - BottomTube.getHeight() + tubeOffset[i] / 2, BottomTube.getWidth(), BottomTube.getHeight());


			}

			//Созадем метод, чтобы птица взлетала и падала
			if(birdY > 0)
			{
				velocity = velocity + gravity;
				birdY -= velocity;
			}else
			{
				gameState = 2; //2 Игра прекращена
				long id = soundHit.play(1.0f);
				soundHit.setPitch(id,1);
				soundHit.setLooping(id,false);
			}

		}else if(gameState == 0)
		{
			//Рисуем текстуру при запуске игры
			batch.draw(tapStart, 180, 400,Gdx.graphics.getWidth()/2 + tapStart.getWidth()/1, Gdx.graphics.getHeight()/2 + tapStart.getHeight()*2);
			gameMade.draw(batch, String.valueOf("Game Made by A.B"), 180, 100);  //Вывод надписи о разработчике
			//Создаем методы для воспроизведения случайной музыки, при каждом новом запуске игры
			if(musicChoose == 1) {
				musicBase.setVolume(1.0f);
				musicBase.setLooping(true);
				musicBase.play();
			}
			if(musicChoose == 2){
				musicBase2.setVolume(1.0f);
				musicBase2.setLooping(true);
				musicBase2.play();
			}
			if(musicChoose == 3){
				musicBase3.setVolume(1.0f);
				musicBase3.setLooping(true);
				musicBase3.play();
			}
			//Если игрок каснется экрана, музыка прекращает играть
			if (Gdx.input.justTouched())
			{
				gameState = 1;
				musicBase.stop();
				musicBase2.stop();
				musicBase3.stop();
			}
		}else if(gameState == 2)
		{
			//Если игрок погиб, выводим таблицу с со счетом и надписью "Игра окончена"
			batch.draw(gameOver, 180, 1500, Gdx.graphics.getWidth()/2 + gameOver.getWidth(), Gdx.graphics.getHeight()/7 - gameOver.getHeight());
			batch.draw(BestScore, 150, 700, Gdx.graphics.getWidth()/2 + BestScore.getWidth(), Gdx.graphics.getHeight()/6 + BestScore.getHeight());
			continueRestart.draw(batch, String.valueOf("Double tap to restart"), 140, 280);  //Для начала новой игры необходимо дважды нажать на экран
			//Создаем метод для определения лучшего счета
			if (maxScore < score)
			{
				maxScore = score;
				int F = maxScore;
				Gdx.app.log("New high score", String.valueOf(F));
				font.draw(batch, String.valueOf(maxScore), 850, 870);
				font.draw(batch, String.valueOf(score), 850, 1030);

			} else {
				font.draw(batch, String.valueOf(maxScore), 850, 870);
				font.draw(batch, String.valueOf(score), 850, 1030);
				if((score & maxScore)!= 0 & score >= maxScore)
				{
					batch.draw(New, 680, 900, Gdx.graphics.getWidth()/10 - New.getWidth(), Gdx.graphics.getHeight()/22 - New.getHeight()*4);

				}
				//Создаем методы для достижений, при наборе игроком определенного кол-ва очков, он открывает новую медаль и играет музыка
				if(score >= 5 | maxScore >= 5)
				{
					batch.draw(bronzeMedal, 225, 930, Gdx.graphics.getWidth()/5 - bronzeMedal.getWidth(), Gdx.graphics.getHeight()/9 - bronzeMedal.getHeight()*4);
					font1.draw(batch, String.valueOf("MEDALS"), 335, 1100);
					if(ch1 == true)
					{
						batch.draw(New, 240, 1065, Gdx.graphics.getWidth()/10 - New.getWidth(), Gdx.graphics.getHeight()/22 - New.getHeight()*4);
						congrats.draw(batch, String.valueOf("CONGRATS!"), 335, 650);
						newMedal.draw(batch, String.valueOf("You Have Unlock New Medal!"), 190, 520);
						musicForBronze.setVolume(1.0f);
						musicForBronze.setLooping(false);
						musicForBronze.play();
						DoNotStart.draw(batch, String.valueOf("Don't touch the screen\n" + "    to listen music :)"), 110, 1400);
					}
				}
				if(score >= 10 | maxScore >= 10)
				{
					batch.draw(silverMedal, 390, 930, Gdx.graphics.getWidth()/5 - silverMedal.getWidth(), Gdx.graphics.getHeight()/9 - silverMedal.getHeight()*4);
					if(ch2 == true)
					{
						batch.draw(New, 240, 1065, Gdx.graphics.getWidth()/10 - New.getWidth(), Gdx.graphics.getHeight()/22 - New.getHeight()*4);
						congrats.draw(batch, String.valueOf("CONGRATS!"), 335, 650);
						newMedal.draw(batch, String.valueOf("You Have Unlock New Medal!"), 190, 520);
						musicForSilver.setVolume(1.0f);
						musicForSilver.setLooping(false);
						musicForSilver.play();
						DoNotStart.draw(batch, String.valueOf("Don't touch the screen\n" + "    to listen music :)"), 110, 1400);
					}
				}
				if(score >= 15 | maxScore >= 15)
				{
					batch.draw(goldMedal, 225, 820, Gdx.graphics.getWidth()/8 + goldMedal.getWidth()*2, Gdx.graphics.getHeight()/9 - goldMedal.getHeight()*4);
					if(ch3 == true)
					{
						batch.draw(New, 240, 1065, Gdx.graphics.getWidth()/10 - New.getWidth(), Gdx.graphics.getHeight()/22 - New.getHeight()*4);
						congrats.draw(batch, String.valueOf("CONGRATS!"), 335, 650);
						newMedal.draw(batch, String.valueOf("You Have Unlock New Medal!"), 190, 520);
						musicForGold.setVolume(1.0f);
						musicForGold.setLooping(false);
						musicForGold.play();
						DoNotStart.draw(batch, String.valueOf("Don't touch the screen\n" + "    to listen music :)"), 110, 1400);
					}
				}
				if(score >= 20 | maxScore >= 20)
				{
					batch.draw(platinumMedal, 390, 820, Gdx.graphics.getWidth()/5 - platinumMedal.getWidth(), Gdx.graphics.getHeight()/10 - platinumMedal.getHeight()*4);
					if(ch4 == true)
					{
						batch.draw(New, 240, 1065, Gdx.graphics.getWidth()/10 - New.getWidth(), Gdx.graphics.getHeight()/22 - New.getHeight()*4);
						congrats.draw(batch, String.valueOf("CONGRATS!"), 335, 650);
						newMedal.draw(batch, String.valueOf("You Have Unlock New Medal!"), 190, 520);
						musicForPlatinum.setVolume(1.0f);
						musicForPlatinum.setLooping(false);
						musicForPlatinum.play();
						DoNotStart.draw(batch, String.valueOf("Don't touch the screen\n" + "    to listen music :)"), 110, 1400);
					}
				}

			}

			//Создаем метод для двойного нажатия на экран
			if(Gdx.input.justTouched())
			{r++;}
			//При двойном нажатии на экран, перезапускаем игру
			if(r == 2)
			{
				r = 0;
				gameState = 1;
				startGame();
				score = 0;
				scoringTube = 0;
				velocity = 0;
				if(maxScore < 5)
				{
					ch1 = true;
				}else
				{
					ch1 = false;
				}
				if(maxScore < 10)
				{
					ch2 = true;
				}else
				{
					ch2 = false;
				}
				if(maxScore < 15)
				{
					ch3 = true;
				}else
				{
					ch3 = false;
				}
				if(maxScore < 20)
				{
					ch4 = true;
				}else
				{
					ch4 = false;
				}
				x = 0;
				musicForBronze.stop();
				musicForSilver.stop();
				musicForGold.stop();
				musicForPlatinum.stop();

			}

		}
		if (flapState == 0)
		{
			flapState = 1;
		}
		else
		{
			flapState = 0;
		}

		//Добавляем метод для рисовки птицы разного цвета, при каждом новом запуске игры
		if(gameState == 0 || gameState == 1) {
			if(birdChoose == 1) {
				batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - birds[flapState].getWidth() / 2, birdY);
			}
			if(birdChoose == 2) {
				batch.draw(bluebirds[flapState], Gdx.graphics.getWidth() / 2 - bluebirds[flapState].getWidth() / 2, birdY);
			}
			if(birdChoose == 3){
				batch.draw(redbirds[flapState], Gdx.graphics.getWidth() / 2 - redbirds[flapState].getWidth() / 2, birdY);
			}
			else
			{
				Gdx.app.log("Error", String.valueOf("Error"));
			}
		}
		batch.end();

		birdCircle.set(Gdx.graphics.getWidth()/2, birdY + birds[flapState].getHeight()/2, birds[flapState].getWidth()/2);

		//shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		//shapeRenderer.setColor(Color.RED);
		//shapeRenderer.circle(birdCircle.x, birdCircle.y, birdCircle.radius);

		//Создаем метод для отслеживания соприкосновения текстур
		for(int i = 0; i < numberOfTubes; i++)
		{
			//shapeRenderer.rect(tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i] / 2, TopTube.getWidth(), TopTube.getHeight());
			//shapeRenderer.rect(tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - BottomTube.getHeight() + tubeOffset[i] / 2, BottomTube.getWidth(), BottomTube.getHeight());

			if(Intersector.overlaps(birdCircle, topTubeRectangle[i]) || Intersector.overlaps(birdCircle, bottomTubeRectangle[i]))
			{
				gameState = 2;
				if(x==0)
				{
					long id = soundHit.play(1.0f);
					soundHit.setPitch(id,1);
					soundHit.setLooping(id,false);
					x++;
				}
			}
		}
		//shapeRenderer.end();
	}
}

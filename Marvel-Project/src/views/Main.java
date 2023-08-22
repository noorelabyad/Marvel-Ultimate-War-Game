package views;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import engine.*;
import model.world.*;
import model.abilities.*;
import model.effects.*;
import exceptions.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;
import javafx.scene.text.Font;

public class Main extends Application
{
	
	private static String n1;
	private static String n2;
	private static boolean f1;
	private static boolean f2;
	

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage)  throws Exception{
		
		
		primaryStage.setTitle("Marvel Battlefield");
		Image icon = new Image ("file:icon.png");
		primaryStage.getIcons().add(icon);
		
		BorderPane layout = new BorderPane();
		Image img = new Image("File:scene03.jpg");
		BackgroundImage bImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
	    BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    Background bGround = new Background(bImg);
	    layout.setBackground(bGround);
	    Button start = new Button("PLAY");
	    start.setPrefSize(200, 50);
	    start.setFont(new Font("Broadway", 20));
	    layout.setCenter(start);
	   
	    Image image = new Image("file:logo.jpg"); 
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setX(50);
	    imageView.setY(50);
	    imageView.setFitWidth(300);
	    imageView.setFitHeight(500);
	    
	  
	   // String uriString = new File("file://C:\\Users\\Admin\\Desktop\\song.mp3").toURI().toString();
	    	  //  MediaPlayer player = new MediaPlayer( new Media(uriString));
	    	    //player.play();
	 
       Media sound = new Media(Paths.get("sound.mp3").toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();    
	    
	    Scene scene0 = new Scene (layout,1550,800);
	    primaryStage.setFullScreen(true);
	    primaryStage.setScene(scene0);
	    start.setOnAction(e-> 
	    {
	    	
		primaryStage.setScene(prepareScene1(primaryStage));
		});
	    
		primaryStage.show();
		
	
		
			
	}
	
	private Scene prepareScene1( Stage primaryStage)
	{
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20,20,20,20));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label nameLabel = new Label ("Player 1 Name");
		nameLabel.setFont(new Font ("Broadway", 18));
		nameLabel.setTextFill(Color.color(1, 1, 1));
		GridPane.setConstraints(nameLabel, 45, 35);
		
		TextField nameInput = new TextField ();
		GridPane.setConstraints(nameInput, 45, 38);
		nameInput.setPrefSize(200,50);
		
		Button name = new Button ("Enter");
		name.setFont(new Font ("Broadway", 14));
		GridPane.setConstraints(name, 45, 41);
		name.setPrefSize(200,40);
		
		Label nameLabel2 = new Label ("Player 2 Name");
		nameLabel2.setFont(new Font ("Broadway", 18));
		nameLabel2.setTextFill(Color.color(1, 1, 1));
		GridPane.setConstraints(nameLabel2, 60, 35);
		
		TextField nameInput2 = new TextField ();
		GridPane.setConstraints(nameInput2, 60, 38);
		nameInput2.setPrefSize(200,50);
		
		Button name2 = new Button ("Enter");
		name2.setFont(new Font ("Broadway", 14));
		GridPane.setConstraints(name2, 60, 41);
		name2.setPrefSize(200,40);
		
		grid.getChildren().addAll(nameLabel, nameInput, name,nameLabel2, nameInput2, name2);
		grid.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, CornerRadii.EMPTY, Insets.EMPTY)));
	
		Scene scene = new Scene (grid,1550,800);
		name.setOnAction(e -> 
		{
			
			String x = nameInput.getText();
			//System.out.println(x);
			nameInput.clear();
			if(!x.equals(""))
			{
				f1=true;
				n1=x;
			}
			if(f1&&f2)
				try {
					
					primaryStage.setScene(prepareScene2(primaryStage));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
		});
		
		name2.setOnAction(e->{
			
			String x = nameInput2.getText();
			//System.out.println(x);
			nameInput2.clear();
			if(!x.equals(""))
			{
				f2=true;
				n2=x;
			}
			if(f1&&f2)
				try {
					
					primaryStage.setScene(prepareScene2(primaryStage));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			
		});
		
		Image img = new Image("File:scene2.jpg");
		BackgroundImage bImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
	    BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    Background bGround = new Background(bImg);
	    grid.setBackground(bGround);
		return scene;
		
	}
	
	private Scene prepareScene2(Stage primaryStage) throws IOException
	{
		Player player1= new Player(n1);
		Player player2= new Player(n2);
		//System.out.println(n1);
		//System.out.println(n2);
		ArrayList<Champion> availableChampions = new ArrayList<Champion>();
		ArrayList<String> availableImages = new ArrayList<String>();
		ArrayList<String> availableIcons = new ArrayList<String>();
		
		ArrayList<String> player1TeamImages= new ArrayList <String>();
		ArrayList<String> player1TeamIcons= new ArrayList <String>();
		
		Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");
		
		ArrayList<String >images = new ArrayList <String> ();
		ArrayList<String >icons = new ArrayList <String> ();
		prepareImages(images);
		prepareIcons(icons);
		
		
		
		VBox top = new VBox (10);
		Label playerLabel = new Label ("Player 1: " + n1);
		playerLabel.setFont(new Font("Broadway", 24));
		playerLabel.setTextFill(Color.color(1, 1, 1));
		
		Label chooseChampionLabel = new Label ("Choose 3 Champions");
		chooseChampionLabel.setFont(new Font("Broadway", 24));
		chooseChampionLabel.setTextFill(Color.color(1, 1, 1));
		
		top.getChildren().addAll(playerLabel, chooseChampionLabel);
		
		
		HBox infoBox= new HBox(50);
		VBox namesVbox = new VBox (5);
		VBox chooseVbox = new VBox (5);
		
		 first (images, infoBox);
		
	
		for (int i=0; i<Game.getAvailableChampions().size();i++)
		{
			Champion ch =Game.getAvailableChampions().get(i);
			availableChampions.add(ch);
			VBox mainInfo = new VBox(3);
			VBox abilitiesInfo = new VBox(3);
			
			Label l  = new Label ("Information about Champion\n");
			Label l1 = new Label ("\nName: " + ch.getName());
			Label l2 = new Label ("MaxHP: " + ch.getMaxHP());
			Label l3 = new Label ("Mana: " + ch.getMana());
			Label l4 = new Label ("Max Action Points per turn: " + ch.getMaxActionPointsPerTurn());
			Label l5 = new Label ("Speed: " + ch.getSpeed());
			Label l6 = new Label ("Attack Range: " + ch.getAttackRange());
			Label l7 = new Label ("Attack Damage: " + ch.getAttackDamage());
			Label lx = new Label ();
			if(ch instanceof Hero)
			{
				lx.setText("Type: Hero");
			}
			else
			{
				if(ch instanceof AntiHero)
					lx.setText("Type: AntiHero");
				else
					lx.setText("Type: Villain");
			}
			
			String k ="";
			for (int j=0; j<ch.getAbilities().size();j++)
			{
				k+= "\n\nAbility " + (j+1) +":\n" + ch.getAbilities().get(j).toString();
				if(ch.getAbilities().get(j) instanceof DamagingAbility)
				{
					k+= "\nType: Damaging Ability\nDamage Amount: " +
							((DamagingAbility) ch.getAbilities().get(j)).getDamageAmount();
				}
				if(ch.getAbilities().get(j) instanceof HealingAbility)
				{
					k+= "\nType: Healing Ability\nHeal Amount: " +
							((HealingAbility) ch.getAbilities().get(j)).getHealAmount();
				}
				if(ch.getAbilities().get(j) instanceof CrowdControlAbility)
				{
					k+= "\nType: Crowd Control Ability\nEffect Info: \n" +
							((CrowdControlAbility) ch.getAbilities().get(j)).getEffect().toString();
				}
				
			}
			Label l8 = new Label ("Abilties: " + k);
			
			formatLabel(l);
			formatLabel(l1);
			formatLabel(l2);
			formatLabel(l3);
			formatLabel(l4);
			formatLabel(l5);
			formatLabel(l6);
			formatLabel(l7);
			formatLabel(l8);
			formatLabel(lx);
			
			mainInfo.getChildren().addAll(l,l1,l2,l3,l4,l5,l6,l7,lx);
			abilitiesInfo.getChildren().addAll(l8);
			
			
			Image image = new Image(images.get(i)); 
			availableImages.add(images.get(i));
			availableIcons.add(icons.get(i));
			String imageFilePath = availableImages.get(i);
			String iconFilePath = icons.get(i);
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setX(50);
		    imageView.setY(50);
		    imageView.setFitWidth(250);
		    imageView.setFitHeight(500);
		    //imageView.setPreserveRatio(true);
		    
		    Button showInfoButton = new Button(ch.getName());
		    showInfoButton.setOnAction(e ->
			{ 
				infoBox.getChildren().clear();
				infoBox.getChildren().addAll(mainInfo, abilitiesInfo,imageView);

			});
			
		    showInfoButton.setPrefSize(150, 50);
		    
			Button chooses = new Button ("Choose");
			chooses.setPrefSize(75, 50);
			chooses.setOnAction(e->
			{ 
				player1.getTeam().add(ch);
				player1TeamImages.add(imageFilePath);
				player1TeamIcons.add(iconFilePath);
				availableChampions.remove(ch);
				availableImages.remove(imageFilePath);
				availableIcons.remove(iconFilePath);
				chooses.setDisable(true);
				if(player1.getTeam().size()==3)
					primaryStage.setScene(prepareScene3(primaryStage,availableChampions, availableImages,availableIcons, 
							player1, player2, player1TeamImages,player1TeamIcons));
				
			});
			
			
			
			namesVbox.getChildren().addAll(showInfoButton);
			chooseVbox.getChildren().addAll(chooses);
			
		}
		
		
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20,20,20,20));
		grid.setVgap(8);
		grid.setHgap(10);
		GridPane.setConstraints(top, 5 ,12 );
		GridPane.setConstraints(namesVbox, 15, 15);
		GridPane.setConstraints(infoBox, 20, 15);
		GridPane.setConstraints(chooseVbox, 5, 15);
		grid.getChildren().addAll(top,namesVbox,chooseVbox,infoBox);
		
		Image img = new Image("File:galaxy2.jpg");
		BackgroundImage bImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
	    BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    Background bGround = new Background(bImg);
	    grid.setBackground(bGround);
		Scene scene= new Scene(grid,1550,800);
		
		return scene;
	}
	
	private void first (ArrayList <String> images, HBox infoBox)
	{
		Champion ch =Game.getAvailableChampions().get(0);
		VBox mainInfo = new VBox(3);
		VBox abilitiesInfo = new VBox(3);
		
		Label l  = new Label ("Information about Champion\n");
		Label l1 = new Label ("\nName: " + ch.getName());
		Label l2 = new Label ("MaxHP: " + ch.getMaxHP());
		Label l3 = new Label ("Mana: " + ch.getMana());
		Label l4 = new Label ("Max Action Points per turn: " + ch.getMaxActionPointsPerTurn());
		Label l5 = new Label ("Speed: " + ch.getSpeed());
		Label l6 = new Label ("Attack Range: " + ch.getAttackRange());
		Label l7 = new Label ("Attack Damage: " + ch.getAttackDamage());
		Label lx = new Label ();
		if(ch instanceof Hero)
		{
			lx.setText("Type: Hero");
		}
		else
		{
			if(ch instanceof AntiHero)
				lx.setText("Type: AntiHero");
			else
				lx.setText("Type: Villain");
		}
		
		String k ="";
		for (int j=0; j<ch.getAbilities().size();j++)
		{
			k+= "\n\nAbility " + (j+1) +":\n" + ch.getAbilities().get(j).toString();
			if(ch.getAbilities().get(j) instanceof DamagingAbility)
			{
				k+= "\nType: Damaging Ability\nDamage Amount: " +
						((DamagingAbility) ch.getAbilities().get(j)).getDamageAmount();
			}
			if(ch.getAbilities().get(j) instanceof HealingAbility)
			{
				k+= "\nType: Healing Ability\nHeal Amount: " +
						((HealingAbility) ch.getAbilities().get(j)).getHealAmount();
			}
			if(ch.getAbilities().get(j) instanceof CrowdControlAbility)
			{
				k+= "\nType: Crowd Control Ability\nEffect Info: \n" +
						((CrowdControlAbility) ch.getAbilities().get(j)).getEffect().toString();
			}
			
		}
		Label l8 = new Label ("Abilties: " + k);
		
		formatLabel(l);
		formatLabel(l1);
		formatLabel(l2);
		formatLabel(l3);
		formatLabel(l4);
		formatLabel(l5);
		formatLabel(l6);
		formatLabel(l7);
		formatLabel(l8);
		formatLabel(lx);
		
		mainInfo.getChildren().addAll(l,l1,l2,l3,l4,l5,l6,l7,lx);
		abilitiesInfo.getChildren().addAll(l8);
		
		
		Image image = new Image(images.get(0)); 
		
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setX(50);
	    imageView.setY(50);
	    imageView.setFitWidth(250);
	    imageView.setFitHeight(500);
	    
	    infoBox.getChildren().addAll(mainInfo, abilitiesInfo,imageView);
		
	}
	
 	private Scene prepareScene3(Stage primaryStage, ArrayList<Champion> availableChampions,
			ArrayList<String> availableImages, ArrayList<String> availableIcons, Player player1, Player player2, 
			ArrayList<String> player1TeamImages, ArrayList<String> player1TeamIcons) 
	{
		VBox top = new VBox (10);
		Label playerLabel = new Label ("Player 1: " + n1);
		playerLabel.setFont(new Font("Broadway", 24));
		playerLabel.setTextFill(Color.color(1, 1, 1));
		
		Label chooseLeaderLabel = new Label ("Choose your Leader");
		chooseLeaderLabel.setFont(new Font("Broadway", 24));
		chooseLeaderLabel.setTextFill(Color.color(1, 1, 1));
		
		top.getChildren().addAll(playerLabel, chooseLeaderLabel);
		
		HBox allLeaderOptions = new HBox (20);
		
		for (int i=0; i<3; i++)
		{
			Champion ch = player1.getTeam().get(i);
			
			VBox leaderOption = new VBox (30);
			
			Label leaderName = new Label (player1.getTeam().get(i).getName());
			leaderName.setFont(new Font("Algerian", 22));
			leaderName.setTextFill(Color.color(1, 1, 1));
			
			Image image = new Image(player1TeamImages.get(i)); 
			
			//availableImages.add(player1TeamImages.get(i));
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setX(50);
		    imageView.setY(50);
		    imageView.setFitWidth(300);
		    imageView.setFitHeight(500);
		    //imageView.setPreserveRatio(true);
		    
		    Button chooseLeader = new Button ("Choose Leader");
		    chooseLeader.setFont(new Font("Algerian", 18));
		    chooseLeader.setPrefSize(300, 50);
		    chooseLeader.setOnAction( e->	
		    {		
		    	player1.setLeader(ch);	
		    	primaryStage.setScene(prepareScene4(primaryStage,availableChampions, availableImages, availableIcons, player1, player2, player1TeamIcons));
		    }
		    );
		    
		    leaderOption.getChildren().addAll(leaderName, imageView, chooseLeader);
		    
		    allLeaderOptions.getChildren().addAll(leaderOption);
		   
		}

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20,20,20,20));
		grid.setVgap(8);
		grid.setHgap(10);
		GridPane.setConstraints(allLeaderOptions, 8, 12);
		GridPane.setConstraints(top, 3, 12);
		grid.getChildren().addAll(top,allLeaderOptions);

		
		Image img = new Image("File:galaxy.jpg");
		BackgroundImage bImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
	    BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    Background bGround = new Background(bImg);
	    grid.setBackground(bGround);
		Scene scene= new Scene(grid,1550,800);
		
		
		return scene;
	}

	private Scene prepareScene4(Stage primaryStage, ArrayList<Champion> availableChampions,
			ArrayList<String> availableImages, ArrayList<String> availableIcons, Player player1, Player player2, ArrayList<String> player1TeamIcons) 
	{
		
		ArrayList<String> player2TeamImages= new ArrayList <String>();
		ArrayList<String> player2TeamIcons= new ArrayList <String>();
		
		
		
		
		
		VBox top = new VBox (10);
		Label playerLabel = new Label ("Player 2: " + n2);
		playerLabel.setFont(new Font("Broadway", 24));
		playerLabel.setTextFill(Color.color(1, 1, 1));
		
		Label chooseChampionLabel = new Label ("Choose 3 Champions");
		chooseChampionLabel.setFont(new Font("Broadway", 24));
		chooseChampionLabel.setTextFill(Color.color(1, 1, 1));
		
		top.getChildren().addAll(playerLabel, chooseChampionLabel);
		
		
		HBox infoBox= new HBox(50);
		VBox namesVbox = new VBox (5);
		VBox chooseVbox = new VBox (5);
	
		first2 (availableImages, infoBox, availableChampions);
		
		for (int i=0; i<availableChampions.size();i++)
		{
			Champion ch = availableChampions.get(i);
			VBox mainInfo = new VBox(3);
			VBox abilitiesInfo = new VBox(3);
			
			Label l  = new Label ("Information about Champion\n");
			Label l1 = new Label ("\nName: " + ch.getName());
			Label l2 = new Label ("MaxHP: " + ch.getMaxHP());
			Label l3 = new Label ("Mana: " + ch.getMana());
			Label l4 = new Label ("Max Action Points per turn: " + ch.getMaxActionPointsPerTurn());
			Label l5 = new Label ("Speed: " + ch.getSpeed());
			Label l6 = new Label ("Attack Range: " + ch.getAttackRange());
			Label l7 = new Label ("Attack Damage: " + ch.getAttackDamage());
			Label lx = new Label ();
			if(ch instanceof Hero)
			{
				lx.setText("Type: Hero");
			}
			else
			{
				if(ch instanceof AntiHero)
					lx.setText("Type: AntiHero");
				else
					lx.setText("Type: Villain");
			}
			
			String k ="";
			for (int j=0; j<ch.getAbilities().size();j++)
			{
				k+= "\n\nAbility " + (j+1) +":\n" + ch.getAbilities().get(j).toString();
				if(ch.getAbilities().get(j) instanceof DamagingAbility)
				{
					k+= "\nType: Damaging Ability\nDamage Amount: " +
							((DamagingAbility) ch.getAbilities().get(j)).getDamageAmount();
				}
				if(ch.getAbilities().get(j) instanceof HealingAbility)
				{
					k+= "\nType: Healing Ability\nHeal Amount: " +
							((HealingAbility) ch.getAbilities().get(j)).getHealAmount();
				}
				if(ch.getAbilities().get(j) instanceof CrowdControlAbility)
				{
					k+= "\nType: Crowd Control Ability\nEffect Info: \n" +
							((CrowdControlAbility) ch.getAbilities().get(j)).getEffect().toString();
				}
				
			}
			Label l8 = new Label ("Abilties: " + k);
			
			formatLabel(l);
			formatLabel(l1);
			formatLabel(l2);
			formatLabel(l3);
			formatLabel(l4);
			formatLabel(l5);
			formatLabel(l6);
			formatLabel(l7);
			formatLabel(l8);
			formatLabel(lx);
			
			
			
			mainInfo.getChildren().addAll(l,l1,l2,l3,l4,l5,l6,l7,lx);
			abilitiesInfo.getChildren().addAll(l8);
			
			
			Image image = new Image(availableImages.get(i)); 
			String iconFilePath = availableIcons.get(i);
			String imageFilePath = availableImages.get(i);
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setX(50);
		    imageView.setY(50);
		    imageView.setFitWidth(250);
		    imageView.setFitHeight(500);
		    //imageView.setPreserveRatio(true);
		    
		    Button showInfoButton = new Button(ch.getName());
		    showInfoButton.setOnAction(e ->
			{ 
				infoBox.getChildren().clear();
				infoBox.getChildren().addAll(mainInfo, abilitiesInfo,imageView);

			});
			
		    showInfoButton.setPrefSize(150, 50);
		    
			Button chooses = new Button ("Choose");
			chooses.setPrefSize(75, 50);
			chooses.setOnAction(e->
			{ 
				player2.getTeam().add(ch);
				player2TeamImages.add(imageFilePath);
				player2TeamIcons.add(iconFilePath);
				chooses.setDisable(true);
				if(player2.getTeam().size()==3)
					primaryStage.setScene(prepareScene5(primaryStage,availableChampions, availableImages, player1, player2, player1TeamIcons,
							player2TeamImages, player2TeamIcons));
				
			});
			
			
			
			namesVbox.getChildren().addAll(showInfoButton);
			chooseVbox.getChildren().addAll(chooses);
			
		}
		
		
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20,20,20,20));
		grid.setVgap(8);
		grid.setHgap(10);
		GridPane.setConstraints(top, 5, 12);
		GridPane.setConstraints(namesVbox, 15, 15);
		GridPane.setConstraints(infoBox, 20, 15);
		GridPane.setConstraints(chooseVbox, 5, 15);
		grid.getChildren().addAll(top,namesVbox,chooseVbox,infoBox);
		
		Image img = new Image("File:galaxy2.jpg");
		BackgroundImage bImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
	    BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    Background bGround = new Background(bImg);
	    grid.setBackground(bGround);
		Scene scene= new Scene(grid,1550,800);
		
		return scene;
	}

	private void first2(ArrayList<String> availableImages, HBox infoBox, ArrayList <Champion> availableChampions) {
	
		Champion ch =availableChampions.get(0);
		VBox mainInfo = new VBox(3);
		VBox abilitiesInfo = new VBox(3);
		
		Label l  = new Label ("Information about Champion\n");
		Label l1 = new Label ("\nName: " + ch.getName());
		Label l2 = new Label ("MaxHP: " + ch.getMaxHP());
		Label l3 = new Label ("Mana: " + ch.getMana());
		Label l4 = new Label ("Max Action Points per turn: " + ch.getMaxActionPointsPerTurn());
		Label l5 = new Label ("Speed: " + ch.getSpeed());
		Label l6 = new Label ("Attack Range: " + ch.getAttackRange());
		Label l7 = new Label ("Attack Damage: " + ch.getAttackDamage());
		Label lx = new Label ();
		if(ch instanceof Hero)
		{
			lx.setText("Type: Hero");
		}
		else
		{
			if(ch instanceof AntiHero)
				lx.setText("Type: AntiHero");
			else
				lx.setText("Type: Villain");
		}
		
		String k ="";
		for (int j=0; j<ch.getAbilities().size();j++)
		{
			k+= "\n\nAbility " + (j+1) +":\n" + ch.getAbilities().get(j).toString();
			if(ch.getAbilities().get(j) instanceof DamagingAbility)
			{
				k+= "\nType: Damaging Ability\nDamage Amount: " +
						((DamagingAbility) ch.getAbilities().get(j)).getDamageAmount();
			}
			if(ch.getAbilities().get(j) instanceof HealingAbility)
			{
				k+= "\nType: Healing Ability\nHeal Amount: " +
						((HealingAbility) ch.getAbilities().get(j)).getHealAmount();
			}
			if(ch.getAbilities().get(j) instanceof CrowdControlAbility)
			{
				k+= "\nType: Crowd Control Ability\nEffect Info: \n" +
						((CrowdControlAbility) ch.getAbilities().get(j)).getEffect().toString();
			}
			
		}
		Label l8 = new Label ("Abilties: " + k);
		
		formatLabel(l);
		formatLabel(l1);
		formatLabel(l2);
		formatLabel(l3);
		formatLabel(l4);
		formatLabel(l5);
		formatLabel(l6);
		formatLabel(l7);
		formatLabel(l8);
		formatLabel(lx);
		
		mainInfo.getChildren().addAll(l,l1,l2,l3,l4,l5,l6,l7,lx);
		abilitiesInfo.getChildren().addAll(l8);
		
		
		Image image = new Image(availableImages.get(0)); 
		
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setX(50);
	    imageView.setY(50);
	    imageView.setFitWidth(250);
	    imageView.setFitHeight(500);
	    
	    infoBox.getChildren().addAll(mainInfo, abilitiesInfo,imageView);
		
	}

	private Scene prepareScene5(Stage primaryStage, ArrayList<Champion> availableChampions,
			ArrayList<String> availableImages, Player player1, Player player2, ArrayList<String> player1TeamIcons,
			ArrayList<String> player2TeamImages, ArrayList<String> player2TeamIcons) 
	{
		
		VBox top = new VBox (10);
		Label playerLabel = new Label ("Player 2: " + n2);
		playerLabel.setFont(new Font("Broadway", 24));
		playerLabel.setTextFill(Color.color(1, 1, 1));
		
		Label chooseLeaderLabel = new Label ("Choose your Leader");
		chooseLeaderLabel.setFont(new Font("Broadway", 24));
		chooseLeaderLabel.setTextFill(Color.color(1, 1, 1));
		
		top.getChildren().addAll(playerLabel, chooseLeaderLabel);
		
		HBox allLeaderOptions = new HBox (20);
		
		for (int i=0; i<3; i++)
		{
			Champion ch = player2.getTeam().get(i);
			VBox leaderOption = new VBox (30);
			
			Label leaderName = new Label (player2.getTeam().get(i).getName());
			leaderName.setFont(new Font("Algerian", 22));
			leaderName.setTextFill(Color.color(1, 1, 1));
			
			Image image = new Image(player2TeamImages.get(i)); 
			availableImages.add(player2TeamImages.get(i));
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setX(50);
		    imageView.setY(50);
		    imageView.setFitWidth(300);
		    imageView.setFitHeight(500);
		    //imageView.setPreserveRatio(true);
		    
		    Button chooseLeader = new Button ("Choose Leader");
		    chooseLeader.setFont(new Font("Algerian", 18));
		    chooseLeader.setPrefSize(300, 50);
		    chooseLeader.setOnAction( e->	
		    {		
		    	player2.setLeader(ch);	
		    	primaryStage.setScene(prepareScene6(primaryStage, player1, player2));
		    }
		    );
		    
		    leaderOption.getChildren().addAll(leaderName, imageView, chooseLeader);
		    
		    allLeaderOptions.getChildren().addAll(leaderOption);
		   
		}

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20,20,20,20));
		grid.setVgap(8);
		grid.setHgap(10);
		GridPane.setConstraints(allLeaderOptions, 8, 12);
		GridPane.setConstraints(top, 3, 12);
		grid.getChildren().addAll(top,allLeaderOptions);
		
		Image img = new Image("File:galaxy.jpg");
		BackgroundImage bImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
	    BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    Background bGround = new Background(bImg);
	    grid.setBackground(bGround);
		Scene scene= new Scene(grid,1550,800);
		
		
		return scene;
	}

	private Scene prepareScene6(Stage primaryStage, Player player1, Player player2) 
	{
		Game game = new Game(player1, player2);
		
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20,20,20,20));
		grid.setVgap(8);
		grid.setHgap(10);
		
		VBox all = updateBoard(game);
		VBox currentInfo = displayCurrentInfo (game);
		
		HBox actions =actionButtons(game,all, currentInfo, primaryStage);
		
		//VBox boardandbuttons = new VBox(30);
		
		//boardandbuttons.getChildren().addAll(board, actions);
		VBox actionsandcurrentInfo = new VBox(50);
		actionsandcurrentInfo.getChildren().clear();
		actionsandcurrentInfo.getChildren().addAll(currentInfo, actions);
		HBox allall = new HBox (80);
		
		allall.getChildren().addAll(all,actionsandcurrentInfo);
		
		GridPane.setConstraints(allall,1, 2);
		grid.getChildren().addAll(allall);
		
		Image img = new Image("File:scene6.png");
		BackgroundImage bImg = new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
	    BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
	    Background bGround = new Background(bImg);
	    grid.setBackground(bGround);
		
		
		Scene scene= new Scene(grid,1550,800);
		return scene;
		
		
		
		
		
	}

	private void prepareImages (ArrayList<String> images)
	{
		images.add("file:camerica.jpg");
		images.add("file:deadpool.jpg");
		images.add("file:drstrange.jpg");
		images.add("file:electro.jpg");
		images.add("file:ghostrider.jpg");
		images.add("file:hela.jpg");
		images.add("file:hulk.jpg");
		images.add("file:iceman.jpg");
		images.add("file:ironman.jpg");
		images.add("file:loki.jpg");
		images.add("file:quicksilver.jpg");
		images.add("file:spiderman.jpg");
		images.add("file:thor.jpg");
		images.add("file:venom.jpg");
		images.add("file:yellowjacket.jpg");
		
	}
	
	private void prepareIcons(ArrayList<String> images)
	{
		images.add("file:camericaIcon.jpg");
		images.add("file:deadpoolIcon.jpg");
		images.add("file:drstrangeIcon.jpg");
		images.add("file:electroIcon.jpg");
		images.add("file:ghostriderIcon.jpg");
		images.add("file:helaIcon.jpg");
		images.add("file:hulkIcon.jpg");
		images.add("file:icemanIcon.jpg");
		images.add("file:ironmanIcon.jpg");
		images.add("file:lokiIcon.jpg");
		images.add("file:quicksilverIcon.jpg");
		images.add("file:spidermanIcon.jpg");
		images.add("file:thorIcon.jpg");
		images.add("file:venomIcon.jpg");
		images.add("file:yellowjacketIcon.jpg");
	}
	
	private HBox actionButtons (Game g,VBox all, VBox currentInfo,Stage primaryStage)
	{
		
		updateBoard(g, all);
		
		HBox actions = new HBox (10);
		
		Button move = new Button ("Move");
		move.setFont(new Font ("Broadway", 14));
		move.setPrefSize(160, 50);
		
		Button attack = new Button ("Attack");
		attack.setFont(new Font ("Broadway", 14));
		attack.setPrefSize(160, 50);
		
		Button castAbility = new Button ("Cast Ability");
		castAbility.setFont(new Font ("Broadway", 14));
		castAbility.setPrefSize(160, 50);
		
		Button useLeaderAbility = new Button ("Use Leader Ability");
		useLeaderAbility.setFont(new Font ("Broadway", 14));
		useLeaderAbility.setPrefSize(160, 50);
		
		Button endTurn = new Button ("End Turn");
		endTurn.setFont(new Font ("Broadway", 14));
		endTurn.setPrefSize(330, 50);
		
		
		VBox buttons = new VBox (10);
		HBox h1 = new HBox (10);
		HBox h2 = new HBox (10);
		h1.getChildren().addAll(move, attack);
		h2.getChildren().addAll(castAbility, useLeaderAbility);
		buttons.getChildren().addAll(h1,h2,endTurn);
		
		
		actions.getChildren().addAll(buttons);
		
		
		move.setOnAction( e->  
		{
			updateBoard(g, all);
			actions.getChildren().clear();
			actions.getChildren().addAll(buttons);
			HBox display = HBoxMove (g, all,currentInfo);
			actions.getChildren().addAll(display);
			
			
			
			
			
		});
		attack.setOnAction( e->  
		{
			
			 
			updateBoard(g, all);
			
			actions.getChildren().clear();
			actions.getChildren().addAll(buttons);
			HBox display = HBoxAttack (g, all,currentInfo, primaryStage);
			actions.getChildren().addAll(display);
						
		});
		castAbility.setOnAction( e->  
		{
			updateBoard(g, all);
			actions.getChildren().clear();
			actions.getChildren().addAll(buttons);
			VBox display = new VBox (10);
			
			
			
			Button a1 = new Button (g.getCurrentChampion().getAbilities().get(0).getName());
			Button a2 = new Button (g.getCurrentChampion().getAbilities().get(1).getName());
			Button a3 = new Button (g.getCurrentChampion().getAbilities().get(2).getName());
			
			a1.setFont(new Font ("Broadway", 14));
			a2.setFont(new Font ("Broadway", 14));
			a3.setFont(new Font ("Broadway", 14));
			a1.setPrefSize(220, 50);
			a2.setPrefSize(220, 50);
			a3.setPrefSize(220, 50);
			display.getChildren().addAll(a1,a2,a3);
			
			
			actions.getChildren().addAll(display);
			
			Ability aa1 = g.getCurrentChampion().getAbilities().get(0);
			Ability aa2 = g.getCurrentChampion().getAbilities().get(1);
			Ability aa3 = g.getCurrentChampion().getAbilities().get(2);
			
			a1.setOnAction( e1 ->
			{
				if(aa1.getCastArea()==AreaOfEffect.DIRECTIONAL)	
				{
					HBox display2 = HBoxAbilityDirectional(g, aa1,all, currentInfo, primaryStage);
					actions.getChildren().clear();
					actions.getChildren().add(buttons);
					actions.getChildren().add(display2);
					actions.getChildren().add(abilityinfo(g,aa1));
					
					
				}
				else if (aa1.getCastArea()==AreaOfEffect.SINGLETARGET)
				{
					HBox display2 = babyBoardAbility(g,all,  currentInfo, primaryStage,  aa1);
					actions.getChildren().clear();
					actions.getChildren().add(buttons);
					actions.getChildren().add(display2);
					actions.getChildren().add(abilityinfo(g,aa1));
				}
				else
				{
					
					actions.getChildren().clear();
					actions.getChildren().add(buttons);
					actions.getChildren().add(cast(g,aa1,all,currentInfo, primaryStage));
					actions.getChildren().add(abilityinfo(g,aa1));
					displayCurrentInfo(g, currentInfo);
					updateBoard(g, all);
					endGame (g, primaryStage);
					
				}
				displayCurrentInfo(g, currentInfo);
				updateBoard(g, all);
				endGame (g, primaryStage);
			}
			);
			a2.setOnAction( e1 ->
			{
				if(aa2.getCastArea()==AreaOfEffect.DIRECTIONAL)	
				{
					HBox display2 = HBoxAbilityDirectional(g,aa2, all, currentInfo, primaryStage);
					actions.getChildren().clear();
					actions.getChildren().add(buttons);
					actions.getChildren().add(display2);
					actions.getChildren().add(abilityinfo(g,aa2));
				}
				else if (aa2.getCastArea()==AreaOfEffect.SINGLETARGET)
				{
					HBox display2 = babyBoardAbility(g,all,  currentInfo, primaryStage, aa2);
					actions.getChildren().clear();
					actions.getChildren().add(buttons);
					actions.getChildren().add(display2);
					actions.getChildren().add(abilityinfo(g,aa2));
				}
				else
				{
					
					actions.getChildren().clear();
					actions.getChildren().add(buttons);
					actions.getChildren().add(cast(g,aa2,all,currentInfo, primaryStage));
					actions.getChildren().add(abilityinfo(g,aa2));
					displayCurrentInfo(g, currentInfo);
					updateBoard(g, all);
					endGame (g, primaryStage);
					
					
				}
				displayCurrentInfo(g, currentInfo);
				updateBoard(g, all);
				endGame (g, primaryStage);
			}
			);
			a3.setOnAction( e1 ->
			{
				if(aa3.getCastArea()==AreaOfEffect.DIRECTIONAL)	
				{
					HBox display2 = HBoxAbilityDirectional(g,aa3, all,  currentInfo, primaryStage);
					actions.getChildren().clear();
					actions.getChildren().add(buttons);
					actions.getChildren().add(display2);
					actions.getChildren().add(abilityinfo(g,aa3));
				}
				else if (aa3.getCastArea()==AreaOfEffect.SINGLETARGET)
				{
					HBox display2 = babyBoardAbility(g,all,  currentInfo, primaryStage, aa3);
					actions.getChildren().clear();
					actions.getChildren().add(buttons);
					actions.getChildren().add(display2);
					actions.getChildren().add(abilityinfo(g,aa3));
				}
				else
				{
					
					actions.getChildren().clear();
					actions.getChildren().add(buttons);
					actions.getChildren().add(cast(g,aa3,all,currentInfo, primaryStage));
					actions.getChildren().add(abilityinfo(g,aa3));
					displayCurrentInfo(g, currentInfo);
					updateBoard(g, all);
					endGame (g, primaryStage);
				}
				displayCurrentInfo(g, currentInfo);
				updateBoard(g, all);
				endGame (g, primaryStage);
			}
			);
			if (g.getCurrentChampion().getAbilities().size()>3)
			{
				Button a4 = new Button (g.getCurrentChampion().getAbilities().get(3).getName());
				a4.setFont(new Font ("Broadway", 12));
				a4.setPrefSize(220, 35);
				
				a1.setFont(new Font ("Broadway", 12));
				a2.setFont(new Font ("Broadway", 12));
				a3.setFont(new Font ("Broadway", 12));
				a1.setPrefSize(220, 35);
				a2.setPrefSize(220, 35);
				a3.setPrefSize(220, 35);
				
				display.getChildren().addAll(a4);
				Ability aa4 = g.getCurrentChampion().getAbilities().get(3);
				a4.setOnAction( e1 ->
				{
					if(aa4.getCastArea()==AreaOfEffect.DIRECTIONAL)	
					{
						HBox display2 = HBoxAbilityDirectional(g,aa4, all,  currentInfo, primaryStage);
						actions.getChildren().clear();
						actions.getChildren().add(buttons);
						actions.getChildren().add(display2);
						actions.getChildren().add(abilityinfo(g,aa4));
					}
					else if (aa4.getCastArea()==AreaOfEffect.SINGLETARGET)
					{
						HBox display2 = babyBoardAbility(g,all,  currentInfo, primaryStage, aa4);
						actions.getChildren().clear();
						actions.getChildren().add(buttons);
						actions.getChildren().add(display2);
						actions.getChildren().add(abilityinfo(g,aa4));
					}
					else
					{
						
						actions.getChildren().clear();
						actions.getChildren().add(buttons);
						actions.getChildren().add(cast(g,aa4,all,currentInfo, primaryStage));
						actions.getChildren().add(abilityinfo(g,aa4));
						displayCurrentInfo(g, currentInfo);
						updateBoard(g, all);
						endGame (g, primaryStage);
					}
					displayCurrentInfo(g, currentInfo);
					updateBoard(g, all);
					endGame (g, primaryStage);
				}
				);
			}
			
			
			
			
		});
		useLeaderAbility.setOnAction( e->  
		{
			updateBoard(g, all);
			try 
			{
				g.useLeaderAbility();
			}
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			};
			displayCurrentInfo(g, currentInfo);
			updateBoard(g, all);
			endGame (g, primaryStage);
			
		});
		endTurn.setOnAction( e->  
		{
			updateBoard(g, all);
			try 
			{
				g.endTurn();
				
			}
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			};
			displayCurrentInfo(g, currentInfo);
			updateBoard(g, all);
			endGame (g, primaryStage);
		});
		
		
		
		return actions;
		
		
		
	}
	
	private HBox HBoxMove (Game g, VBox all, VBox currentInfo)
	{
		HBox HDirections = new HBox (10);
		VBox VDirections1 = new VBox (10);
		VBox VDirections2 = new VBox (10);
		VBox VDirections3 = new VBox (10);
		
		Button v11 = new Button ();
		Button v12 = new Button ("Left");
		Button v13 = new Button ();
		VDirections1.getChildren().addAll(v11,v12,v13);
		
		Button v21 = new Button ("Up");
		Button v22 = new Button ();
		Button v23 = new Button ("Down");
		
		VDirections2.getChildren().addAll(v21,v22,v23);
		
		Button v31 = new Button ();
		Button v32 = new Button ("Right");
		Button v33 = new Button ();
		VDirections3.getChildren().addAll(v31,v32,v33);
		
		v12.setFont(new Font ("Broadway", 14));
		v21.setFont(new Font ("Broadway", 14));
		v23.setFont(new Font ("Broadway", 14));
		v32.setFont(new Font ("Broadway", 14));
		
		
		v11.setPrefSize(65, 50);
		v12.setPrefSize(65, 50);
		v13.setPrefSize(65, 50);
		v21.setPrefSize(65, 50);
		v22.setPrefSize(65, 50);
		v23.setPrefSize(65, 50);
		v31.setPrefSize(65, 50);
		v32.setPrefSize(65, 50);
		v33.setPrefSize(65, 50);
		
		v13.setVisible(false);
		v22.setVisible(false);
		v31.setVisible(false);
		v11.setVisible(false);
		v33.setVisible(false);
	
		HDirections.getChildren().addAll(VDirections1, VDirections2, VDirections3);
		
		
		v12.setOnAction( e->  
		{
			try 
			{
				g.move(Direction.LEFT);
				
			}
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			}
			updateBoard(g, all);
			displayCurrentInfo(g, currentInfo);
		}
		);
		
		v21.setOnAction( e->  
		{
			try 
			{
				g.move(Direction.UP);
							
			}
			
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			}
			updateBoard(g, all);
			displayCurrentInfo(g, currentInfo);
		}
		);
		v23.setOnAction( e->  
		{
			try 
			{
				g.move(Direction.DOWN);
				
			}
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			}
			updateBoard(g, all);
			displayCurrentInfo(g, currentInfo);
		}
		);
		v32.setOnAction( e->  
		{
			try 
			{
				g.move(Direction.RIGHT);
				
			}
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			}
			updateBoard(g, all);
			displayCurrentInfo(g, currentInfo);
		}
		);
		
		
		return HDirections;
	}
	
	private HBox HBoxAttack (Game g,VBox all, VBox currentInfo, Stage primaryStage)
	{
		HBox HDirections = new HBox (10);
		VBox VDirections1 = new VBox (10);
		VBox VDirections2 = new VBox (10);
		VBox VDirections3 = new VBox (10);
		
		Button v11 = new Button ();
		Button v12 = new Button ("Left");
		Button v13 = new Button ();
		VDirections1.getChildren().addAll(v11,v12,v13);
		
		Button v21 = new Button ("Up");
		Button v22 = new Button ();
		Button v23 = new Button ("Down");
		VDirections2.getChildren().addAll(v21,v22,v23);
		
		Button v31 = new Button ();
		Button v32 = new Button ("Right");
		Button v33 = new Button ();
		VDirections3.getChildren().addAll(v31,v32,v33);
		
		
		v12.setFont(new Font ("Broadway", 14));
		v21.setFont(new Font ("Broadway", 14));
		v23.setFont(new Font ("Broadway", 14));
		v32.setFont(new Font ("Broadway", 14));
		

		v11.setPrefSize(65, 50);
		v12.setPrefSize(65, 50);
		v13.setPrefSize(65, 50);
		v21.setPrefSize(65, 50);
		v22.setPrefSize(65, 50);
		v23.setPrefSize(65, 50);
		v31.setPrefSize(65, 50);
		v32.setPrefSize(65, 50);
		v33.setPrefSize(65, 50);
		
		v13.setVisible(false);
		v22.setVisible(false);
		v31.setVisible(false);
		v11.setVisible(false);
		v33.setVisible(false);
		
		
		
		
		HDirections.getChildren().addAll(VDirections1, VDirections2, VDirections3);
		
		v12.setOnAction( e->  
		{
			try 
			{
				int [][] k = prepareHP (g);
				int [][]l = prepareDebuffNo (g);
				int [][]m = prepareBuffNo (g);
				
				g.attack(Direction.LEFT);
				updateView(g,all,k,l,m);
				
			}
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			}
			
			displayCurrentInfo(g, currentInfo);
			endGame (g, primaryStage);
			//updateBoard(g, all);

		}
		);
		
		v21.setOnAction( e->  
		{
			try 
			{
				int [][] k = prepareHP (g);
				int [][]l = prepareDebuffNo (g);
				int [][]m = prepareBuffNo (g);
				
				g.attack(Direction.UP);
				updateView(g,all,k,l,m);
				
			}
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			}
			//updateBoard(g, all);
			displayCurrentInfo(g, currentInfo);
			endGame (g, primaryStage);
			
		}
		);
		v23.setOnAction( e->  
		{
			try 
			{
				int [][] k = prepareHP (g);
				int [][]l = prepareDebuffNo (g);
				int [][]m = prepareBuffNo (g);
				
				g.attack(Direction.DOWN);
				updateView(g,all,k,l,m);
				
			}
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			}
			//updateBoard(g, all);
			displayCurrentInfo(g, currentInfo);
			endGame (g, primaryStage);
		}
		);
		v32.setOnAction( e->  
		{
			try 
			{
				int [][] k = prepareHP (g);
				int [][]l = prepareDebuffNo (g);
				int [][]m = prepareBuffNo (g);
				
				g.attack(Direction.RIGHT);
				updateView(g,all,k,l,m);
				
				
				
			}
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			}
			//updateBoard(g, all);
			displayCurrentInfo(g, currentInfo);
			endGame (g, primaryStage);
		}
		);
		
		
		return HDirections;
	}
	
	private HBox HBoxAbilityDirectional (Game g, Ability a, VBox all, VBox currentInfo, Stage primaryStage)
	{
		HBox HDirections = new HBox (10);
		VBox VDirections1 = new VBox (10);
		VBox VDirections2 = new VBox (10);
		VBox VDirections3 = new VBox (10);
		
		Button v11 = new Button ();
		Button v12 = new Button ("Left");
		Button v13 = new Button ();
		VDirections1.getChildren().addAll(v11,v12,v13);
		
		Button v21 = new Button ("Up");
		Button v22 = new Button ();
		Button v23 = new Button ("Down");
		VDirections2.getChildren().addAll(v21,v22,v23);
		
		Button v31 = new Button ();
		Button v32 = new Button ("Right");
		Button v33 = new Button ();
		VDirections3.getChildren().addAll(v31,v32,v33);
		
		v12.setFont(new Font ("Broadway", 14));
		v21.setFont(new Font ("Broadway", 14));
		v23.setFont(new Font ("Broadway", 14));
		v32.setFont(new Font ("Broadway", 14));
		

		v11.setPrefSize(65, 50);
		v12.setPrefSize(65, 50);
		v13.setPrefSize(65, 50);
		v21.setPrefSize(65, 50);
		v22.setPrefSize(65, 50);
		v23.setPrefSize(65, 50);
		v31.setPrefSize(65, 50);
		v32.setPrefSize(65, 50);
		v33.setPrefSize(65, 50);
		
		v13.setVisible(false);
		v22.setVisible(false);
		v31.setVisible(false);
		v11.setVisible(false);
		v33.setVisible(false);
		
		HDirections.getChildren().addAll(VDirections1, VDirections2, VDirections3);
		
		v12.setOnAction( e->  
		{
			try 
			{
				int [][] k = prepareHP (g);
				int [][]l = prepareDebuffNo (g);
				int [][]m = prepareBuffNo (g);
				
				g.castAbility(a, Direction.LEFT);
				updateView(g,all,k,l,m);
				
			}
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			}
			displayCurrentInfo(g, currentInfo);
			//updateBoard(g, all);
			endGame (g, primaryStage);
		}
		);
		
		v21.setOnAction( e->  
		{
			try 
			{
				int [][] k = prepareHP (g);
				int [][]l = prepareDebuffNo (g);
				int [][]m = prepareBuffNo (g);
				
				g.castAbility(a, Direction.UP);
				updateView(g,all,k,l,m);
				
			}
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			}
			displayCurrentInfo(g, currentInfo);
			//updateBoard(g, all);
			endGame (g, primaryStage);
		}
		);
		v23.setOnAction( e->  
		{
			try 
			{
				int [][] k = prepareHP (g);
				int [][]l = prepareDebuffNo (g);
				int [][]m = prepareBuffNo (g);
				
				g.castAbility(a, Direction.DOWN);
				updateView(g,all,k,l,m);
				
			}
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			}
			displayCurrentInfo(g, currentInfo);
			//updateBoard(g, all);
			endGame (g, primaryStage);
		}
		);
		v32.setOnAction( e->  
		{
			try 
			{
				int [][] k = prepareHP (g);
				int [][]l = prepareDebuffNo (g);
				int [][]m = prepareBuffNo (g);
				
				g.castAbility(a, Direction.RIGHT);
				updateView(g,all,k,l,m);
				
				
			}
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			}
			displayCurrentInfo(g, currentInfo);
			//updateBoard(g, all);
			endGame (g, primaryStage);
		}
		);
		return HDirections;
	}

	private HBox babyBoardAbility (Game game,VBox all, VBox currentInfo, Stage primaryStage, Ability a)
	{
		
		Button [][] buttonBoardSmall = new Button [Game.getBoardwidth()][Game.getBoardheight()]; 
		HBox HButtons = new HBox (2);
		
		for (int i=0; i<Game.getBoardwidth();i++)
		{
			VBox VButtons = new VBox(27);
			VBox VButtons2 = new VBox(2);
			
			
			for (int j=Game.getBoardheight()-1; j>=0;j--)
			{
				
				buttonBoardSmall[j][i] = new Button();
				buttonBoardSmall[j][i].setPrefSize(45, 33);
				if(game.getBoard()[j][i] instanceof Cover )
				{
					Image image = new Image("file:cover.jpg"); 
					ImageView imageView2 = new ImageView();
					imageView2.setImage(image);
					imageView2.setX(50);
				    imageView2.setY(50);
				    imageView2.setFitWidth(28);
				    imageView2.setFitHeight(22);
				    
					buttonBoardSmall[j][i].setGraphic(imageView2);
					buttonBoardSmall[j][i].setStyle("-fx-background-color:#ffffff; ");
					
				}
				if(game.getBoard()[j][i] instanceof Champion )
				{
					Champion c= (Champion) game.getBoard()[j][i];
					for (int k=0; k<game.getFirstPlayer().getTeam().size();k++)
					{
						if (c==game.getFirstPlayer().getTeam().get(k))
						{
							Image image = new Image(returnIcon(game.getFirstPlayer().getTeam().get(k))); 
						    ImageView imageView2 = new ImageView();
							imageView2.setImage(image);
							imageView2.setX(50);
						    imageView2.setY(50);
						    imageView2.setFitWidth(28);
						    imageView2.setFitHeight(22);
						    buttonBoardSmall[j][i].setGraphic(imageView2);
						    buttonBoardSmall[j][i].setStyle("-fx-background-color:#800000; ");
						}
							
					}
					for (int k=0; k<game.getSecondPlayer().getTeam().size();k++)
					{
						if (c==game.getSecondPlayer().getTeam().get(k))
						{
							Image image = new Image(returnIcon(game.getSecondPlayer().getTeam().get(k))); 
							ImageView imageView2 = new ImageView();
							imageView2.setImage(image);
							imageView2.setX(50);
						    imageView2.setY(50);
						    imageView2.setFitWidth(28);
						    imageView2.setFitHeight(22);
						    buttonBoardSmall[j][i].setGraphic(imageView2);
						    buttonBoardSmall[j][i].setStyle("-fx-background-color:#0B0B45; ");
						}
							
					}
				}
				if (game.getBoard()[j][i] ==null)	
				{
					if ((i%2==0 && j%2==0) || (i%2==1 && j%2==1))
						buttonBoardSmall[j][i].setStyle("-fx-background-color:#1F456E; ");
					else
						buttonBoardSmall[j][i].setStyle("-fx-background-color:#757688; ");
				}
				VButtons2.getChildren().addAll(buttonBoardSmall[j][i]);
				
			}
			
			VButtons.getChildren().addAll(VButtons2);	
			HButtons.getChildren().add(VButtons);
		}
		
		for (int i=0; i<Game.getBoardwidth();i++)
		{
			
			for (int j=Game.getBoardheight()-1; j>=0;j--)
			{
				int y=i;
				int x=j;
				Button b= buttonBoardSmall[x][y];
				b.setOnAction( e->
				{
					
					try
					{
						int [][] k = prepareHP (game);
						int [][]l = prepareDebuffNo (game);
						int [][]m = prepareBuffNo (game);
						
						game.castAbility(a,x,y);
						updateView(game,all,k,l,m);
						//HButtons.getChildren().removeAll(VButtons);
					}
					catch (Exception ex)
					{
						ExceptionAlert.display("Invalid Action", ex.getMessage());
					}
					
					displayCurrentInfo(game, currentInfo);
					//updateBoard(game, all);
					endGame (game, primaryStage);
				});
			}
		}
		
		return HButtons;
	}
	
	private void updateBoard (Game game, VBox all)
	{
		Button [][] buttonBoard = new Button [Game.getBoardwidth()][Game.getBoardheight()]; 
		HBox HButtons = new HBox (2);
		HBox info = new HBox (50);
		
		for (int i=0; i<Game.getBoardwidth();i++)
		{
			VBox VButtons = new VBox(27);
			VBox VButtons1 = new VBox(2);
			
			
			for (int j=Game.getBoardheight()-1; j>=0;j--)
			{
				
				buttonBoard[j][i] = new Button();
				buttonBoard[j][i].setPrefSize(126, 100);
				
				
				if(game.getBoard()[j][i] instanceof Cover )
				{
					Cover c = (Cover) game.getBoard()[j][i];
					
					Image image = new Image("file:cover.jpg"); 
					ImageView imageView = new ImageView();
					imageView.setImage(image);
					imageView.setX(50);
				    imageView.setY(50);
				    imageView.setFitWidth(100);
				    imageView.setFitHeight(80);
				    //buttonBoard[j][i].setText ("Current HP: " + c.getCurrentHP());
					buttonBoard[j][i].setGraphic(imageView);
					buttonBoard[j][i].setStyle("-fx-background-color:#ffffff; ");
					
				}
				if(game.getBoard()[j][i] instanceof Champion )
				{
					Champion c= (Champion) game.getBoard()[j][i];
					/*if (c==game.getCurrentChampion())
					{
						buttonBoard[j][i].setStyle("-fx-border-color: #03C04A; -fx-border-width: 10px;");
					}*/
					for (int k=0; k<game.getFirstPlayer().getTeam().size();k++)
					{
						if (c==game.getFirstPlayer().getTeam().get(k))
						{
							Image image = new Image(returnIcon(game.getFirstPlayer().getTeam().get(k))); 
							ImageView imageView = new ImageView();
							imageView.setImage(image);
							imageView.setX(50);
						    imageView.setY(50);
						    imageView.setFitWidth(100);
						    imageView.setFitHeight(80);
						    buttonBoard[j][i].setGraphic(imageView);
						    buttonBoard[j][i].setStyle("-fx-background-color:#800000; ");
						    
						    
						}
							
					}
					for (int k=0; k<game.getSecondPlayer().getTeam().size();k++)
					{
						if (c==game.getSecondPlayer().getTeam().get(k))
						{
							Image image = new Image(returnIcon(game.getSecondPlayer().getTeam().get(k))); 
							ImageView imageView = new ImageView();
							imageView.setImage(image);
							imageView.setX(50);
						    imageView.setY(50);
						    imageView.setFitWidth(100);
						    imageView.setFitHeight(80);
						    buttonBoard[j][i].setGraphic(imageView);
						    buttonBoard[j][i].setStyle("-fx-background-color:#0B0B45; ");
						    
						   
						}
							
					}
				}
				if (game.getBoard()[j][i] ==null)	
				{
					if ((i%2==0 && j%2==0) || (i%2==1 && j%2==1))
						buttonBoard[j][i].setStyle("-fx-background-color:#1F456E; ");
					else
						buttonBoard[j][i].setStyle("-fx-background-color:#757688; ");
				}
				displayInfo (game, info, buttonBoard[j][i], game.getBoard()[j][i]);
				VButtons1.getChildren().addAll(buttonBoard[j][i]);
				
				
			}
			
			VButtons.getChildren().addAll(VButtons1);	
			HButtons.getChildren().add(VButtons);
		}
		
		all.getChildren().clear();
		all.getChildren().addAll(HButtons, info);
		
	}
	
	private VBox updateBoard (Game game)
	{
		Button [][] buttonBoard = new Button [Game.getBoardwidth()][Game.getBoardheight()]; 
		HBox HButtons = new HBox (2);
		HBox info = new HBox (50);
		
		for (int i=0; i<Game.getBoardwidth();i++)
		{
			VBox VButtons = new VBox(27);
			VBox VButtons1 = new VBox(2);
			
			
			for (int j=Game.getBoardheight()-1; j>=0;j--)
			{
				
				buttonBoard[j][i] = new Button();
				buttonBoard[j][i].setPrefSize(126, 100);
				
				if(game.getBoard()[j][i] instanceof Cover )
				{
					Image image = new Image("file:cover.jpg"); 
					ImageView imageView = new ImageView();
					imageView.setImage(image);
					imageView.setX(50);
				    imageView.setY(50);
				    imageView.setFitWidth(100);
				    imageView.setFitHeight(80);
				    
					buttonBoard[j][i].setGraphic(imageView);
					buttonBoard[j][i].setStyle("-fx-background-color:#ffffff; ");
					
				}
				if(game.getBoard()[j][i] instanceof Champion )
				{
					Champion c= (Champion) game.getBoard()[j][i];
					
					
					for (int k=0; k<game.getFirstPlayer().getTeam().size();k++)
					{
						if (c==game.getFirstPlayer().getTeam().get(k))
						{
							Image image = new Image(returnIcon(game.getFirstPlayer().getTeam().get(k))); 
							ImageView imageView = new ImageView();
							imageView.setImage(image);
							imageView.setX(50);
						    imageView.setY(50);
						    imageView.setFitWidth(100);
						    imageView.setFitHeight(80);
						    //buttonBoard[j][i].setText ("Current HP: " + c.getCurrentHP());
						    buttonBoard[j][i].setGraphic(imageView);
						    buttonBoard[j][i].setStyle("-fx-background-color:#800000; ");
						    
						    
						}
							
					}
					for (int k=0; k<game.getSecondPlayer().getTeam().size();k++)
					{
						if (c==game.getSecondPlayer().getTeam().get(k))
						{
							Image image = new Image(returnIcon(game.getSecondPlayer().getTeam().get(k))); 
							ImageView imageView = new ImageView();
							imageView.setImage(image);
							imageView.setX(50);
						    imageView.setY(50);
						    imageView.setFitWidth(100);
						    imageView.setFitHeight(80);
						    buttonBoard[j][i].setGraphic(imageView);
						    buttonBoard[j][i].setStyle("-fx-background-color:#0B0B45; ");
						    
						   
						}
							
					}
					/*if (c==game.getCurrentChampion())
					{
						buttonBoard[j][i].setStyle("-fx-border-color: #03C04A; -fx-border-width: 2px;");
					}*/
				}
				if (game.getBoard()[j][i] ==null)	
				{
					if ((i%2==0 && j%2==0) || (i%2==1 && j%2==1))
						buttonBoard[j][i].setStyle("-fx-background-color:#1F456E; ");
					else
						buttonBoard[j][i].setStyle("-fx-background-color:#757688; ");
				}
				 displayInfo (game, info, buttonBoard[j][i], game.getBoard()[j][i]);
				VButtons1.getChildren().addAll(buttonBoard[j][i]);
				
				
			}
			
			VButtons.getChildren().addAll(VButtons1);	
			HButtons.getChildren().add(VButtons);
		}
		VBox v= new VBox(50);
		v.getChildren().addAll(HButtons, info);
		 return v;
	}

	private void formatLabel (Label l)
	{
		l.setFont(new Font ("Cooper Black" , 12));
	}

	private void displayInfo (Game g, HBox info, Button buttonBoard, Object object)
	{
		
		VBox v1 = new VBox (10);
		VBox v2 = new VBox (10);
		VBox v3 = new VBox (3);
		if (object instanceof Champion)
		{
			Champion ch = (Champion) object;
			Label l  = new Label ("Information about Champion:\n");
			Label l1 = new Label ("Name: " + ch.getName());
			Label l2 = new Label ("Current HP: " + ch.getCurrentHP());
			Label l3 = new Label ("Mana: " + ch.getMana());
			Label l4 = new Label ("Max Action Points per turn: " + ch.getMaxActionPointsPerTurn());
			
			formatLabel(l);
			formatLabel(l1);
			formatLabel(l2);
			formatLabel(l3);
			formatLabel(l4);
			
			v1.getChildren().clear();
			v1.getChildren().addAll(l,l1,l2,l3,l4);
			
			Label l5 = new Label ("Speed: " + ch.getSpeed());
			Label l6 = new Label ("Attack Range: " + ch.getAttackRange());
			Label l7 = new Label ("Attack Damage: " + ch.getAttackDamage());
			Label l8 = new Label (type(ch));
			Label l9 = new Label (isLeader (g,ch));
			
			formatLabel(l5);
			formatLabel(l6);
			formatLabel(l7);
			formatLabel(l8);
			formatLabel(l9);
			
			v2.getChildren().clear();
			v2.getChildren().addAll(l5,l6,l7,l8,l9);
			
			showEffects (v3, ch);
			
			buttonBoard.setOnAction(e->
			{
				info.getChildren().clear();
				info.getChildren().addAll(v1,v2,v3);
			});
			
		}
		if (object instanceof Cover)
		{
			Cover c = (Cover) object;
			Label l1  = new Label ("Information about Cover:\n");
			Label l = new Label ("Current HP: " + c.getCurrentHP());
			formatLabel(l);
			formatLabel(l1);
			v1.getChildren().clear();
			v1.getChildren().addAll(l1,l);
			buttonBoard.setOnAction(e->
			{
				info.getChildren().clear();
				info.getChildren().addAll(v1);
			});
		}
		
		
	}
	
	private String isLeader (Game g,Champion c)
	{
		String s;
		if (g.getFirstPlayer().getLeader()==c || g.getSecondPlayer().getLeader()==c)
			s= "Champion is a Team Leader";
		else
			s= "Champion is  NOT a Leader";
		return s;
	}

	private String type (Champion ch)
	{
		String s;
		if(ch instanceof Hero)
		{
			s=("Type: Hero");
		}
		else
		{
			if(ch instanceof AntiHero)
				s=("Type: AntiHero");
			else
				s=("Type: Villain");
		}
		return s;
	}

	private void showEffects(VBox v3, Champion c)
	{
		Label ll = new Label ("Applied Effects on Champion:");
		formatLabel(ll);
		v3.getChildren().clear();
		v3.getChildren().add(ll);
		for (int i=0; i<c.getAppliedEffects().size(); i++)
		{
			Label lll= new Label ("Effect " + (i+1) +":");
			Label l = new Label ("" +c.getAppliedEffects().get(i));
			formatLabel(lll);
			formatLabel(l);
			
			
			v3.getChildren().addAll(lll, l);
		}
		if (c.getAppliedEffects().size()==0)
		{
			Label empty= new Label ("\n\n\n\n");
			v3.getChildren().addAll(empty);
		}
	}

	private void displayCurrentInfo (Game g, VBox v)
	{
		v.getChildren().clear();
		HBox h1 = new HBox (40);
		HBox h2 = new HBox (10);
		HBox h3 = new HBox (10);
		VBox v1 = new VBox (4);
		VBox v2 = new VBox (10); //image
		HBox hx = playersinfo(g);
		
		
		HBox player = new HBox (15);
		Label x = player(g);
		player.getChildren().add(x);
		
		
		
		
		
		
		
		Image image = new Image(returnIcon(g.getCurrentChampion())); 
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setX(50);
	    imageView.setY(50);
	    imageView.setFitWidth(220);
	    imageView.setFitHeight(250);
	    
		v2.getChildren().add(imageView);
		VBox turnOrder = turnOrder(g);
		
		
		Champion ch = g.getCurrentChampion();
		Label l  = new Label ("Information about Champion\n");
		Label l1 = new Label ("Name: " + ch.getName());
		Label l2 =  new Label (type(ch));
		Label l8 = new Label ("Current HP: " + ch.getCurrentHP());
		Label l3 = new Label ("Mana: " + ch.getMana());
		Label l4 = new Label ("Current Action Points per turn: " + ch.getCurrentActionPoints());
		
		formatLabel(l);
		formatLabel(l1);
		formatLabel(l2);
		formatLabel(l3);
		formatLabel(l4);
		
		Label l5 = new Label ("Speed: " + ch.getSpeed());
		Label l6 = new Label ("Attack Range: " + ch.getAttackRange());
		Label l7 = new Label ("Attack Damage: " + ch.getAttackDamage());
		
		Label l9 = new Label (isLeader (g,ch));
		String j="Abilities:\n";
		for (int i=0; i<3; i++)
		{
			j+=  "- " + ch.getAbilities().get(i).getName()+  "\n";
		}
		Label l10 = new Label (j);
		
		formatLabel(l5);
		formatLabel(l6);
		formatLabel(l7);
		formatLabel(l8);
		formatLabel(l9);
		formatLabel(l10);
		
		
		//showEffects (v3, ch);
		showAbilities(h2, ch);
		v1.getChildren().clear();
		v1.getChildren().addAll(l,l1,l2,l8,l3,l4,l7,l6,l5,l9,l10);
		
		h1.getChildren().addAll(v1,v2,turnOrder);
			
		showCurrentEffects(h3,ch);
		
		v.getChildren().addAll(hx,player, h1,h3);
		
	}
	
	private void showAbilities(HBox h2, Champion ch)
	{
		
		h2.getChildren().clear();
		
		for (int j=0; j<ch.getAbilities().size();j++)
		{
			String k ="";
			k+= "\nAbility " + (j+1) +":\n" + ch.getAbilities().get(j).toString();
			if(ch.getAbilities().get(j) instanceof DamagingAbility)
			{
				k+= "\nType: Damaging Ability\nDamage Amount: " +
						((DamagingAbility) ch.getAbilities().get(j)).getDamageAmount();
			}
			if(ch.getAbilities().get(j) instanceof HealingAbility)
			{
				k+= "\nType: Healing Ability\nHeal Amount: " +
						((HealingAbility) ch.getAbilities().get(j)).getHealAmount();
			}
			if(ch.getAbilities().get(j) instanceof CrowdControlAbility)
			{
				k+= "\nType: Crowd Control Ability\nEffect Info: \n" +
						((CrowdControlAbility) ch.getAbilities().get(j)).getEffect().toString();
			}
			Label l = new Label(k);
			formatLabel(l);
			VBox v = new VBox(10);
			v.getChildren().add(l);
			h2.getChildren().add(v);
			
		}
		
	}

	
	private void showCurrentEffects(HBox h2, Champion ch)
	{
		
		h2.getChildren().clear();
		VBox v = new VBox(3);
		Label ll = new Label ("Applied Effects on Champion:");
		formatLabel(ll);
		v.getChildren().clear();
		v.getChildren().add(ll);
		
		for (int j=0; j<ch.getAppliedEffects().size();j++)
		{
			
			Label l = new Label(ch.getAppliedEffects().get(j)+"");
			formatLabel(l);
			
			v.getChildren().add(l);
			
		}
		
		if (ch.getAppliedEffects().size()==0)
		{
			Label empty= new Label ("\n\n\n\n");
			v.getChildren().addAll(empty);
		}
		if (ch.getAppliedEffects().size()==1)
		{
			Label empty= new Label ("\n\n\n");
			v.getChildren().addAll(empty);
		}
		if (ch.getAppliedEffects().size()==2)
		{
			Label empty= new Label ("\n\n");
			v.getChildren().addAll(empty);
		}
		if (ch.getAppliedEffects().size()==3)
		{
			Label empty= new Label ("\n");
			v.getChildren().addAll(empty);
		}
		h2.getChildren().add(v);
		
	}

	private VBox displayCurrentInfo (Game g)
	{
		VBox v = new VBox(10);
		v.getChildren().clear();
		HBox h1 = new HBox (40);
		HBox h2 = new HBox (10);
		HBox h3 = new HBox (10);
		VBox v1 = new VBox (4);
		VBox v2 = new VBox (10); //image
		HBox hx = playersinfo(g);
		
		HBox player = new HBox (15);
		Label x = player(g);
		player.getChildren().add(x);
		
		
		Image image = new Image(returnIcon(g.getCurrentChampion())); 
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setX(50);
	    imageView.setY(50);
	    imageView.setFitWidth(220);
	    imageView.setFitHeight(250);
	    
		v2.getChildren().add(imageView);
		
		VBox turnOrder = turnOrder(g);
		
		
		
		Champion ch = g.getCurrentChampion();
		Label l  = new Label ("Information about Champion\n");
		Label l1 = new Label ("Name: " + ch.getName());
		Label l2 =  new Label (type(ch));
		Label l8 = new Label ("Current HP: " + ch.getCurrentHP());
		Label l3 = new Label ("Mana: " + ch.getMana());
		Label l4 = new Label ("Current Action Points per turn: " + ch.getCurrentActionPoints());
		
		formatLabel(l);
		formatLabel(l1);
		formatLabel(l2);
		formatLabel(l3);
		formatLabel(l4);
		
		Label l5 = new Label ("Speed: " + ch.getSpeed());
		Label l6 = new Label ("Attack Range: " + ch.getAttackRange());
		Label l7 = new Label ("Attack Damage: " + ch.getAttackDamage());
		
		Label l9 = new Label (isLeader (g,ch));
		
		String j="Abilities:\n";
		for (int i=0; i<3; i++)
		{
			j+=  "- " + ch.getAbilities().get(i).getName()+  "\n";
		}
		Label l10 = new Label (j);
		
		formatLabel(l5);
		formatLabel(l6);
		formatLabel(l7);
		formatLabel(l8);
		formatLabel(l9);
		formatLabel(l10);
		
		
		//showEffects (v3, ch);
		showAbilities(h2, ch);
		v1.getChildren().clear();
		v1.getChildren().addAll(l,l1,l2,l8,l3,l4,l7,l6,l5,l9,l10);
		
		h1.getChildren().addAll(v1,v2,turnOrder);
			
		showCurrentEffects(h3,ch);
		
		v.getChildren().addAll(hx,player,h1,h3);
		return v;
		
	}

	private void endGame (Game g, Stage primaryStage)
	{
		if (g.checkGameOver()==null)
			return;
		else
		{
			if (g.checkGameOver() == g.getFirstPlayer())
				ExceptionAlert.display("GAME OVER", "Player 1 WON!!\nCONGRATULATIONS " + n1 + "!!!");
			else
				ExceptionAlert.display("GAME OVER", "Player 2 WON!!\nCONGRATULATIONS " + n2 + "!!!");
			primaryStage.close();
		}
	}

	private  String returnIcon (Champion c)
	{
		switch (c.getName())
		{
			case "Captain America" : return "file:camericaIcon.jpg";
			case "Deadpool" : return "file:deadpoolIcon.jpg";
			case "Dr Strange" : return "file:drstrangeIcon.jpg";
			case "Electro" : return "file:electroIcon.jpg";
			case "Ghost Rider": return "file:ghostriderIcon.jpg";
			case "Hela": return "file:helaIcon.jpg";
			case "Hulk": return "file:hulkIcon.jpg";
			case "Iceman": return "file:icemanIcon.jpg";
			case "Ironman": return "file:ironmanIcon.jpg";
			case "Loki": return "file:lokiIcon.jpg";
			case "Quicksilver": return "file:quicksilverIcon.jpg";
			case "Spiderman": return "file:spidermanIcon.jpg";
			case "Thor": return "file:thorIcon.jpg";
			case "Venom": return "file:venomIcon.jpg";
			case "Yellow Jacket": return "file:yellowjacketIcon.jpg";
			default: return null;

		}
	}

	private Label player (Game g)
	{
		Label l= new Label ();
		String s;
		Champion c= g.getCurrentChampion();
		if (Game.isPresent(c,g.getFirstPlayer().getTeam()))
		{
			s=("Player 1: " + n1 + "'s Turn");
			l.setText(s);
			l.setTextFill(Color.color(0.502, 0, 0));
			l.setFont((new Font ("Broadway", 24)));
		}
		else
		{
			s=("Player 2: " + n2 + "'s Turn");
			l.setText(s);
			l.setTextFill(Color.color(0.12, 0.12, 0.76));
			l.setFont((new Font ("Broadway", 24)));
			
		}
		return l;
		
			
	}
	private VBox turnOrder (Game g)
	{
		VBox turnOrder = new VBox (3);
		Label l = new Label ("Turn Order");
		l.setFont(new Font ("Broadway", 14));
		turnOrder.getChildren().add(l);
		ArrayList <Champion> turns= new ArrayList <Champion> ();
		PriorityQueue tmp = new PriorityQueue (6);
		while (!g.getTurnOrder().isEmpty())
		{
			turns.add((Champion)g.getTurnOrder().peekMin());
			tmp.insert(g.getTurnOrder().remove());
		}
		while (!tmp.isEmpty())
			g.getTurnOrder().insert(tmp.remove());
		int i;
		for (i=0; i<turns.size(); i++)
		{
			
			
			Button b = new Button (turns.get(i).getName());
			b.setFont(new Font ("Broadway", 12));
			b.setTextFill(Color.color(1, 1, 1));
			b.setPrefSize(160, 35);
			if (Game.isPresent(turns.get(i), g.getFirstPlayer().getTeam()))
				b.setStyle("-fx-background-color:#800000; ");

			else
				b.setStyle("-fx-background-color:#0B0B45; ");
			turnOrder.getChildren().add(b);
			
		}
		while (i<6)
		{
			Button b = new Button ();
			b.setPrefSize(160, 35);
			turnOrder.getChildren().add(b);
			i++;
		}
		return turnOrder;
			
	}
	private void  turnOrder (Game g, VBox turnOrder)
	{
		
		turnOrder.getChildren().clear();
		Label l = new Label ("Turn Order");
		l.setFont(new Font ("Broadway", 14));
		turnOrder.getChildren().add(l);
		
		ArrayList <Champion> turns= new ArrayList <Champion> ();
		PriorityQueue tmp = new PriorityQueue (6);
		while (!g.getTurnOrder().isEmpty())
		{
			turns.add((Champion)g.getTurnOrder().peekMin());
			tmp.insert(g.getTurnOrder().remove());
		}
		while (!tmp.isEmpty())
			g.getTurnOrder().insert(tmp.remove());
		int i;
		for (i=0; i<turns.size(); i++)
		{
			
			
			Button b = new Button (turns.get(i).getName());
			b.setFont(new Font ("Broadway", 12));
			b.setTextFill(Color.color(1, 1, 1));
			b.setPrefSize(160, 35);
			if (Game.isPresent(turns.get(i), g.getFirstPlayer().getTeam()))
				b.setStyle("-fx-background-color:#800000; ");

			else
				b.setStyle("-fx-background-color:#0B0B45; ");
			turnOrder.getChildren().add(b);
			
		}
		while (i<6)
		{
			Button b = new Button ();
			b.setPrefSize(160, 35);
			turnOrder.getChildren().add(b);
			i++;
		}
		
			
		
	}
	private VBox abilityinfo (Game g,Ability a)
	{
		VBox v = new VBox(10);
		Champion ch = g.getCurrentChampion();
		for (int j=0; j<ch.getAbilities().size();j++)
		{
			if(ch.getAbilities().get(j)== a)
			{
				String k ="";
				k+= "\nAbility " + (j+1) +":\n" + ch.getAbilities().get(j).toString();
				if(ch.getAbilities().get(j) instanceof DamagingAbility)
				{
					k+= "\nType: Damaging Ability\nDamage Amount: " +
							((DamagingAbility) ch.getAbilities().get(j)).getDamageAmount();
				}
				if(ch.getAbilities().get(j) instanceof HealingAbility)
				{
					k+= "\nType: Healing Ability\nHeal Amount: " +
							((HealingAbility) ch.getAbilities().get(j)).getHealAmount();
				}
				if(ch.getAbilities().get(j) instanceof CrowdControlAbility)
				{
					k+= "\nType: Crowd Control Ability\nEffect Info: \n" +
							((CrowdControlAbility) ch.getAbilities().get(j)).getEffect().toString();
				}
				Label l = new Label(k);
				l.setTextFill(Color.color(1, 1, 1));
				formatLabel(l);
				
				v.getChildren().add(l);
			}
			
			
			
		}
		return v;
	}
	
	private Button cast (Game g,Ability a, VBox all, VBox currentInfo, Stage primaryStage )
	{
		Button b = new Button ("Cast");
		b.setFont(new Font ("Broadway", 14));
		b.setPrefSize(180, 170);
		b.setOnAction( e-> 
		{
			try
			{
				int [][] x = prepareHP (g);
				int [][]y = prepareDebuffNo (g);
				int [][]z = prepareBuffNo (g);
				g.castAbility(a);
				updateView(g,all,x,y,z);
				
				
			}
			catch (Exception ex)
			{
				ExceptionAlert.display("Invalid Action", ex.getMessage());
			}
			displayCurrentInfo(g, currentInfo);
			//updateBoard(g, all);
			endGame (g, primaryStage);
		});
		return b;
		
	}
	private HBox playersinfo (Game g)
	{
		HBox h = new HBox (30);
		VBox v1 = new VBox (10);
		VBox v2 = new VBox (10);
		
		Label l1 = new Label ("Player 1: " + n1);
		l1.setTextFill(Color.color(0.502, 0, 0));
		Label l2 = new Label ();
		l1.setFont(new Font("Broadway", 20));
		l2.setFont(new Font("Broadway", 20));
		if(g.isFirstLeaderAbilityUsed())
			l2.setText("Leader Ability has been used");
		else
			l2.setText("Leader Ability hasn't been used");
		
		v1.getChildren().addAll(l1,l2);
		
		Label k1 = new Label ("Player 2: " + n2);
		k1.setTextFill(Color.color(0.12, 0.12, 0.76));
		Label k2 = new Label ();
		if(g.isSecondLeaderAbilityUsed())
			k2.setText("Leader Ability has been used");
		else
			k2.setText("Leader Ability hasn't been used");
		
		k1.setFont(new Font("Broadway", 20));
		k2.setFont(new Font("Broadway", 20));
		v2.getChildren().addAll(k1,k2);
		
		h.getChildren().addAll(v1, v2);
		return h;
	}

	private int[][] prepareHP (Game g)
	{
		int[][] hp = new int [5][5];
		
		for (int i=0; i<Game.getBoardwidth();i++)
		{
			for (int j=Game.getBoardheight()-1; j>=0;j--)
			{
				if(g.getBoard()[j][i] instanceof Damageable )
					hp[j][i] = ((Damageable) g.getBoard()[j][i] ).getCurrentHP();				
			}
		}
		return hp;
	}
	private void updateView (Game game, VBox all, int[][] hp1, int [][] debuffNo1, int [][] buffNo1) 
	{
		int[][] hp2 = new int [5][5];
		
		for (int i=0; i<Game.getBoardwidth();i++)
		{
			for (int j=Game.getBoardheight()-1; j>=0;j--)
			{
				if(game.getBoard()[j][i] instanceof Damageable )
					hp2[j][i] = ((Damageable) game.getBoard()[j][i] ).getCurrentHP();				
			}
		}
		int[][] debuffNo2 = new int [5][5];
		
		for (int i=0; i<Game.getBoardwidth();i++)
		{
			
			for (int j=Game.getBoardheight()-1; j>=0;j--)
			{	
				int counter =0;
				if (game.getBoard()[j][i] instanceof Champion)		
				{
					Champion c = (Champion)game.getBoard()[j][i];
					for (int k=0; k<c.getAppliedEffects().size(); k++)
					{
						if(c.getAppliedEffects().get(k).getType()==EffectType.DEBUFF)
							counter++;
					}
					debuffNo2 [j][i]=counter;
					
				}
			}
		}
		int[][] buffNo2 = new int [5][5];
		
		for (int i=0; i<Game.getBoardwidth();i++)
		{
			
			for (int j=Game.getBoardheight()-1; j>=0;j--)
			{	
				int counter =0;
				if (game.getBoard()[j][i] instanceof Champion)		
				{
					Champion c = (Champion)game.getBoard()[j][i];
					for (int k=0; k<c.getAppliedEffects().size(); k++)
					{
						if(c.getAppliedEffects().get(k).getType()==EffectType.BUFF)
							counter++;
					}
					buffNo2 [j][i]=counter;
					
				}
			}
		}
		
		Button [][] buttonBoard = new Button [Game.getBoardwidth()][Game.getBoardheight()]; 
		HBox HButtons = new HBox (2);
		HBox info = new HBox (50);
		
		for (int i=0; i<Game.getBoardwidth();i++)
		{
			VBox VButtons = new VBox(27);
			VBox VButtons1 = new VBox(2);
			
			
			for (int j=Game.getBoardheight()-1; j>=0;j--)
			{
				
				buttonBoard[j][i] = new Button();
				buttonBoard[j][i].setPrefSize(126, 100);
				
				if(game.getBoard()[j][i] instanceof Damageable )
				{

					if (hp1[j][i] > hp2[j][i] || debuffNo1[j][i] < debuffNo2 [j][i])
					{
						Image image = new Image("file:boom.gif"); 
						ImageView imageView = new ImageView();
						imageView.setImage(image);
						imageView.setX(50);
					    imageView.setY(50);
					    imageView.setFitWidth(110);
					    imageView.setFitHeight(90);
						buttonBoard[j][i].setGraphic(imageView);
						//Thread.sleep(2000);
					}
					else if (hp1[j][i] < hp2[j][i] || buffNo1[j][i] < buffNo2 [j][i] || debuffNo1[j][i] > debuffNo2 [j][i]) {
						Image image = new Image("file:new.gif"); 
						ImageView imageView = new ImageView();
						imageView.setImage(image);
						imageView.setX(50);
					    imageView.setY(50);
					    imageView.setFitWidth(110);
					    imageView.setFitHeight(90);
						buttonBoard[j][i].setGraphic(imageView);
					}	
					else if (buffNo1[j][i] > buffNo2 [j][i])
					{
						Image image = new Image("file:shield.gif"); 
						ImageView imageView = new ImageView();
						imageView.setImage(image);
						imageView.setX(50);
					    imageView.setY(50);
					    imageView.setFitWidth(110);
					    imageView.setFitHeight(90);
						buttonBoard[j][i].setGraphic(imageView);
					}
					
					
					else
					{
						if(game.getBoard()[j][i] instanceof Cover )
						{
							Cover c = (Cover) game.getBoard()[j][i];
							Image image = new Image("file:cover.jpg"); 
							ImageView imageView = new ImageView();
							imageView.setImage(image);
							imageView.setX(50);
						    imageView.setY(50);
						    imageView.setFitWidth(100);
						    imageView.setFitHeight(80);
							buttonBoard[j][i].setGraphic(imageView);
							buttonBoard[j][i].setStyle("-fx-background-color:#ffffff; ");
							
						}
						if(game.getBoard()[j][i] instanceof Champion )
						{
							Champion c= (Champion) game.getBoard()[j][i];
							/*if (c==game.getCurrentChampion())
							{
								buttonBoard[j][i].setStyle("-fx-border-color: #03C04A; -fx-border-width: 10px;");
							}*/
							for (int k=0; k<game.getFirstPlayer().getTeam().size();k++)
							{
								if (c==game.getFirstPlayer().getTeam().get(k))
								{
									Image image = new Image(returnIcon(game.getFirstPlayer().getTeam().get(k))); 
									ImageView imageView = new ImageView();
									imageView.setImage(image);
									imageView.setX(50);
								    imageView.setY(50);
								    imageView.setFitWidth(100);
								    imageView.setFitHeight(80);
								    buttonBoard[j][i].setGraphic(imageView);
								    buttonBoard[j][i].setStyle("-fx-background-color:#800000; ");
								    
								    
								}
									
							}
							for (int k=0; k<game.getSecondPlayer().getTeam().size();k++)
							{
								if (c==game.getSecondPlayer().getTeam().get(k))
								{
									Image image = new Image(returnIcon(game.getSecondPlayer().getTeam().get(k))); 
									ImageView imageView = new ImageView();
									imageView.setImage(image);
									imageView.setX(50);
								    imageView.setY(50);
								    imageView.setFitWidth(100);
								    imageView.setFitHeight(80);
								    buttonBoard[j][i].setGraphic(imageView);
								    buttonBoard[j][i].setStyle("-fx-background-color:#0B0B45; ");
								    
								   
								}
									
							}
						}
					}
				}
						
				
				if (game.getBoard()[j][i] ==null)	
				{
					if ((i%2==0 && j%2==0) || (i%2==1 && j%2==1))
						buttonBoard[j][i].setStyle("-fx-background-color:#1F456E; ");
					else
						buttonBoard[j][i].setStyle("-fx-background-color:#757688; ");
				}
				displayInfo (game, info, buttonBoard[j][i], game.getBoard()[j][i]);
				VButtons1.getChildren().addAll(buttonBoard[j][i]);
				
				
			}
			
			VButtons.getChildren().addAll(VButtons1);	
			HButtons.getChildren().add(VButtons);
		}
		
		all.getChildren().clear();
		all.getChildren().addAll(HButtons, info);
		
		
	}
	
	private int [][] prepareDebuffNo (Game g)
	{
		int[][] debuffNo = new int [5][5];
		
		for (int i=0; i<Game.getBoardwidth();i++)
		{
			
			for (int j=Game.getBoardheight()-1; j>=0;j--)
			{	
				int counter =0;
				if (g.getBoard()[j][i] instanceof Champion)		
				{
					Champion c = (Champion)g.getBoard()[j][i];
					for (int k=0; k<c.getAppliedEffects().size(); k++)
					{
						if(c.getAppliedEffects().get(k).getType()==EffectType.DEBUFF)
							counter++;
					}
					debuffNo [j][i]=counter;
					
				}
			}
		}
		return debuffNo;
	}
	private int [][] prepareBuffNo (Game g)
	{
		int[][] buffNo = new int [5][5];
		
		for (int i=0; i<Game.getBoardwidth();i++)
		{
			
			for (int j=Game.getBoardheight()-1; j>=0;j--)
			{	
				int counter =0;
				if (g.getBoard()[j][i] instanceof Champion)		
				{
					Champion c = (Champion)g.getBoard()[j][i];
					for (int k=0; k<c.getAppliedEffects().size(); k++)
					{
						if(c.getAppliedEffects().get(k).getType()==EffectType.BUFF)
							counter++;
					}
					buffNo [j][i]=counter;
					
				}
			}
		}
		return buffNo;
	}
	


}

	




	
	
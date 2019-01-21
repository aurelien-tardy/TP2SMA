package controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Grille;
import model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class tp2 extends Application implements Observer {

	public static Grille grille;

	private final static int TAILLE_CASE = 100;
	private final static int TAILLE_GRILLE = 5;
	private final static Color COULEUR_GRILLE = Color.TRANSPARENT;

	private GridPane gPaneGrilleInit;

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		List<List<Message>> boiteAuxLettres = new ArrayList<List<Message>>();

		grille = new Grille(TAILLE_GRILLE, 1);
		grille.addObserver(this);

		// Remplissage des deux grilles initiales et finales avec les position
		// des agents
		gPaneGrilleInit = this.initialisationGrille(TAILLE_GRILLE, TAILLE_GRILLE, COULEUR_GRILLE, TAILLE_CASE, false);
		GridPane gPaneGrilleFinale = this.initialisationGrille(TAILLE_GRILLE, TAILLE_GRILLE, COULEUR_GRILLE,
				TAILLE_CASE, true);

		// Création des onglets
		Group root = new Group();
		TabPane tabPane = new TabPane();
		BorderPane borderPane = new BorderPane();

		// Onglet de la grille initiale
		Tab tabI = new Tab();
		tabI.setText("Grille courrante");
		HBox hboxI = new HBox();
		hboxI.getChildren().add(gPaneGrilleInit);
		hboxI.setAlignment(Pos.CENTER);
		tabI.setContent(hboxI);
		tabPane.getTabs().add(tabI);

		// Onglet de la grille finale
		Tab tabF = new Tab();
		tabF.setText("Grille finale");
		HBox hboxF = new HBox();
		hboxF.getChildren().add(gPaneGrilleFinale);
		hboxF.setAlignment(Pos.CENTER);
		tabF.setContent(hboxF);
		tabPane.getTabs().add(tabF);

		// Gestion de la taille de la fenêtre
		Scene scene = new Scene(root, TAILLE_GRILLE * TAILLE_CASE, TAILLE_GRILLE * TAILLE_CASE + 50);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());

		borderPane.setCenter(tabPane);
		root.getChildren().add(borderPane);
		primaryStage.setTitle("Projet SMA");
		primaryStage.setScene(scene);
		primaryStage.show();

		grille.launchAgents();
	}

	/**
	 * Initialisation de la Grid
	 */
	private GridPane initialisationGrille(int width, int height, Color gridColor, int sizeCase, boolean grilleFinale) {
		GridPane gridPane = new GridPane();
		gridPane.setGridLinesVisible(true);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Rectangle rectangle = new Rectangle(sizeCase, sizeCase);
				rectangle.setFill(gridColor);
				rectangle.setStroke(Color.BLACK);
				rectangle.setStrokeWidth(2);
				gridPane.add(rectangle, i, j);
			}
		}

		// Ajout des contraintes
		for (int i = 0; i < width; i++) {
			ColumnConstraints column = new ColumnConstraints(sizeCase);
			gridPane.getColumnConstraints().add(column);
		}
		for (int j = 0; j < height; j++) {
			RowConstraints row = new RowConstraints(sizeCase);
			gridPane.getRowConstraints().add(row);
		}
		gridPane.setMaxSize(sizeCase * width, sizeCase * height);

		grille.getAgents().forEach(agent -> {
			if (agent != null) {
				// Ajout du cercle
				Circle c = new Circle();
				c.setRadius(50);
				c.setFill(Color.BLUE);

				// Ajout de l'id
				Text text = new Text("    A" + agent.getIdAgent());
				text.setFont(new Font(25));
				text.setFill(Color.BLACK);
				text.setX(25);
				text.setY(45);

				// Dessin des cercles à la bonne position
				if (!grilleFinale) {
					gridPane.add(c, agent.getPos().getY() - 1, agent.getPos().getX() - 1);
					gridPane.add(text, agent.getPos().getY() - 1, agent.getPos().getX() - 1);
				} else {
					gridPane.add(c, agent.getPosFinal().getY() - 1, agent.getPosFinal().getX() - 1);
					gridPane.add(text, agent.getPosFinal().getY() - 1, agent.getPosFinal().getX() - 1);
				}
			}
		});
		return gridPane;
	}

	public void updateGrille() {

		GridPane newGridPane = this.initialisationGrille(TAILLE_GRILLE, TAILLE_GRILLE, COULEUR_GRILLE, TAILLE_CASE,
				false);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gPaneGrilleInit.getChildren().clear();
				for (int i = 0; i < TAILLE_GRILLE; i++) {
					for (int j = 0; j < TAILLE_GRILLE; j++) {
						Rectangle rectangle = new Rectangle(TAILLE_CASE, TAILLE_CASE);
						rectangle.setFill(COULEUR_GRILLE);
						rectangle.setStroke(Color.BLACK);
						rectangle.setStrokeWidth(2);
						gPaneGrilleInit.add(rectangle, i, j);
					}
				}

				// Ajout des contraintes
				for (int i = 0; i < TAILLE_GRILLE; i++) {
					ColumnConstraints column = new ColumnConstraints(TAILLE_CASE);
					gPaneGrilleInit.getColumnConstraints().add(column);
				}
				for (int j = 0; j < TAILLE_GRILLE; j++) {
					RowConstraints row = new RowConstraints(TAILLE_CASE);
					gPaneGrilleInit.getRowConstraints().add(row);
				}
				gPaneGrilleInit.setMaxSize(TAILLE_CASE * TAILLE_GRILLE, TAILLE_CASE * TAILLE_GRILLE);

				grille.getAgents().forEach(agent -> {
					if (agent != null) {
						// Ajout du cercle
						Circle c = new Circle();
						c.setRadius(50);
						c.setFill(Color.BLUE);

						// Ajout de l'id
						Text text = new Text("    A" + agent.getIdAgent());
						text.setFont(new Font(25));
						text.setFill(Color.BLACK);
						text.setX(25);
						text.setY(45);

						gPaneGrilleInit.add(c, agent.getPos().getY() - 1, agent.getPos().getX() - 1);
						gPaneGrilleInit.add(text, agent.getPos().getY() - 1, agent.getPos().getX() - 1);

					}
				});

			}
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		this.updateGrille();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
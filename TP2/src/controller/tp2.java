package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Agent;
import model.Grille;
import model.Message;

public class tp2 extends Application {

	public static Grille grille;

	private final static int SIZE_CASE = 100;
	private final static Color GRID_COLOR = Color.TRANSPARENT;

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		int N = 5;

		List<List<Message>> boiteAuxLettres = new ArrayList<List<Message>>();

		grille = new Grille(N, 4);

		int tailleGrille = grille.getN();

		GridPane gPane = new GridPane();
		gPane.setGridLinesVisible(true);
		initialisationGrille(gPane, tailleGrille, tailleGrille, GRID_COLOR, SIZE_CASE);
		gPane.setGridLinesVisible(false);

		BorderPane border = new BorderPane();
		border.setCenter(gPane);

		final Scene scene = new Scene(border, tailleGrille * SIZE_CASE, tailleGrille * SIZE_CASE);
		// scene.setOnMouseClicked(controller);

		primaryStage.setTitle("Projet SMA");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Initialisation de la Grid
	 */
	private void initialisationGrille(GridPane gridPane, int width, int height, Color gridColor, int sizeCase) {
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
				Circle c = new Circle();
				c.setRadius(50);
				c.setFill(Color.RED);
				gridPane.draw
				System.out.println("agent " + agent.getPos().getX() + ", " + agent.getPos().getY());
				gridPane.add(c, agent.getPos().getY() - 1, agent.getPos().getX() - 1);
			}
		});

	}
}

package hr.fer.seminar.shapes;

import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GridField extends StackPane {
	private Shape disk;
	
	public GridField() {
		super();
		Rectangle area = new Rectangle(100, 100);
		Circle hole = new Circle(45);
		hole.centerXProperty().set(50);
		hole.centerYProperty().set(50);
		Shape cell = Path.subtract(area, hole);
		cell.setFill(Color.BLUE);
		cell.setStroke(Color.BLUE);
		cell.setOpacity(.85);
		
		InnerShadow effect = new InnerShadow();
		effect.setRadius(25);
		effect.setOffsetX(5.0f);
		effect.setOffsetY(5.0f);
		
		effect.setColor(Color.DARKBLUE);
		cell.setEffect(effect);
		
		disk = new Circle(45);
		disk.setFill(Color.TRANSPARENT);
		
		getChildren().addAll(cell, disk);
	}
	
	public void setColor(Color color){
		disk.setFill(color);
		disk.setOpacity(0.85);
	}
}

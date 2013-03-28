package com;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PageBrowser extends Application {
    private Timeline timeline;
    private ArrayList<Piece> pieces;
    private Desk desk;
    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setScene(new Scene(root));
        // load puzzle image
        Image image = null;
        try {
            image = new Image(getClass().getResource(
          "/Resources/a.jpg").openStream(),  150, 0, false, false);
        } catch (IOException ex) {
        }
        
        desk = new Desk();
        // create puzzle pieces
        pieces  = new ArrayList<>();
        
                for (int row = 0; row < 3; row++) {
                final Piece piece1 = new Piece(image, row * 150+ (row+1)*37.5, 75, 
                        desk.getWidth(), desk.getHeight());
                pieces.add(piece1);
                }
        
        // create button box
        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-font-size: 2em;");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent actionEvent) {
                nextPiece();
            }
        });
            
        desk.getChildren().addAll(pieces);
        HBox buttonBox = new HBox(8);
        
        buttonBox.getChildren().addAll(closeButton);
        // create vbox for desk and buttons
        VBox vb = new VBox(10);
        vb.getChildren().addAll(desk, buttonBox);
        root.getChildren().addAll(vb);
    }

    Image nextImage() {
        try {
            return new Image(getClass().getResource(
          "/Resources/a.jpg").openStream(),  150, 0, false, false);
        } catch (IOException ex) {
        }
        return null;
    }
    
    void nextPiece() {
        Image i = nextImage();
        desk.getChildren().remove(0);
        addNewPiece(i, 600, 75, 600, 400);
        if (timeline != null) timeline.stop();
            timeline = new Timeline();
            for (final Piece piece : pieces) {
                piece.setActive();
                double shuffleX = 150 + 37.5;
                double shuffleY = 50;
               
                timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(piece.translateXProperty(), -shuffleX),
                        new KeyValue(piece.translateYProperty(), 0)));
                
                piece.setTranslateX(piece.getCorrectX() - shuffleX);
                piece.setCorrectX(piece.getTranslateX());
                piece.setX();
            }
            removePiece();
        timeline.playFromStart();
        //
        desk.getChildren().add(pieces.get(pieces.size() - 1));
        
    }
    
    void addNewPiece(Image i, double x, double y, double deskWidth, double deskHeight) {
        pieces.add(new Piece(i, x, y, deskWidth, deskHeight));
    }
    void removePiece() {
        pieces.remove(0);
    }
    
    /**
     * Node that represents the playing area/ desktop where the puzzle pieces sit
     */
    public static class Desk extends Pane {
        Desk() {
            setStyle("-fx-background-color: #000000; " +
                    "-fx-border-color: #464646; " +
                    "-fx-effect: innershadow( two-pass-box , rgba(0,0,0,0.8) , 15, 0.0 , 0 , 4 );");
            double DESK_WIDTH = 600;
            double DESK_HEIGHT = 400;
            setPrefSize(DESK_WIDTH,DESK_HEIGHT);
            setMaxSize(DESK_WIDTH, DESK_HEIGHT);
            autosize();
            // create path for lines
            
        }
        @Override protected void layoutChildren() {}
    }

    /**
     * Node that represents a puzzle piece
     */
    public static class Piece extends Parent {
        public static final int HEIGHT = 250;
        public static final int WIDTH = 150;
        private double correctX;
        private double correctY;
        private ImageView imageView = new ImageView();
        
        public Piece(Image image, final double correctX, final double correctY,
                     final double deskWidth, final double deskHeight) {
            
            this.correctX = correctX;
            this.correctY = correctY;
            
            // create image view
            imageView.setImage(image);
            imageView.setTranslateX(correctX);
            imageView.setTranslateY(correctY);
            setFocusTraversable(true);
            getChildren().addAll(imageView);
            // turn on caching so the jigsaw piece is fasr to draw when dragging
            setCache(true);
            // start in inactive state
            setInactive();
            
        }

        private Shape createPiece() {
            Shape shape = createPieceRectangle();
            
            shape.setTranslateX(correctX);
            shape.setTranslateY(correctY);
//            shape.setLayoutX(100f);
//            shape.setLayoutY(100f);
            return shape;
        }

        private Rectangle createPieceRectangle() {
            Rectangle rec = new Rectangle();
            rec.setX(0);
            rec.setY(0);
            rec.setWidth(WIDTH);
            rec.setHeight(HEIGHT);
            return rec;
        }

        public void setActive() {
            setDisable(false);
            setEffect(new DropShadow());
            toFront();
        }

        public void setInactive() {
            setEffect(null);
            setDisable(true);
            toBack();
        }
        
        public void setCorrectX(double x) {
            correctX = x;
        }
        
        public void setX() {
            imageView.setTranslateX(correctX);
            imageView.setTranslateY(correctY);
        }
        
        public void setCorrectY(double y) {
            correctY = y;
        }

        public double getCorrectX() { return correctX; }

        public double getCorrectY() { return correctY; }
    }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX 
     * application. main() serves only as fallback in case the 
     * application can not be launched through deployment artifacts,
     * e.g., in IDEs with limited FX support. NetBeans ignores main().
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

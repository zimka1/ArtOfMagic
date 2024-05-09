package CardView;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * An abstract class that provides a foundation for creating card views in a user interface.
 * This class initializes a common background for all cards and defines an abstract method
 * for setting up user interface elements specific to each type of card.
 */
public abstract class GenericCardView extends StackPane {
    /**
     * Constructor that initializes the card view with a default background.
     */
    public GenericCardView() {
        initializeBackground();
    }
    /**
     * Initializes the background of the card view. This method creates a rectangle that
     * will serve as the backdrop for the card elements. The style and dimensions are predefined
     * and can be customized by modifying the 'card-background' style class in CSS.
     */
    private void initializeBackground() {
        Rectangle background = new Rectangle(110, 135);
        background.getStyleClass().add("card-background");
        this.getChildren().add(background);
    }

    /**
     * An abstract method that must be implemented by subclasses to set up specific UI elements
     * such as labels and images that depict the card's attributes like name, cost, power, etc.
     */
    protected abstract void initializeUI();
}
module khessfx {
    requires kotlin.stdlib;
    requires javafx.controls;
    requires javafx.fxml;
    requires carte.controllerfx;

    opens carte.game.khess.controllers to javafx.fxml;

    exports carte.game.khess;
}
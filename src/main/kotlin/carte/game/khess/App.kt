package carte.game.khess

import carte.game.khess.controllers.HomeController
import carte.toolfx.core.runFxmlScreen
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class App: Application() {
    override fun start(stage: Stage) {

        runFxmlScreen<HomeController>(
            stage = stage,
            css = "/css/Application.css",
            title = "KhessFX",
            resizable = true,
            width = 800.0,
            height = 640.0

        )

    }

}
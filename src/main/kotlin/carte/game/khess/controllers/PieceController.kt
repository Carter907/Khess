package carte.game.khess.controllers

import carte.toolfx.core.Controller
import carte.toolfx.core.Element

import javafx.fxml.FXML
import javafx.scene.image.ImageView

@Element("/fxml/nodes/piece.fxml")
class PieceController : Controller(){



    @FXML
    lateinit var pieceImage: ImageView;


    override fun onCreate() {

    }
}
package at.fhj.swengb.apps.calculator

import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.beans.property.{ObjectProperty, SimpleObjectProperty}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.TextField
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.util.{Failure, Success}
import scala.util.control.NonFatal

object CalculatorApp {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[CalculatorFX], args: _*)
  }
}

class CalculatorFX extends javafx.application.Application {

  val fxml = "/at/fhj/swengb/apps/calculator/calculator.fxml"
  val css = "/at/fhj/swengb/apps/calculator/calculator.css"

  def mkFxmlLoader(fxml: String): FXMLLoader = {
    new FXMLLoader(getClass.getResource(fxml))
  }

  override def start(stage: Stage): Unit =
    try {
      stage.setTitle("Calculator")
      setSkin(stage, fxml, css)
      stage.show()
      stage.setMinWidth(stage.getWidth)
      stage.setMinHeight(stage.getHeight)
    } catch {
      case NonFatal(e) => e.printStackTrace()
    }

  def setSkin(stage: Stage, fxml: String, css: String): Boolean = {
    val scene = new Scene(mkFxmlLoader(fxml).load[Parent]())
    stage.setScene(scene)
    stage.getScene.getStylesheets.clear()
    stage.getScene.getStylesheets.add(css)
  }

}

class CalculatorFxController extends Initializable {

  val calculatorProperty: ObjectProperty[RpnCalculator] = new SimpleObjectProperty[RpnCalculator](RpnCalculator())

  def getCalculator() : RpnCalculator = calculatorProperty.get()

  def setCalculator(rpnCalculator : RpnCalculator) : Unit = calculatorProperty.set(rpnCalculator)

  @FXML var numberTextField : TextField = _

  /*
  //Output labels
  @FXML private var lbl1: Label = _
  @FXML private var lbl2: Label = _
  @FXML private var lbl3: Label = _
  @FXML private var taStack: TextArea = _

  //Functionality Buttons
  @FXML private var bttnComma: Button = _

  //All math function buttons of calculator
  @FXML var bttnMinus: Button = _
  @FXML var bttnPlus: Button = _
  @FXML var bttnMultiply: Button = _
  @FXML var bttnDivide: Button = _

  private var writeToLabel2: Boolean = false
  private var replaceLabelText: Boolean = true
  private var clearJustLabelContent = true
  private var positiveNumber: Boolean = true; */

  override def initialize(location: URL, resources: ResourceBundle) = {

  }

  def sgn(): Unit = {
    getCalculator().push(Op(numberTextField.getText)) match {
      case Success(c) => setCalculator(c)
      case Failure(e) => e.getMessage
    }
    getCalculator().stack foreach println
  }


}
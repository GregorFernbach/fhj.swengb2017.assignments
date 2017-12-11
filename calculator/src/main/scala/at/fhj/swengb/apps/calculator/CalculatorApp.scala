package at.fhj.swengb.apps.calculator


import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.beans.property.{ObjectProperty, SimpleObjectProperty}
import javafx.event.ActionEvent
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.Label
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.util.control.NonFatal
import scala.util.{Failure, Success}

object CalculatorApp {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[CalculatorFX], args: _*)
  }
}

class CalculatorFX extends javafx.application.Application {

  val fxml = "/at/fhj/swengb/apps/calculator/calculator.fxml"
  val css = "/at/fhj/swengb/apps/calculator/calculator.css"

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

  def mkFxmlLoader(fxml: String): FXMLLoader = {
    new FXMLLoader(getClass.getResource(fxml))
  }

}

class CalculatorFxController extends Initializable {

  val calculatorProperty: ObjectProperty[RpnCalculator] = new SimpleObjectProperty[RpnCalculator](RpnCalculator())
  @FXML var lbl1: Label = _
  @FXML var lbl2: Label = _
  var ac: Boolean = false

  override def initialize(location: URL, resources: ResourceBundle) = {

  }

  def clearerPrimusDerErste: Unit = {
    if (ac) {
      setCalculator(RpnCalculator())
      lbl1.setText("")
      lbl2.setText("")
      ac = false
    }
    else {
      lbl1.setText("0")
      ac = true
    }
  }

  def signchanger3comma5: Unit = {
    if (!lbl1.getText.isEmpty) {
      if (lbl1.getText.head.equals('-'))
        lbl1.setText(lbl1.getText.tail)
      else lbl1.setText('-' + lbl1.getText)
    }
    else lbl1.setText("-")
  }

  def commamaker100000: Unit = if (lbl1.getText.isEmpty) inserter2000("0.") else if (lbl1.getText.count(_ == '.') < 1) inserter2000(".")

  def enterer8000: Unit = {
    if (!lbl1.getText.isEmpty) {
      sgn()
      if (true)
        if (lbl2.getText.isEmpty) {
          lbl2.setText(lbl1.getText)
          lbl1.setText("")
        }
        else {
          lbl2.setText(lbl1.getText)
          lbl1.setText("")
        }
    }
    else {
      inserter2000("0")
    }
  }

  def sgn(): Unit = {
    getCalculator().push(Op(lbl1.getText)) match {
      case Success(c) => setCalculator(c)
      case Failure(e) => e.getMessage
    }
    getCalculator().stack foreach println
  }

  def inserter2000(number: String): Unit = {
    number match {
      case "Add" => getCalculator().push(Op("+")) match {
        case Success(c) => setCalculator(c)
          lbl2.setText(getCalculator().stack.last match { case v: Val => v.value.toString })

      }
      case "Sub" => getCalculator().push(Op("-")) match {
        case Success(c) => setCalculator(c)
          lbl2.setText(getCalculator().stack.last match { case v: Val => v.value.toString })

      }
      case "Mul" => getCalculator().push(Op("*")) match {
        case Success(c) => setCalculator(c)
          lbl2.setText(getCalculator().stack.last match { case v: Val => v.value.toString })

      }
      case "Div" => getCalculator().push(Op("/")) match {
        case Success(c) => setCalculator(c)
          lbl2.setText(getCalculator().stack.last match { case v: Val => v.value.toString })

      }
      case _ =>
        if (lbl1.getText.isEmpty) lbl1.setText(number)
        else lbl1.setText(lbl1.getText + number)
    }
  }

  def getCalculator(): RpnCalculator = calculatorProperty.get()

  def setCalculator(rpnCalculator: RpnCalculator): Unit = calculatorProperty.set(rpnCalculator)

  @FXML private def txt5000(click: ActionEvent): Unit = {
    val button: String = click.getSource.asInstanceOf[javafx.scene.control.Button].toString
    println(button)
    button match {
      case "Button[id=bttn1, styleClass=button]'1'" => inserter2000("1")
      case "Button[id=bttn2, styleClass=button]'2'" => inserter2000("2")
      case "Button[id=bttn3, styleClass=button]'3'" => inserter2000("3")
      case "Button[id=bttn4, styleClass=button]'4'" => inserter2000("4")
      case "Button[id=bttn5, styleClass=button]'5'" => inserter2000("5")
      case "Button[id=bttn6, styleClass=button]'6'" => inserter2000("6")
      case "Button[id=bttn7, styleClass=button]'7'" => inserter2000("7")
      case "Button[id=bttn8, styleClass=button]'8'" => inserter2000("8")
      case "Button[id=bttn9, styleClass=button]'9'" => inserter2000("9")
      case "Button[id=bttn0, styleClass=button]'0'" => inserter2000("0")
      case "Button[id=bttnEnter, styleClass=button]'='" => enterer8000
      case "Button[id=bttnComma, styleClass=button]'.'" => commamaker100000
      case "Button[id=bttnPlus, styleClass=button]'+'" => inserter2000("Add")
      case "Button[id=bttnMinus, styleClass=button]'−'" => inserter2000("Sub")
      case "Button[id=bttnMultiply, styleClass=button]'*'" => inserter2000("Mul")
      case "Button[id=bttnDivide, styleClass=button]'/'" => inserter2000("Div")
      case "Button[id=bttnClear, styleClass=button]'C'" => clearerPrimusDerErste
      case "Button[id=bttnNegative, styleClass=button]'-x'" => signchanger3comma5
      case _ => //Fallback – If this happens, some really crazy shit is going on over here…*/
    }
  }
}
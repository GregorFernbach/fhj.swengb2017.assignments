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

  override def initialize(location: URL, resources: ResourceBundle) = {

  }

  @FXML private def txt5000(click: ActionEvent): Unit = {
    val button: String = click.getSource.asInstanceOf[javafx.scene.control.Button].toString
    button match {
      case _ => println(button)
      /*
    case "Button[id=bttn1, styleClass=button]'1'" => insertThis("1")
    case "Button[id=bttn2, styleClass=button]'2'" => insertThis("2")
    case "Button[id=bttn3, styleClass=button]'3'" => insertThis("3")
    case "Button[id=bttn4, styleClass=button]'4'" => insertThis("4")
    case "Button[id=bttn5, styleClass=button]'5'" => insertThis("5")
    case "Button[id=bttn6, styleClass=button]'6'" => insertThis("6")
    case "Button[id=bttn7, styleClass=button]'7'" => insertThis("7")
    case "Button[id=bttn8, styleClass=button]'8'" => insertThis("8")
    case "Button[id=bttn9, styleClass=button]'9'" => insertThis("9")
    case "Button[id=bttn0, styleClass=button]'0'" => insertThis("0")
    case "Button[id=bttnEnter, styleClass=button]'='" => onEnter
    case "Button[id=bttnComma, styleClass=button]'.'" => comma
    case "Button[id=bttnPlus, styleClass=button]'+'" => insertThis("Add")
    case "Button[id=bttnMinus, styleClass=button]'−'" => insertThis("Sub")
    case "Button[id=bttnMultiply, styleClass=button]'×'" => insertThis("Mul")
    case "Button[id=bttnDivide, styleClass=button]'÷'" => insertThis("Div")
    case "Button[id=bttnClear, styleClass=button]'C'" => clear
    case "Button[id=bttnChange, styleClass=button]'±'" => changeSign
    case _ => //Fallback – If this happens, some crazy shit is going on…
    */
    }
  }

  /*
    def sgn(): Unit = {
      getCalculator().push(Op(lbl1.getText)) match {
        case Success(c) => setCalculator(c)
        case Failure(e) => e.getMessage
      }
      getCalculator().stack foreach println
    }
  
    def getCalculator(): RpnCalculator = calculatorProperty.get()
  
    def setCalculator(rpnCalculator: RpnCalculator): Unit = calculatorProperty.set(rpnCalculator)
  
    def clear: Unit = {
      if (ca) {
        setCalculator(RpnCalculator())
        num1.setText("")
        num2.setText("")
        num3.setText("")
        ca = false
      }
      else {
        num1.setText("0")
        ca = true
      }
    }
  
    def changeSign: Unit = {
      if (!num1.getText.isEmpty) {
        if (num1.getText.head.equals('-'))
          num1.setText(num1.getText.tail)
        else num1.setText('-' + num1.getText)
      }
      else num1.setText("-")
    }
  
    def insertThis(number: String): Unit = {
      number match {
        case "Add" => getCalculator().push(Op("+")) match {
          case Success(c) => setCalculator(c)
            num2.setText(getCalculator().stack.last match { case v: Val => v.value.toString })
            num3.setText("")
        }
        case "Sub" => getCalculator().push(Op("-")) match {
          case Success(c) => setCalculator(c)
            num2.setText(getCalculator().stack.last match { case v: Val => v.value.toString })
            num3.setText("")
        }
        case "Mul" => getCalculator().push(Op("*")) match {
          case Success(c) => setCalculator(c)
            num2.setText(getCalculator().stack.last match { case v: Val => v.value.toString })
            num3.setText("")
        }
        case "Div" => getCalculator().push(Op("/")) match {
          case Success(c) => setCalculator(c)
            num2.setText(getCalculator().stack.last match { case v: Val => v.value.toString })
            num3.setText("")
        }
        case _ =>
          if (num1.getText.isEmpty) num1.setText(number)
          else num1.setText(num1.getText + number)
      }
    }
  
    def comma: Unit = if (num1.getText.isEmpty) insertThis("0.") else if (num1.getText.count(_ == '.') < 1) insertThis(".")
  
    def onEnter: Unit = {
      if (!num1.getText.isEmpty) {
        sgn()
        if (true)
          if (num2.getText.isEmpty) {
            num2.setText(num1.getText)
            num1.setText("")
          }
          else {
            num3.setText(num2.getText)
            num2.setText(num1.getText)
            num1.setText("")
          }
      }
      else {
        insertThis("0")
      }
    }
    */
}
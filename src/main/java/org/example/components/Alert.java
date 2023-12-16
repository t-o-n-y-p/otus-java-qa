package org.example.components;

import static com.codeborne.selenide.appium.SelenideAppium.$x;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class Alert extends AbsComponent {

  private final SelenideElement okButton = $x(xpath + "//android.widget.Button[@text='OK']");

  public Alert() {
    super("//*[@resource-id='android:id/parentPanel' and .//*[@resource-id='android:id/alertTitle']]");
  }
}

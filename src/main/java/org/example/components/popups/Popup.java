package org.example.components.popups;

public interface Popup<T extends Popup<T>> {

  T shouldBeVisible();

  T shouldNotBeVisible();
}

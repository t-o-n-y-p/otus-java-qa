package org.example.extensions;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.pages.ExercisePage;
import org.example.pages.GrammarPage;
import org.example.pages.MainPage;
import org.example.pages.OnboardingPage;

@SuppressWarnings("unused")
public class AndroidModule extends AbstractModule {

  @Provides
  @Singleton
  public OnboardingPage getOnboardingPage() {
    return new OnboardingPage();
  }

  @Provides
  @Singleton
  public MainPage getMainPage() {
    return new MainPage();
  }

  @Provides
  @Singleton
  public ExercisePage getExercisePage() {
    return new ExercisePage();
  }

  @Provides
  @Singleton
  public GrammarPage getGrammarPage() {
    return new GrammarPage();
  }

}

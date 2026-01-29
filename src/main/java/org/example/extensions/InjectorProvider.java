package org.example.extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;

public class InjectorProvider {

  @Getter private static final Injector parent = Guice.createInjector();
}

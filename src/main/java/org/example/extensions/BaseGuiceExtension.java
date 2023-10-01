package org.example.extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.factory.DriverModule;

/**
 .
 */
public abstract class BaseGuiceExtension {

  protected static final Injector INJECTOR = Guice.createInjector(new DriverModule());

}

package com.vinaysshenoy.chronicle.java.sample;

import com.vinaysshenoy.chronicle.Chronicle;
import java.util.logging.Logger;

public class Main {

  private static final Logger LOGGER = Logger.getLogger("Main");

  private final Chronicle chronicle;

  private Main() {
    chronicle = new Chronicle();
  }

  private void run() {
    LOGGER.info("RUNNING!");
  }

  public static void main(String[] args) {
    new Main().run();
  }
}

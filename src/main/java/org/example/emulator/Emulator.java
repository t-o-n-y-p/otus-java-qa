package org.example.emulator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Emulator {
  ANDROID_12(4723, "c9daa54e-c29d-4272-a30e-4f436f3c832d"),
  ANDROID_14(4724, "d9c4f5e7-0b47-4101-859a-28a37706a312");

  private final int port;
  private final String sessionId;

}

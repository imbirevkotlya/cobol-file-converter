package com.epam.lemon.record;

import com.epam.lemon.copybook.Copybook;

public class Record {

  private final Copybook recordStructure;
  private byte[] value;

  public Record(Copybook recordStructure) {
    this.recordStructure = recordStructure;
  }

  public Copybook getRecordStructure() {
    return recordStructure;
  }

  public byte[] getValue() {
    return value;
  }

  public void setValue(byte[] value) {
    this.value = value;
  }
}

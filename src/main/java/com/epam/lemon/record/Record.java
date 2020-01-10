package com.epam.lemon.record;

import com.epam.lemon.copybook.Copybook;

public class Record {

  private final Copybook recordStructure;
  private byte[] value;
  private final Integer length;

  public Record(Copybook recordStructure, Integer length) {
    this.recordStructure = recordStructure;
    this.length = length;
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

  public Integer getLength() {
    return length;
  }
}

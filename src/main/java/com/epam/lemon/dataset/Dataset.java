package com.epam.lemon.dataset;

import com.epam.lemon.record.Record;
import java.util.ArrayList;
import java.util.List;

public class Dataset {

  private final List<Record> records;

  public Dataset() {
    this.records = new ArrayList<>();
  }

  public void addRecord(Record record) {
    records.add(record);
  }

  public List<Record> getRecords() {
    return records;
  }

  public byte[] getDataFile() {
    return null;
  }
}

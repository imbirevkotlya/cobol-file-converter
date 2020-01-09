package com.epam.lemon.converter;

import com.epam.lemon.dataset.Dataset;
import com.epam.lemon.exception.InvalidDataException;
import com.epam.lemon.field.*;
import com.epam.lemon.record.Encoding;
import com.epam.lemon.record.Record;
import com.epam.lemon.record.RecordIterator;
import com.epam.lemon.statement.StatementType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CobolConverter {

  private final List<FieldConverter> fieldConverters;

  public CobolConverter() {
    fieldConverters = new ArrayList<>();
    fieldConverters.add(new IntegerFieldConverter());
    fieldConverters.add(new AlphanumericFieldConverter());
  }

  public byte[] convert(RecordIterator dataFileIterator, Encoding sourceEncoding,  Encoding targetEncoding) throws InvalidDataException {
    Dataset dataset = new Dataset(dataFileIterator.getValue().length);
    while (dataFileIterator.hasNext()) {
      Record record = dataFileIterator.next();
      record = convertRecord(record, sourceEncoding, targetEncoding);
      dataset.addRecord(record);
    }
    return dataset.getDataFile();
  }

  private Record convertRecord(Record record, Encoding sourceEncoding, Encoding targetEncoding) throws InvalidDataException {
    StatementValueIterator statementValueIterator = new StatementValueIterator(record);
    ByteBuffer buffer = ByteBuffer.allocate(statementValueIterator.getRecordLength());
    while (statementValueIterator.hasNext()) {
      for (FieldConverter fieldConverter : fieldConverters) {
        StatementValue statementValue = statementValueIterator.next();
        if (fieldConverter.getStatementType().equals(statementValue.statementType)) {
          byte[] resultValue = fieldConverter.convertValue(statementValue.value, sourceEncoding, targetEncoding);
          buffer.put(resultValue);
        }
      }
    }
    Record resultRecord = new Record(record.getRecordStructure());
    resultRecord.setValue(buffer.array());
    return resultRecord;
  }

  private static class StatementValueIterator implements Iterator<StatementValue> {

    private final Record record;
    private final Integer recordLength;

    StatementValueIterator(Record record) {
      this.record = record;
      recordLength = null;
    }

    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public StatementValue next() {
      return null;
    }

    Integer getRecordLength() {
      return recordLength;
    }
  }

  private static class StatementValue {

    private final byte[] value;
    private final StatementType statementType;
    private final Integer length;

    public StatementValue(byte[] value, StatementType statementType, Integer length) {
      this.value = value;
      this.statementType = statementType;
      this.length = length;
    }
  }

}


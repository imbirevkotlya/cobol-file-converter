package com.epam.lemon.converter;

import com.epam.lemon.dataset.Dataset;
import com.epam.lemon.exception.InvalidDataException;
import com.epam.lemon.field.*;
import com.epam.lemon.record.Encoding;
import com.epam.lemon.record.Record;
import com.epam.lemon.record.RecordIterator;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.GroupDataDeclarationCobolStatement;
import com.epam.lemon.statement.RegularDataDeclarationCobolStatement;
import com.epam.lemon.statement.StatementType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CobolConverter {

  private final List<FieldConverter> fieldConverters;

  public CobolConverter() {
    fieldConverters = new ArrayList<>();
    fieldConverters.add(new IntegerFieldConverter());
    fieldConverters.add(new AlphanumericFieldConverter());
  }

  public byte[] convert(RecordIterator dataFileIterator, Encoding sourceEncoding, Encoding targetEncoding) throws InvalidDataException {
    Dataset dataset = new Dataset(dataFileIterator.getValue().length);
    while (dataFileIterator.hasNext()) {
      Record record = dataFileIterator.next();
      record = convertRecord(record, sourceEncoding, targetEncoding);
      dataset.addRecord(record);
    }
    return dataset.getDataFile();
  }

  private Record convertRecord(Record record, Encoding sourceEncoding, Encoding targetEncoding) throws InvalidDataException {
    FieldValueIterator fieldValueIterator = new FieldValueIterator(record, record.getLength());
    ByteBuffer buffer = ByteBuffer.allocate(record.getLength());
    while (fieldValueIterator.hasNext()) {
      FieldValue fieldValue = fieldValueIterator.next();
      for (FieldConverter fieldConverter : fieldConverters) {
        if (fieldConverter.getStatementType().equals(fieldValue.fieldType)) {
          byte[] resultValue = fieldConverter.convertValue(fieldValue.value, sourceEncoding, targetEncoding);
          buffer.put(resultValue);
        }
      }
    }
    Record resultRecord = new Record(record.getRecordStructure(), record.getLength());
    resultRecord.setValue(buffer.array());
    return resultRecord;
  }

  private static class FieldValueIterator implements Iterator<FieldValue> {

    private final Record record;
    private final List<FieldValue> fieldValues = new ArrayList<>();
    private Integer cursor = 0;
    private Integer startPosition = 0;

    FieldValueIterator(Record record, Integer recordLength) {
      this.record = record;
      initFields();
    }

    private void initFields() {
      byte[] recordValue = record.getValue();
      List<DataDeclarationCobolStatement> recordFields = record.getRecordStructure().getCobolStatements();
      for (DataDeclarationCobolStatement field : recordFields) {
        if (field.getStatementType().equals(StatementType.GROUP_STATEMENT)) {
          initGroupField(recordValue, (GroupDataDeclarationCobolStatement) field);
        }
        else {
          initRegularField(recordValue, (RegularDataDeclarationCobolStatement) field);
        }
      }
    }

    private void initGroupField(byte[] recordValue, GroupDataDeclarationCobolStatement field) {
      List<DataDeclarationCobolStatement> childrenFields = field.getChildrenStatements();
      for (DataDeclarationCobolStatement childrenField : childrenFields) {
        initRegularField(recordValue, (RegularDataDeclarationCobolStatement) childrenField);
      }
    }

    private void initRegularField(byte[] recordValue, RegularDataDeclarationCobolStatement field) {
      Integer fieldLength = field.getLength();
      try {
        FieldValue fieldValue = new FieldValue(
            Arrays.copyOfRange(recordValue, startPosition, startPosition + fieldLength),
            field.getStatementType());
        startPosition += fieldLength;
        fieldValues.add(fieldValue);
      } catch (Exception e) {
        throw new InvalidDataException("Such field is wrong typed: " + field.getName()
            + ", in such record with value: " + Arrays.toString(recordValue));
      }
    }

    @Override
    public boolean hasNext() {
      return cursor < fieldValues.size();
    }

    @Override
    public FieldValue next() {
      if (hasNext()) {
        return fieldValues.get(cursor++);
      }
      throw new NoSuchElementException();
    }
  }

  private static class FieldValue {

    private final byte[] value;
    private final StatementType fieldType;

    FieldValue(byte[] value, StatementType fieldType) {
      this.value = value;
      this.fieldType = fieldType;
    }
  }

}

